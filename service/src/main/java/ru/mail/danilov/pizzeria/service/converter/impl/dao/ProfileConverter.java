package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.dto.ProfileDto;
import ru.mail.danilov.pizzeria.dao.model.Profile;

import java.util.List;
@Component("profileConverter")
public class ProfileConverter implements Converter<ProfileDto, Profile> {
    @Override
    public Profile toEntity(ProfileDto dto) {
        Profile profile = new Profile();
        profile.setAddress(dto.getAddress());
        profile.setTelephone(dto.getTelephone());
        return profile;
    }
}
