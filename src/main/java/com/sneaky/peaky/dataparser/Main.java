package com.sneaky.peaky.dataparser;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;
import com.sneaky.peaky.dataparser.jpa.mapper.SummonerJPAToSPMapper;
import com.sneaky.peaky.dataparser.domain.mapper.SummonerRestMapper;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.boreeas.riotapi.Shard;
import net.boreeas.riotapi.rest.Summoner;
import net.boreeas.riotapi.rest.ThrottledApiHandler;
import static net.boreeas.riotapi.rest.ThrottledApiHandler.*;

/**
 *
 * @author Roel
 */
public class Main {
    
    private final SummonerJPAToSPMapper summonerJPAToSPMapper = new SummonerJPAToSPMapper();
    
    private final SummonerRestMapper summonerRestMapper = new SummonerRestMapper();

    private static final String API_KEY = "dd3cd155-fa3e-43d2-a75b-1afed477bcc6";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Main m = new Main();
        m.test();
    }
    
    public void test() throws InterruptedException, ExecutionException{
        Limit limit1 = new Limit(10, 10, TimeUnit.SECONDS);
        Limit limit2 = new Limit(500, 10, TimeUnit.MINUTES);
        ThrottledApiHandler handler = new ThrottledApiHandler(Shard.EUW, API_KEY, limit1, limit2);
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.sneaky.peaky_dataparser");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        Summoner summoner = handler.getSummoner("Je suis kaas").get();
        
        SPSummoner sPSummoner = summonerRestMapper.mapToSP(summoner);
        
        JPASummoner jPASummoner = summonerJPAToSPMapper.mapToJPA(sPSummoner);
        
        
        entityManager.persist(jPASummoner);
        
        entityManager.getTransaction().commit();
        entityManager.close();
        
        System.exit(0);
    }
}
