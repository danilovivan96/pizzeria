package ru.mail.danilov.pizzeria.service;

import ru.mail.danilov.pizzeria.service.dto.ChangePasswordDto;
import ru.mail.danilov.pizzeria.service.dto.ProfileDto;
import ru.mail.danilov.pizzeria.service.dto.RoleDto;
import ru.mail.danilov.pizzeria.service.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getForPage(Long page);

    UserDto register(UserDto user);

    UserDto getById(Long id);

    void delete(Long id);

    void enable(Long id);

    Long countPages(Long quantity);

    ProfileDto saveProfile(ProfileDto profileDto);

    List<RoleDto> getRoleList();

    void update(UserDto user);

    void changeRole(Long user, Long role);

    void changePassword(ChangePasswordDto passwordDto);
}
