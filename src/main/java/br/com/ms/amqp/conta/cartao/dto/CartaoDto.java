package br.com.ms.amqp.conta.cartao.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

public class CartaoDto {

    public enum Response{;

        @Data
        public static class Cartao{
            private UUID id;
            private UUID contaId;
            private String nome;
            private String numero;
            private String dataValidade;
            private String cvv;
            private boolean padrao;
            private LocalDateTime dataCriacao;
        }
    }
}
