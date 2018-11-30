package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.Role;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;

@Component("roleDtoConverter")
public class RoleDtoConverter implements ConverterDto<RoleDto, Role> {
    @Override
    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> list) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : list) {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getId());
            roleDto.setName(role.getName());
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }
}
