package br.com.sportsfree.utils;

import br.com.sportsfree.dto.EsporteDto;
import br.com.sportsfree.entity.EsporteEntity;

public class EsporteTesteUtils {
    
    public static EsporteDto criarEsporteDto(){
        
        return EsporteDto.builder()
                .nome("Esporte 01")
                .descricao("Descrição sobre o esporte")
                .urlImagem("https://localimagem.com")
                .build();
    }

    public static EsporteEntity criarEsporteEntity(){
        
        return EsporteEntity.builder()
                .nome("Esporte 01")
                .descricao("Descrição sobre o esporte")
                .urlImagem("https://localimagem.com")
                .build();
    }

    public static EsporteDto criarEsporteDtoComId(){
        EsporteDto esporteDto = criarEsporteDto();
        esporteDto.setId(1L);
        return esporteDto;
    }

    public static EsporteEntity criarEsporteEntityComId(){
        EsporteEntity esporteEntity = criarEsporteEntity();
        esporteEntity.setId(1L);
        return esporteEntity;
    }

}
