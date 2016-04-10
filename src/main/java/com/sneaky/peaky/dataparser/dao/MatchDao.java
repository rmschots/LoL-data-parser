package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPAMatch;
import javax.persistence.EntityManager;

/**
 *
 * @author Roel Mangelschots
 */
public class MatchDao extends AbstractDao<JPAMatch> implements Dao<JPAMatch> {
    public MatchDao(EntityManager em){
        super(em);
    }
}
