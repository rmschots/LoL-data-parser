package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.AbstractJPAObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Roel Mangelschots
 * @param <T> AbstractJPAObject
 */
public abstract class AbstractDao<T extends AbstractJPAObject> implements Dao<T> {

    protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.sneaky.peaky_dataparser");

    protected final Class<T> type;
    
    public AbstractDao(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        EntityManager em = openSession();
        final Query query = em.createQuery(queryString.toString());
        List<T> result = query.getResultList();
        closeSession(em);
        return result;
    }
    
    @Override
    public T create(final T t) {
        EntityManager em = openSession();
        em.persist(t);
        closeSession(em);
        return t;
    }

    @Override
    public void delete(final Object id) {
        EntityManager em = openSession();
        em.remove(em.getReference(type, id));
        closeSession(em);
    }

    @Override
    public T find(final Object id) {
        EntityManager em = openSession();
        T result = em.find(type, id);
        closeSession(em);
        return result;
    }

    @Override
    public T update(final T t) {
        EntityManager em = openSession();
        T result = em.merge(t);
        closeSession(em);
        return result;  
    }

    protected EntityManager openSession() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }

    protected void closeSession(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
