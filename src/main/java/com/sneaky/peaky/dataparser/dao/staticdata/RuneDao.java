package com.sneaky.peaky.dataparser.dao.staticdata;

import com.sneaky.peaky.dataparser.dao.AbstractDao;
import com.sneaky.peaky.dataparser.dao.Dao;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPARune;
import javax.persistence.EntityManager;

/**
 *
 * @author Roel Mangelschots
 */
public class RuneDao extends AbstractDao<JPARune> implements Dao<JPARune>{
    public RuneDao(EntityManager em){
        super(em);
    }
}