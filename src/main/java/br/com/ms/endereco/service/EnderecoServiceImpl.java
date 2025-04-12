package br.com.ms.endereco.service;

import br.com.ms.endereco.Endereco;
import br.com.ms.endereco.dto.EnderecoDto;
import br.com.ms.endereco.repository.EnderecoRepository;
import br.com.ms.utils.service.DtoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static br.com.ms.config.exception.enums.MensagensException.ENTIDADE_NAO_ENCONTRADA;

@Service
public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Object buscar(String id, Pageable pageable) {
        if(Objects.nonNull(id)){
            Endereco endereco = this.enderecoRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

            return DtoService.entityToDto(endereco, EnderecoDto.Response.Endereco.class);
        }

        Page<Endereco> enderecos = this.enderecoRepository.findAll(pageable);
        return new PageImpl<>(DtoService.entitysToDtos(enderecos.getContent(), EnderecoDto.Response.Endereco.class));
    }

    @Override
    public EnderecoDto.Response.Endereco cadastrar(EnderecoDto.Request.Endereco dto) {
        Endereco endereco = DtoService.dtoToEntity(dto, Endereco.class);
        Endereco enderecoPersistido = this.enderecoRepository.save(endereco);

        return DtoService.entityToDto(enderecoPersistido, EnderecoDto.Response.Endereco.class);
    }

    @Override
    public void deletar(String id) {
        Endereco endereco = this.enderecoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(ENTIDADE_NAO_ENCONTRADA.getDescricao()));

        this.enderecoRepository.delete(endereco);
    }
}
