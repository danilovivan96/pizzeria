package ru.mail.danilov.pizzeria.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.danilov.pizzeria.service.dto.UserDto;

import java.util.regex.Pattern;

@Component("userUpdateValidator")
public class UserUpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "surname", "user.surname.empty");
        ValidationUtils.rejectIfEmpty(errors, "login", "item.login.empty");

        UserDto user = (UserDto) object;
        Pattern loginPattern = Pattern.compile(
                "^[0-9A-Za-z.%?!_-]{6,40}$",
                Pattern.CASE_INSENSITIVE);
        Pattern namePattern = Pattern.compile("^[A-Za-z]{2,40}$",
                Pattern.CASE_INSENSITIVE);

        if (!(loginPattern.matcher(user.getLogin()).matches())) {
            errors.rejectValue("login", "user.login.invalid");
        } else if (!(namePattern.matcher(user.getName()).matches())) {
            errors.rejectValue("login", "user.name.invalid");
        } else if (!(namePattern.matcher(user.getSurname()).matches())) {
            errors.rejectValue("login", "user.surname.invalid");

        }
    }
}
