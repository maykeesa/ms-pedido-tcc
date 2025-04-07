package br.com.ms.pedido;

import br.com.ms.pedido.dto.PedidoDto;
import br.com.ms.pedido.service.PedidoService;
import br.com.ms.produto.dto.ProdutoDto;
import br.com.ms.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Object> buscar(
            @RequestParam(required = false) String id,
            @PageableDefault(sort = "nome", size = 15, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(this.pedidoService.buscar(id, pageable));
    }

    @PostMapping
    public ResponseEntity<PedidoDto.Response.Pedido> cadastrar(
            @RequestBody @Valid PedidoDto.Request.Pedido dto){
        return ResponseEntity.status(CREATED).body(this.pedidoService.cadastrar(dto));
    }
}
