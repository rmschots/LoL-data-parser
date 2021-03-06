package com.sneaky.peaky.dataparser.dao.staticdata;

import com.sneaky.peaky.dataparser.dao.AbstractDao;
import com.sneaky.peaky.dataparser.dao.Dao;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import javax.persistence.EntityManager;

/**
 *
 * @author Roel Mangelschots
 */
public class ChampionDao extends AbstractDao<JPAChampion> implements Dao<JPAChampion>{
    public ChampionDao(EntityManager em){
        super(em);
    }
}
