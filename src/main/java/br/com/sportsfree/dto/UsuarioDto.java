package br.com.sportsfree.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private String email;
    private String nome;
    private String password;
    private List<String> roles;
}
