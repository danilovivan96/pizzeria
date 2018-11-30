package ru.mail.danilov.pizzeria.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.RoleDao;
import ru.mail.danilov.pizzeria.dao.model.Role;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl() {
        super(Role.class);
    }

    public Role getByName(String name) {
        try {
            String getRole = "from Role as R where R.name=:name";
            Query query = this.getCurrentSession().createQuery(getRole);
            query.setParameter("name", name);
            return (Role) query.uniqueResult();
        } catch (Exception var4) {
            logger.info("Object Role with name '" + name + "' not exist");
            logger.error(var4.getMessage(), var4);
            return null;
        }
    }
}
