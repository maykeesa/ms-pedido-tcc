package br.com.ms.amqp;

import br.com.ms.amqp.conta.dto.ContaDto;
import br.com.ms.pedido.dto.PedidoDto;
import br.com.ms.utils.service.DtoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PedidoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ContaDto.Response.Conta buscarConta(UUID contaId) {
        Object dados = this.rabbitTemplate.convertSendAndReceive("pedido.conta", contaId);
        return DtoService.entityToDto(dados, ContaDto.Response.Conta.class);
    }

    public void enviarPedidoConcluido(PedidoDto.Response.Pedido pedido, UUID contaId){
        pedido.setContaId(contaId);
        this.rabbitTemplate.convertAndSend("pedido.concluido", pedido);
    }
}
