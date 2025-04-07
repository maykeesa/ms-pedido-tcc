package br.com.ms.produto.dto;

import br.com.ms.pedido.Pedido;
import br.com.ms.produto.enums.DisponibilidadeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProdutoDto {

    public enum Request{;

        @Data
        public static class Produto{
            @NotBlank(message = "O valor não pode ser nulo ou vazio.")
            private String nome;
            @NotBlank(message = "O valor não pode ser nulo ou vazio.")
            private String descricao;
            @NotNull(message = "O valor não pode ser nulo")
            private BigDecimal preco;
            @Pattern(regexp = "DISPONIVEL|INDISPONIVEL", message = "Esse campo só pode ser preenchido como " +
                    "'DISPONIVEL' ou 'INDISPONIVEL' ")
            @NotNull(message = "O valor não pode ser nulo")
            private String disponibilidade;
            @Positive(message = "A quantidade deve ser positiva")
            @NotNull(message = "O valor não pode ser nulo")
            private Integer estoque;
        }
    }

    public enum Response{;

        @Data
        public static class Produto{
            private UUID id;
            private String nome;
            private String descricao;
            private BigDecimal preco;
            private DisponibilidadeStatus disponibilidade;
            private Integer estoque;
            private LocalDateTime dataCriacao;
            private LocalDateTime dataAtualizacao;
            private List<Pedido> pedidos;
        }
    }

}
