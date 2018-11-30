package ru.mail.danilov.pizzeria.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.ItemDao;
import ru.mail.danilov.pizzeria.dao.model.Item;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {
    public ItemDaoImpl() {
        super(Item.class);
    }

    private static final Logger logger = LogManager.getLogger(ItemDaoImpl.class);

    @Override
    public Item findByUniqueNumber(String uniqueNum) {
        String getByNum = "from Item as I where I.uniqueNumber=:number";
        Query query = getCurrentSession().createQuery(getByNum);
        query.setParameter("number", uniqueNum);
        try {
            return (Item) query.uniqueResult();
        } catch (Exception e) {
            logger.info("item with num: " + uniqueNum + " not found");
            return null;
        }
    }

    @Override
    public Item findByName(String name) {
        String getByName = "from Item as I where I.name=:name";
        Query query = getCurrentSession().createQuery(getByName);
        query.setParameter("name", name);
        try {
            return (Item) query.uniqueResult();
        } catch (Exception e) {
            logger.info("item with name: " + name + " not found");
            return null;
        }
    }

    @Override

    public Long countAll() {
        String countItem = "select count(*) from Item as i  where i.isDelete=false";
        Query query = getCurrentSession().createQuery(countItem);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Item> getForPage(Long page) {
        String selectPage = "from Item as i  where i.isDelete=false order by i.name desc";
        Query query = getCurrentSession().createQuery(selectPage);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

}