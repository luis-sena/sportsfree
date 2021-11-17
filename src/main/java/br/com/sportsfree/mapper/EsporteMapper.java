package br.com.sportsfree.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.entity.EsporteEntity;

@Component
public class EsporteMapper implements AbstractMapper<EsporteDto, EsporteEntity>{
    
    public EsporteEntity mapToEntity(EsporteDto dto) {
        EsporteEntity esporte = new EsporteEntity();       
        BeanUtils.copyProperties(dto, esporte);
        esporte.setId(dto.getId());
        return esporte;
    }

    public EsporteDto mapToDto(EsporteEntity esporte) {
        EsporteDto dto = new EsporteDto();
        BeanUtils.copyProperties(esporte, dto);
        dto.setId(esporte.getId());
        return dto;
    }

}
