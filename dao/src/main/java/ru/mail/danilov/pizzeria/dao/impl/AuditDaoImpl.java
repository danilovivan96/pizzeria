package ru.mail.danilov.pizzeria.dao.impl;

import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.AuditDao;
import ru.mail.danilov.pizzeria.dao.model.Audit;


@Repository
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {
    public AuditDaoImpl() {
        super(Audit.class);
    }
}