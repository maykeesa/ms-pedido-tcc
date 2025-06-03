package br.com.ms.amqp.conta.dto;

import br.com.ms.amqp.conta.cartao.dto.CartaoDto;
import br.com.ms.amqp.conta.endereco.dto.EnderecoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ContaDto {

    public enum Response{;

        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Conta{
            private UUID id;
            private String email;
            private String nome;
            private String cpf;
            private List<CartaoDto.Response.Cartao> cartoes;
            private List<EnderecoDto.Response.Endereco> enderecos;
            private LocalDateTime dataCriacao;
        }

    }
}
