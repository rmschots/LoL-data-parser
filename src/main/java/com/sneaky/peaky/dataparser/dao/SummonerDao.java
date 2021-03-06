package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerDao extends AbstractDao<JPASummoner> implements Dao<JPASummoner>{
    public SummonerDao(EntityManager em){
        super(em);
    }
    
    public JPASummoner findBySummonerId(Long summonerId, Long revisionDate) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.summonerId = ").append(summonerId).append(" AND o.revisionDate = ").append(revisionDate);
        final Query query = em.createQuery(queryString.toString());
        JPASummoner result;
        try{
            result = (JPASummoner) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        return result;
    }    
}
