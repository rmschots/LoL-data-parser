/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sneaky.peaky.dataparser.dao;

import com.sneaky.peaky.dataparser.jpa.pojo.JPATeam;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Roel
 */
public class TeamDao extends AbstractDao<JPATeam> implements Dao<JPATeam>{
    public TeamDao(){
        super();
    }
    
    public JPATeam findByTeamId(String teamId, Long revisionDate) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.teamId = ").append(teamId).append(" AND o.revisionDate = ").append(revisionDate);
        EntityManager em = openSession();
        final Query query = em.createQuery(queryString.toString());
        JPATeam result;
        try{
            result = (JPATeam) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        closeSession(em);
        return result;
    }
    
    public JPATeam findByTeamId(String teamId) {
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append("WHERE o.teamId = '").append(teamId).append("' ORDER BY o.revisionDate DESC");
        EntityManager em = openSession();
        final Query query = em.createQuery(queryString.toString()).setMaxResults(1);
        JPATeam result;
        try{
            result = (JPATeam) query.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        closeSession(em);
        return result;
    }
    
    public Set<Long> findAllSummonersOfTeams() {
        EntityManager em = openSession();
        final Query query = em.createQuery("select distinct(s) from JPATeam o join o.summonerIds s");
        Set<Long> result;
        try{
            result = new HashSet<>(query.getResultList());
        }catch(Exception e){
            result = null;
        }
        closeSession(em);
        return result;
    }
}

