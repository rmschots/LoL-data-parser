package com.sneaky.peaky.dataparser.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Roel Mangelschots
 */
public class EntityManagerManager {
    protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.sneaky.peaky_dataparser");
    
    private static final List<EntityManager> entityManagers = new ArrayList<>();
    
    public static EntityManager openSession() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        entityManagers.add(em);
        return em;
    }
    
    public static void closeSession(EntityManager em) {
        em.getTransaction().commit();
        em.close();
        entityManagers.remove(em);
    }
    
    public static void finalizeAll(){
        while(!entityManagers.isEmpty()){
            EntityManager em = entityManagers.get(0);
            em.clear();
            em.close();
            entityManagers.remove(em);
        }
    }
    
}
