package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPAChampion;

/**
 *
 * @author Roel Mangelschots
 */
public class StaticDataDAO extends AbstractDao<JPAChampion> implements Dao<JPAChampion>{
    public StaticDataDAO(){
        super();
    }
}
