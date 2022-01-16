package br.com.sportsfree.validate.validators;

import br.com.sportsfree.validate.constraints.UsuarioExistenteConstraint;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class UsuarioExistenteValidator implements ConstraintValidator<UsuarioExistenteConstraint, String> {

    private AuthErrorCode authErrorCode;

    @Override
    public void initialize(UsuarioExistenteConstraint constraintAnnotation) {}

    @Override
    @SneakyThrows
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            FirebaseAuth.getInstance().getUserByEmail(value);
            context.buildConstraintViolationWithTemplate("Já existe um usuario com o email " + value);
            authErrorCode = AuthErrorCode.EMAIL_ALREADY_EXISTS;
        }catch (FirebaseAuthException ex){
            authErrorCode = ex.getAuthErrorCode();
        }
        return AuthErrorCode.USER_NOT_FOUND.equals(authErrorCode);
    }
}
