package br.com.sportsfree.service.impl;

import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.dto.UsuarioDto;
import br.com.sportsfree.entity.DoadorEntity;
import br.com.sportsfree.mapper.DoadorMapper;
import br.com.sportsfree.repository.DoadorRepository;
import br.com.sportsfree.service.AbstractService;
import br.com.sportsfree.service.UsuarioService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoadorService extends AbstractService<DoadorDto, DoadorEntity> {

    private final UsuarioService usuarioService;

    public DoadorService(DoadorRepository repository, DoadorMapper mapper, UsuarioService usuarioService) {
        super(repository, mapper);
        this.usuarioService = usuarioService;
    }

    @Override
    @SneakyThrows
    @Transactional
    public DoadorDto salvar(DoadorDto dto) {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nome(dto.getNome())
                .email(dto.getContato().getEmail())
                .password(dto.getPassword())
                .roles(List.of("DOADOR"))
                .build();

        usuarioService.cadastro(usuarioDto);
        return super.salvar(dto);
    }

    @Override
    public void deletar(Long id) {
        DoadorDto doadorDto = super.recuperar(id);
        usuarioService.excluir(doadorDto.getContato().getEmail());
        super.deletar(id);
    }
}
