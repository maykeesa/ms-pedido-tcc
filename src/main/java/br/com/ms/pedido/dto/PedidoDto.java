package br.com.ms.pedido.dto;

import br.com.ms.pedido.enums.PedidoStatus;
import br.com.ms.produto.dto.ProdutoDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PedidoDto {

    public enum Request{;

        @Data
        public static class Pedido {
            @NotNull(message = "A lista de produtos não pode ser nula.")
            private List<UUID> produtosIds;
        }
    }

    public enum Response{;

        @Data
        public static class Pedido {
            private UUID id;
            private List<ProdutoDto.Response.Produto> produtos;
            private BigDecimal valorTotal;
            private PedidoStatus status;
            private LocalDateTime dataCriacao;
            private LocalDateTime dataAtualizacao;
        }
    }
}
