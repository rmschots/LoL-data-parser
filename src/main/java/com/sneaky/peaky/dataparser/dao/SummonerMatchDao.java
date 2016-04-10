package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummonerMatch;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchDAO extends AbstractDao<JPASummonerMatch> implements Dao<JPASummonerMatch>{
    public SummonerMatchDAO(){
        super();
    }
    
    public JPASummonerMatch findBySummonerId(Long summonerId) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.summonerId = ").append(summonerId).append(" ORDER BY o.startTime DESC");
        EntityManager em = openSession();
        final Query query = em.createQuery(queryString.toString()).setMaxResults(1);
        JPASummonerMatch result;
        try{
            result = (JPASummonerMatch) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        closeSession(em);
        return result;
    }
    
    public Long findMostRecentStartTimeForSummoner(Long summonerId) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o.startTime from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.summonerId = ").append(summonerId).append(" ORDER BY o.startTime DESC").append(" LIMIT 1");
        EntityManager em = openSession();
        final Query query = em.createQuery(queryString.toString()).setMaxResults(1);
        Long result;
        try{
            result = (Long) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        closeSession(em);
        return result;
    }
    
}