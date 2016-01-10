package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPARanking;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Roel
 */
public class RankingDao extends AbstractDao<JPARanking> implements Dao<JPARanking>{
    public RankingDao(){
        super();
    }
    
    public List<String> findAllTeamIds() {
        EntityManager em = openSession();
        final Query query = em.createQuery("select distinct o.playerOrTeamId from JPARanking o WHERE o.isTeam = true");
        List<String> result;
        try{
            result = query.getResultList();
        }catch(Exception e){
            result = null;
        }
        closeSession(em);
        return result;
    }
    
    public Set<Long> findAllSummonerIds() {
        EntityManager em = openSession();
        final Query query = em.createQuery("select distinct o.playerOrTeamId from JPARanking o WHERE o.isTeam = false");
        Set<String> result;
        try{
            result = new HashSet<>(query.getResultList());
        }catch(Exception e){
            result = null;
        }
        closeSession(em);
        Set<Long> variable = (Set<Long>)(Set<?>) result;
        return variable;
    }
}
