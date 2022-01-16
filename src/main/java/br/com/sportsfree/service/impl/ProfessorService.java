package br.com.sportsfree.service.impl;

import br.com.sportsfree.dto.ProfessorDto;
import br.com.sportsfree.dto.UsuarioDto;
import br.com.sportsfree.entity.ProfessorEntity;
import br.com.sportsfree.mapper.ProfessorMapper;
import br.com.sportsfree.repository.ProfessorRepository;
import br.com.sportsfree.service.AbstractService;
import br.com.sportsfree.service.UsuarioService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService extends AbstractService<ProfessorDto, ProfessorEntity> {

    private final UsuarioService usuarioService;

    public ProfessorService(ProfessorRepository repository, ProfessorMapper mapper, UsuarioService usuarioService) {
        super(repository, mapper);
        this.usuarioService = usuarioService;
    }

    @Override
    @SneakyThrows
    public ProfessorDto salvar(ProfessorDto dto) {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .roles(List.of("PROFESSOR"))
                .build();
        usuarioService.cadastro(usuarioDto);
        return super.salvar(dto);
    }

    @Override
    public void deletar(Long id) {
        ProfessorDto professorDto = super.recuperar(id);
        usuarioService.excluir(professorDto.getEmail());
        super.deletar(id);
    }
}
