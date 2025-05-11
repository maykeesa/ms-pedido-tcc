package br.com.ms.amqp;

import br.com.ms.pedido.dto.PedidoDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean validarConta(String email) {
        return (Boolean) this.rabbitTemplate.convertSendAndReceive("pedido.conta.validacao", email);
    }

    public void enviarPedidoConcluido(PedidoDto.Response.Pedido pedido){
        this.rabbitTemplate.convertAndSend("pedido.concluido", pedido);
    }
}
