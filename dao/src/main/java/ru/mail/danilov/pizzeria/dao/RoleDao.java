package ru.mail.danilov.pizzeria.dao;

import ru.mail.danilov.pizzeria.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role getByName(String var1);
}
