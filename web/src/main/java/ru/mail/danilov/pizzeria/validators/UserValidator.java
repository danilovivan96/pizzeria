package ru.mail.danilov.pizzeria.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.danilov.pizzeria.service.dto.UserDto;

import java.util.regex.Pattern;

@Component("userValidator")
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "surname", "user.surname.empty");
        ValidationUtils.rejectIfEmpty(errors, "login", "user.login.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
        UserDto user = (UserDto) obj;

        Pattern loginPattern = Pattern.compile(
                "^[0-9A-Za-z.%?!_'@\"\\s]{6,40}$",
                Pattern.CASE_INSENSITIVE);
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{2,40}$",
                Pattern.CASE_INSENSITIVE);
        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9!?_:\\s.]{6,40}$",
                Pattern.CASE_INSENSITIVE);
        if (!(loginPattern.matcher(user.getLogin()).matches()) && !errors.hasFieldErrors("login")) {
            errors.rejectValue("login", "user.login.invalid");
        } else if (!(namePattern.matcher(user.getName()).matches())&& !errors.hasFieldErrors("name")) {
            errors.rejectValue("name", "user.name.invalid");
        } else if (!(namePattern.matcher(user.getSurname()).matches())&& !errors.hasFieldErrors("surname")) {
            errors.rejectValue("surname", "user.surname.invalid");
        } else if (!(passwordPattern.matcher(user.getPassword()).matches())&& !errors.hasFieldErrors("password")) {
            errors.rejectValue("password", "user.password.invalid");
        }
    }
}
