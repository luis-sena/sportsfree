package br.com.sportsfree.validate.constraints;

import br.com.sportsfree.validate.validators.UsuarioExistenteValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsuarioExistenteValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioExistenteConstraint {
    String message() default "Já existe um usuario com o email informado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
