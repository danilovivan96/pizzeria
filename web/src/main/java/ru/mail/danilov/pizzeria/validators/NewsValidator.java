package ru.mail.danilov.pizzeria.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.danilov.pizzeria.service.dto.NewsDto;

import java.util.regex.Pattern;

@Component("newsValidator")
public class NewsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NewsDto.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "tittle", "news.tittle.empty");
        ValidationUtils.rejectIfEmpty(errors, "content", "news.content.empty");
        NewsDto news = (NewsDto) object;
        Pattern tittlePattern = Pattern.compile("^[0-9A-Za-z,./?!_-]{0,50}$",
                Pattern.CASE_INSENSITIVE);
        Pattern contentPattern = Pattern.compile("^[0-9A-Za-z,./?!_-]{0,255}$",
                Pattern.CASE_INSENSITIVE);
        if (!(tittlePattern.matcher(news.getTittle()).matches())) {
            errors.rejectValue("news", "item.tittle.invalid");
        } else if (!(contentPattern.matcher(news.getContent()).matches())) {
            errors.rejectValue("news", "item.content.invalid");
        }
    }
}
