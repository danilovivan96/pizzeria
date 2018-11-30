package ru.mail.danilov.pizzeria.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.danilov.pizzeria.service.dto.ChangePasswordDto;

import java.util.regex.Pattern;

@Component("passwordValidator")
public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePasswordDto.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "newPassword", "password.new.empty");
        ValidationUtils.rejectIfEmpty(errors, "replicateNewPassword", "password.rep.empty");

        ChangePasswordDto passwordDto = (ChangePasswordDto) object;

        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9!?_:;.-]{6,40}$",
                Pattern.CASE_INSENSITIVE);

        if (!(passwordPattern.matcher(passwordDto.getNewPassword()).matches())) {
            errors.rejectValue("newPassword", "password.new.invalid");
        } else if (!passwordDto.getNewPassword().equals(passwordDto.getReplicateNewPassword())) {
            errors.rejectValue("newPassword", "password.coincidence.failed");
        }
    }
}