package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.User;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Component("userDtoConverter")
public class UserDtoConverter implements ConverterDto<UserDto, User> {
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setRole(user.getRole().getName());
        userDto.setPassword(user.getPassword());
        userDto.setEnable(user.getEnable());
        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> list) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : list) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setLogin(user.getLogin());
            userDto.setName(user.getName());
            userDto.setRole(user.getRole().getName());
            userDto.setSurname(user.getSurname());
            userDto.setPassword(user.getPassword());
            userDto.setEnable(user.getEnable());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
