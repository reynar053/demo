package com.crudtask.db;

import com.crudtask.model.ITableRow;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Scope("prototype")
public class Dao<T extends ITableRow> {
    private final SessionFactory sessionFactory;
    private Class<T> tClass;

    @Autowired
    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Transactional
    public T read(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(tClass, id);
    }

    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(tClass, id));
    }

    @Transactional
    public void create(T tableRow) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(tableRow);
    }

    @Transactional
    public void update(T rowToBeUpdated) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(rowToBeUpdated);
    }

    @Transactional
    public List<T> getRowsWithRepeatingNumberId(String repeatingNumbers, int limit) {
        Session session = sessionFactory.getCurrentSession();

        String hqlQuery = "from :table: where id like concat('%', :repeatingNumbers, '%')";
        Query<T> query = session.createQuery(hqlQuery.replace(":table:", tClass.getSimpleName()), tClass)
                .setParameter("repeatingNumbers", repeatingNumbers)
                .setMaxResults(limit);
        return query.list();
    }

    @Transactional
    public T findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        try {
            String hqlQuery = "FROM :table: WHERE name LIKE CONCAT('%',:name,'%')";
            Query<T> query = session.createQuery(hqlQuery.replace(":table:", tClass.getSimpleName()), tClass)
                    .setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public T findByOrCreate(T t, String column, String key) {
        Session session = sessionFactory.getCurrentSession();
        try {
            String hqlQuery = "FROM :table: WHERE :column: LIKE CONCAT('%',:key,'%')"
                    .replace(":table:", tClass.getSimpleName())
                    .replace(":column:", column);
            Query<T> query = session.createQuery(hqlQuery, tClass)
                    .setParameter("key", key);
            return query.getSingleResult();
        } catch (NoResultException e) {
            create(t);
            return t;
        }
    }
}
