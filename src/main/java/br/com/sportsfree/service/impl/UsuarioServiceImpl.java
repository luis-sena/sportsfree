package br.com.sportsfree.service.impl;

import br.com.sportsfree.dto.UsuarioDto;
import br.com.sportsfree.service.UsuarioService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public void cadastro(UsuarioDto usuarioDto) {
        log.info("Criando usuario {}", usuarioDto.getEmail());

        CreateRequest request = new CreateRequest()
                .setEmail(usuarioDto.getEmail())
                .setPassword(usuarioDto.getPassword())
                .setDisplayName(usuarioDto.getNome())
                .setDisabled(false);
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            List<String> roles = usuarioDto.getRoles();
            if(Objects.nonNull(roles) && !roles.isEmpty())
                adcionarClaim(userRecord, Map.of("scope",  String.join(",", roles)));

            log.info("Usuario {} criado com sucesso!", userRecord.getDisplayName());
        } catch (FirebaseAuthException e) {
            log.error("Erro ao criar usuario {}!", usuarioDto.getNome());
        }
    }

    @Override
    public void desativarUsuario(UserRecord user) {
        log.info("Desativando usuario {}", user.getDisplayName());
        try {
            UpdateRequest updateRequest = user.updateRequest().setDisabled(true);
            FirebaseAuth.getInstance().updateUser(updateRequest);
            log.info("Usuario {} desativado com sucesso", user.getDisplayName());
        } catch (FirebaseAuthException e) {
            log.error("Erro ao desativar usuario {}!", user.getDisplayName());
        }
    }

    @Override
    public void adcionarClaim(UserRecord user, Map<String, Object> claims) {
        log.info("Adicionando claims {} no usuario {}", claims, user.getDisplayName());
        Map<String, Object> userCustomClaims = user.getCustomClaims();
        try {
            Map<String, Object> newClaims = claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                if(userCustomClaims.containsKey(entry.getKey())){
                    String customClaimValue = userCustomClaims.get(entry.getKey()).toString();
                    String newClaim = String.valueOf(entry.getValue());
                    if(!customClaimValue.contains(newClaim)){
                        return String.join(",", customClaimValue, newClaim);
                    }
                    return customClaimValue;
                }
                return entry.getValue();
            }));

            FirebaseAuth.getInstance().setCustomUserClaims(user.getUid(), newClaims);
            log.info("Claims {} adicionada com sucesso no usuario {}", claims, user.getDisplayName());
        } catch (FirebaseAuthException e) {
            log.error("Erro ao adicionar claims {} no usuario {}!", claims, user.getDisplayName());
        }
    }

    @Override
    public void excluir(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        try {
            UserRecord userRecord = auth.getUserByEmail(email);
            auth.deleteUser(userRecord.getUid());
            log.info("Usuario {} deletado com sucesso", email);
        } catch (FirebaseAuthException e) {
            log.error("Não existe um usuario com o email {}", email);
        }

    }
}
