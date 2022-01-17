package br.com.sportsfree.service;

import br.com.sportsfree.dto.AbstractDto;
import br.com.sportsfree.entity.AbstractEntity;
import br.com.sportsfree.error.RequestParamException;
import br.com.sportsfree.error.ResourceNotFoundExeption;
import br.com.sportsfree.mapper.AbstractMapper;
import br.com.sportsfree.repository.AbstractRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractService<D extends AbstractDto, E extends AbstractEntity<Long>> {

    private final AbstractRepository<E> repository;
    private final AbstractMapper<D, E> mapper;

    public List<D> listar() {
        try {
            List<E> entities = repository.findAll();
            return entities.stream().map(mapper::mapToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RequestParamException("Erro ao listar recurso");
        }
    }

    public D recuperar(Long id) {
        E entity = recuperarEntity(id);
        return mapper.mapToDto(entity);
    }

    public void deletar(Long id) {
        E entity = recuperarEntity(id);
        try {
            repository.delete(entity);
        } catch (Exception e) {
            throw new RequestParamException("Não foi possível deletar o recurso com o id: " + id);
        }
    }

    public D salvar(D dto) {
        try {
            E entity = repository.saveAndFlush(mapper.mapToEntity(dto));
            return mapper.mapToDto(entity);
        } catch (Exception e) {
            throw new RequestParamException("Erro ao salvar recurso, verifique os parametros digitados");
        }
    }

    public D atualizar(D dto){
        recuperar(dto.getId());
        try {
            return salvar(dto);
        } catch (Exception e) {
            throw new RequestParamException("Erro ao atualizar recurso, verifique os parametros digitados");
        }
    }

    private E recuperarEntity(Long id){
        Optional<E> entity = repository.findById(id);
        return entity.orElseThrow(() -> new ResourceNotFoundExeption("Recurso não encontrado com o ID: " + id));
    }
}
