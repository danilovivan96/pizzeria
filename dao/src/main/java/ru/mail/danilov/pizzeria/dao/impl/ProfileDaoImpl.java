package ru.mail.danilov.pizzeria.dao.impl;

import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.ProfileDao;
import ru.mail.danilov.pizzeria.dao.model.Profile;

@Repository
public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {
    public ProfileDaoImpl() {
        super(Profile.class);
    }
}
