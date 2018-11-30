package ru.mail.danilov.pizzeria.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.danilov.pizzeria.service.dto.CommentDto;

import java.util.regex.Pattern;

@Component("commentValidator")
public class CommentValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return CommentDto.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "content", "comment.content.empty");
        CommentDto comment = (CommentDto) object;
        Pattern contentPattern = Pattern.compile("^[0-9A-Za-z,./?!_-]{0,200}$",
                Pattern.CASE_INSENSITIVE);
        if (!(contentPattern.matcher(comment.getContent()).matches())) {
            errors.rejectValue("content", "comment.content.invalid");
        }
    }
}