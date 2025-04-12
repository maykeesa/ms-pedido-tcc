package br.com.ms.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.ms.utils.constants.MensagensConstants.NAO_NULO;
import static br.com.ms.utils.constants.MensagensConstants.NAO_NULO_BRANCO;

public class EnderecoDto {

    public enum Request{;

        @Data
        public static class Endereco {
            @NotBlank(message = NAO_NULO_BRANCO)
            private String email;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String logradouro;
            @NotNull(message = NAO_NULO)
            private Integer numero;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String complemento;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String bairro;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String cidade;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String estado;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String cep;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String pais;
        }
    }

    public enum Response{;

        @Data
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
            private LocalDateTime dataCriacao;
            private LocalDateTime dataAtualizacao;
        }
    }
}
