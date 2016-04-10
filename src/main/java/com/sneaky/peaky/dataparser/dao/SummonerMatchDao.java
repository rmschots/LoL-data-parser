package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPASummonerMatch;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchDao extends AbstractDao<JPASummonerMatch> implements Dao<JPASummonerMatch>{
    public SummonerMatchDao(EntityManager em){
        super(em);
    }
    
    public JPASummonerMatch findBySummonerId(Long summonerId) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.summonerId = ").append(summonerId).append(" ORDER BY o.startTime DESC");
        final Query query = em.createQuery(queryString.toString()).setMaxResults(1);
        JPASummonerMatch result;
        try{
            result = (JPASummonerMatch) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        return result;
    }
    
    public Long findMostRecentStartTimeForSummoner(Long summonerId) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o.startTime from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.summonerId = ").append(summonerId).append(" ORDER BY o.startTime DESC").append(" LIMIT 1");
        final Query query = em.createQuery(queryString.toString()).setMaxResults(1);
        Long result;
        try{
            result = (Long) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        return result;
    }
    
}