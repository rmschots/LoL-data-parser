package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPATeam;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Roel
 */
public class TeamDao extends AbstractDao<JPATeam> implements Dao<JPATeam>{
    public TeamDao(EntityManager em){
        super(em);
    }
    
    public JPATeam findByTeamId(String teamId, Long revisionDate) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.teamId = ").append(teamId).append(" AND o.revisionDate = ").append(revisionDate);
        final Query query = em.createQuery(queryString.toString());
        JPATeam result;
        try{
            result = (JPATeam) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        return result;
    }
    
    public JPATeam findByTeamId(String teamId) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.teamId = '").append(teamId).append("' ORDER BY o.revisionDate DESC");
        final Query query = em.createQuery(queryString.toString()).setMaxResults(1);
        JPATeam result;
        try{
            result = (JPATeam) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        return result;
    }
    
    public Set<Long> findAllSummonersOfTeams() {
        final Query query = em.createQuery("select distinct(s) from JPATeam o join o.summonerIds s");
        Set<Long> result;
        try{
            result = new HashSet<>(query.getResultList());
        }catch(Exception e){
            result = null;
        }
        return result;
    }
}

