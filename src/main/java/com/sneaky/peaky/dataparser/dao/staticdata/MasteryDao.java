package com.sneaky.peaky.dataparser.dao.staticdata;

import com.sneaky.peaky.dataparser.dao.AbstractDao;
import com.sneaky.peaky.dataparser.dao.Dao;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAMastery;
import javax.persistence.EntityManager;

/**
 *
 * @author Roel Mangelschots
 */
public class MasteryDao extends AbstractDao<JPAMastery> implements Dao<JPAMastery>{
    public MasteryDao(EntityManager em){
        super(em);
    }
}
