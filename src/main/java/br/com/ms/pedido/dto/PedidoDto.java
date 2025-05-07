package br.com.ms.pedido.dto;

import br.com.ms.pedido.enums.PedidoStatus;
import br.com.ms.produto.dto.ProdutoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.ms.utils.constants.MensagensConstants.EMAIL;
import static br.com.ms.utils.constants.MensagensConstants.NAO_NULO_BRANCO;

public class PedidoDto {

    public enum Request{;

        @Data
        public static class Pedido {
            @NotBlank(message = NAO_NULO_BRANCO)
            private String nome;
            @Size(min = 11, max = 11, message = "O valor tem que ter 11 caractéres.")
            @NotBlank(message = NAO_NULO_BRANCO)
            private String cpf;
            @Email(message = EMAIL)
            @NotBlank(message = NAO_NULO_BRANCO)
            private String email;
            @NotBlank(message = NAO_NULO_BRANCO)
            private String enderecoId;
            @NotNull(message = "A lista de produtos não pode ser nula.")
            private List<UUID> produtosIds;
        }
    }

    public enum Response{;

        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Pedido {
            private UUID id;
            private String nrPedido;
            private String nome;
            private String cpf;
            private String email;
            private UUID enderecoId;
            private List<ProdutoDto.Response.Produto> produtos;
            private BigDecimal valorTotal;
            private PedidoStatus status;
            private LocalDateTime dataCriacao;
            private LocalDateTime dataAtualizacao;
        }
    }
}
