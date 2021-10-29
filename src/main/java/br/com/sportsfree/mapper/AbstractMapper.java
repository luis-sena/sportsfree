package br.com.sportsfree.mapper;

public interface AbstractMapper<D,E> {
    E mapToEntity(D dto);
    D mapToDto(E entity);
}
