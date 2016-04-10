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
    public RankingDao(EntityManager em){
        super(em);
    }
    
    public List<String> findAllTeamIds() {
        final Query query = em.createQuery("select distinct o.playerOrTeamId from JPARanking o WHERE o.isTeam = true");
        List<String> result;
        try{
            result = query.getResultList();
        }catch(Exception e){
            result = null;
        }
        return result;
    }
    
    public Set<Long> findAllSummonerIds() {
        final Query query = em.createQuery("select distinct o.playerOrTeamId from JPARanking o WHERE o.isTeam = false");
        Set<String> result;
        try{
            result = new HashSet<>(query.getResultList());
        }catch(Exception e){
            result = null;
        }
        Set<Long> variable = (Set<Long>)(Set<?>) result;
        return variable;
    }
}
