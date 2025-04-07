package br.com.ms.produto.service.utils;

import br.com.ms.produto.Produto;
import br.com.ms.produto.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.ms.utils.enums.Mensagens.ENTIDADE_NAO_ENCONTRADA;

@Service
public class ProdutoServiceUtils {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarProduto(UUID id){
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));
    }
}
