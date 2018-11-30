package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.User;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.dto.UserDto;

import java.util.List;

@Component("userConverter")
public class UserConverter implements Converter<UserDto, User> {
    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        return user;
    }
}
