package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.AbstractJPAObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Roel Mangelschots
 * @param <T> AbstractJPAObject
 */
public abstract class AbstractDao<T extends AbstractJPAObject> implements Dao<T> {

    protected final Class<T> type;
    
    protected EntityManager em;
    
    public AbstractDao(EntityManager em){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        this.em = em;
    }

    @Override
    public Set<T> findAll() {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        final Query query = em.createQuery(queryString.toString());
        Set<T> result = new HashSet<>(query.getResultList());
        return result;
    }
    
    @Override
    public T create(final T t) {
        em.persist(t);
        return t;
    }

    @Override
    public void delete(final Object id) {
        em.remove(em.getReference(type, id));
    }

    @Override
    public T find(final Object id) {
        T result = em.find(type, id);
        return result;
    }

    @Override
    public T update(final T t) {
        T result = em.merge(t);
        return result;  
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
