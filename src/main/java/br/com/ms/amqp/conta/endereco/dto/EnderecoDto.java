package br.com.ms.amqp.conta.endereco.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

public class EnderecoDto {

    public enum Response{;

        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Endereco {
            private UUID id;
            private String email;
            private String logradouro;
            private Integer numero;
            private String complemento;
            private String bairro;
            private String cidade;
            private String estado;
            private String cep;
            private String pais;
            private boolean padrao;
            private LocalDateTime dataCriacao;
            private LocalDateTime dataAtualizacao;
        }
    }
}
