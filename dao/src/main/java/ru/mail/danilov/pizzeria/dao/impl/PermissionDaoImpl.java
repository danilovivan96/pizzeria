package ru.mail.danilov.pizzeria.dao.impl;

import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.PermissionDao;
import ru.mail.danilov.pizzeria.dao.model.Permission;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {
    public PermissionDaoImpl() {
        super(Permission.class);
    }
}
