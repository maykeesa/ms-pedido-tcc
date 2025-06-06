package br.com.ms.pedido.service;

import br.com.ms.amqp.PedidoPublisher;
import br.com.ms.amqp.conta.dto.ContaDto;
import br.com.ms.pedido.Pedido;
import br.com.ms.pedido.dto.PedidoDto;
import br.com.ms.pedido.repository.PedidoRepository;
import br.com.ms.produto.Produto;
import br.com.ms.produto.service.utils.ProdutoServiceUtils;
import br.com.ms.utils.service.DtoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.com.ms.pedido.enums.PedidoStatus.PENDENTE;
import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoServiceUtils produtoServiceUtils;

    @Autowired
    private PedidoPublisher pedidoPublisher;

    @Override
    public Object buscar(String id, Pageable pageable) {
        if(Objects.nonNull(id)){
            Pedido pedido = this.pedidoRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

            return DtoService.entityToDto(pedido, PedidoDto.Response.Pedido.class);
        }

        Page<Pedido> pedidos = this.pedidoRepository.findAll(pageable);
        return new PageImpl<>(DtoService.entitysToDtos(pedidos.getContent(), PedidoDto.Response.Pedido.class));
    }

    @Override
    public PedidoDto.Response.Pedido cadastrar(PedidoDto.Request.Pedido dto) {
        List<Produto> produtos = dto.getProdutosIds().stream()
                .map(id -> produtoServiceUtils.buscarProduto(id))
                .toList();

        BigDecimal valorTotal = produtos.stream().map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        ContaDto.Response.Conta conta = pedidoPublisher.buscarConta(UUID.fromString(dto.getContaId()));
        if(Objects.isNull(conta)){
            throw new EntityNotFoundException("Conta: " + ENTIDADE_NAO_ENCONTRADA.getDescricao());
        }

        Pedido pedido = new Pedido();
        pedido.setNrPedido(this.produtoServiceUtils.gerarNrPedido());
        pedido.setNome(conta.getNome());
        pedido.setCpf(conta.getCpf());
        pedido.setEmail(conta.getEmail());
        pedido.setEnderecoId(conta.getEnderecos().get(0).getId());
        pedido.setStatus(PENDENTE);
        pedido.setValorTotal(valorTotal);
        pedido.setProdutos(produtos);

        Pedido pedidoPersistido = this.pedidoRepository.save(pedido);
        PedidoDto.Response.Pedido pedidoDto = DtoService.entityToDto(pedidoPersistido, PedidoDto.Response.Pedido.class);
        pedidoDto.setContaId(null);
        pedidoDto.setMetodoPagamento(dto.getMetodoPagamento());

        return pedidoDto;
    }
}
