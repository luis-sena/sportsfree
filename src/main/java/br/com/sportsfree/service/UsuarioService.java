package br.com.sportsfree.service;

import br.com.sportsfree.dto.UsuarioDto;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.util.Map;

public interface UsuarioService {
    void cadastro(UsuarioDto usuarioDto) throws FirebaseAuthException;
    void desativarUsuario(UserRecord user);
    void adcionarClaim(UserRecord user, Map<String, Object> claims);
    void excluir(String email);
}
