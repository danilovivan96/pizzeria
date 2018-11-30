package ru.mail.danilov.pizzeria.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.danilov.pizzeria.service.dto.ProfileDto;

import java.util.regex.Pattern;

@Component("profileValidator")
public class ProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileDto.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "address", "profile.address.empty");
        ValidationUtils.rejectIfEmpty(errors, "telephone", "profile.address.empty");
        ProfileDto profile = (ProfileDto) object;
        Pattern telephonePattern = Pattern.compile("^[+375]+[0-9]{9}$");
        Pattern addressPattern = Pattern.compile("^[0-9A-Za-z\\s.,]{0,50}$");
        if (!(telephonePattern.matcher(profile.getTelephone()).matches())) {
            errors.rejectValue("telephone", "profile.telephone.invalid");
        }
        if (!(addressPattern.matcher(profile.getAddress()).matches())) {
            errors.rejectValue("telephone", "profile.address.invalid");
        }
    }
}
