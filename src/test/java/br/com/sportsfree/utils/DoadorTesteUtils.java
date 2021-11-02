package br.com.sportsfree.utils;

import br.com.sportsfree.dto.ContatoDto;
import br.com.sportsfree.dto.DoadorDto;
import br.com.sportsfree.dto.EnderecoDto;
import br.com.sportsfree.entity.DoadorEntity;
import br.com.sportsfree.entity.embeddable.Contato;
import br.com.sportsfree.entity.embeddable.Endereco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoadorTesteUtils {

    public static DoadorDto criarDoadorDto(){
        EnderecoDto enderecoDto = EnderecoDto.builder()
                .cep("11111-111")
                .cidade("Cidade do Doador")
                .bairro("Bairro do Doador")
                .rua("Rua do Doador")
                .numero(20)
                .uf("RP")
                .complemento("Complemento do doador")
                .build();

        ContatoDto contato = ContatoDto.builder()
                .email("doador@doador.com.br")
                .telefones(List.of("11912345678"))
                .redeSocial("@doador")
                .build();

        return DoadorDto.builder()
                .nome("Doador 1")
                .cpfCnpj("111.111.111-11")
                .rg("11.111.11-1")
                .endereco(enderecoDto)
                .contato(contato)
                .build();
    }

    public static DoadorEntity criarDoadorEntity(){
        List<String> telefones = Stream.of("11912345678").collect(Collectors.toList());

        Endereco endereco = Endereco.builder()
                .cep("11111-111")
                .cidade("Cidade do Doador")
                .bairro("Bairro do Doador")
                .rua("Rua do Doador")
                .numero(20)
                .uf("RP")
                .complemento("Complemento do doador")
                .build();

        Contato contato = Contato.builder()
                .email("doador@doador.com.br")
                .telefones(telefones)
                .redeSocial("@doador")
                .build();

        return DoadorEntity.builder()
                .nome("Doador 1")
                .contato(contato)
                .cpfCnpj("111.111.111-11")
                .rg("11.111.11-1")
                .endereco(endereco)
                .build();
    }

    public static DoadorDto criarDoadorDtoComId(){
        DoadorDto doadorDto = criarDoadorDto();
        doadorDto.setId(1L);
        return doadorDto;
    }

    public static DoadorEntity criarDoadorEntityComId(){
        DoadorEntity doadorEntity = criarDoadorEntity();
        doadorEntity.setId(1L);
        return doadorEntity;
    }
}
