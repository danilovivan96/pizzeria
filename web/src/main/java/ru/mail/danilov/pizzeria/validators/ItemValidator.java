package ru.mail.danilov.pizzeria.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.danilov.pizzeria.service.dto.ItemDto;

import java.util.regex.Pattern;

@Component("itemValidator")
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ItemDto.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "item.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "uniqueNum", "item.uniqueNum.empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "item.price.empty");
        ItemDto item = (ItemDto) object;
        Pattern namePattern = Pattern.compile("^[0-9A-Za-z\\s*]{0,50}$",
                Pattern.CASE_INSENSITIVE);
        Pattern pricePattern = Pattern.compile("^[0-9]{1,3}(\\.+[0-9]{1,3})?$");
        Pattern uniqueNumPattern = Pattern.compile("^[0-9A-Za-z]{0,50}$");
        Pattern descriptionPattern = Pattern.compile("^.{1,255}$",
                Pattern.CASE_INSENSITIVE);
        if (!(uniqueNumPattern.matcher(item.getUniqueNum()).matches())) {
            errors.rejectValue("uniqueNum", "item.unique.number.invalid");
        } else if (!(namePattern.matcher(item.getName()).matches())) {
            errors.rejectValue("name", "item.name.invalid");
        } else if (!(descriptionPattern.matcher(item.getDescription()).matches())) {
            errors.rejectValue("description", "item.description.invalid");
        }
        String price = String.valueOf(item.getPrice());
        if (!(pricePattern.matcher(price).matches())) {
            errors.rejectValue("price", "item.price.invalid");
        }
    }
}
