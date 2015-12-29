package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.AbstractJPAObject;
import java.util.List;

/**
 *
 * @author Roel Mangelschots
 * @param <T> AbstractJPAObject
 */
public interface Dao<T extends AbstractJPAObject> {
    List<T> findAll();
    T create(T t);
    void delete(Object id);
    T find(Object id);
    T update(T t);   
}
