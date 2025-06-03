package br.com.ms.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoAMQPConfiguration {

    @Bean
    public Queue criarFilaPedidoConcluido(){
        return QueueBuilder.nonDurable("pedido.concluido").build();
    }

    @Bean
    public Queue criarFilaContaValidacao() {
        return QueueBuilder.nonDurable("pedido.conta").build();
    }

}
