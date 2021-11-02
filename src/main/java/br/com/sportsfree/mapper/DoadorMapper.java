package br.com.sportsfree.mapper;

import br.com.sportsfree.dto.ContatoDto;
import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.entity.DoadorEntity;
import br.com.sportsfree.entity.embeddable.Contato;
import br.com.sportsfree.entity.embeddable.Endereco;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DoadorMapper implements AbstractMapper<DoadorDto, DoadorEntity> {

    public DoadorEntity mapToEntity(DoadorDto dto) {
        DoadorEntity doadorEntity = new DoadorEntity();
        Endereco endereco = new Endereco();
        Contato contato = new Contato();
        BeanUtils.copyProperties(dto, doadorEntity);
        BeanUtils.copyProperties(dto.getEndereco(), endereco);
        BeanUtils.copyProperties(dto.getContato(), contato);
        dto.getContato().getTelefones().forEach(contato::addTelefone);
        doadorEntity.setEndereco(endereco);
        doadorEntity.setContato(contato);
        doadorEntity.setId(dto.getId());
        return doadorEntity;
    }

    public DoadorDto mapToDto(DoadorEntity doador) {
        DoadorDto dto = new DoadorDto();
        EnderecoDto enderecoDto = new EnderecoDto();
        ContatoDto contatoDto = new ContatoDto();
        BeanUtils.copyProperties(doador, dto);
        BeanUtils.copyProperties(doador.getEndereco(), enderecoDto);
        BeanUtils.copyProperties(doador.getContato(), contatoDto);
        dto.setEndereco(enderecoDto);
        dto.setContato(contatoDto);
        dto.setId(doador.getId());
        return dto;
    }
}
