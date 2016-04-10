package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.EntityManagerManager;
import com.sneaky.peaky.dataparser.dao.MatchDao;
import com.sneaky.peaky.dataparser.dao.staticdata.ChampionDao;
import com.sneaky.peaky.dataparser.dao.staticdata.MasteryDao;
import com.sneaky.peaky.dataparser.dao.staticdata.RuneDao;
import com.sneaky.peaky.dataparser.domain.mapper.MatchRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPMastery;
import com.sneaky.peaky.dataparser.domain.pojo.SPMatch;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPRune;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.MasteryJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.MatchJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.RuneJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAMastery;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAMatch;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPARune;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import net.boreeas.riotapi.rest.MatchDetail;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel Mangelschots
 */
public class MatchService {

    public void fetchMatch(ThrottledApiHandler handler, Long matchId) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        MatchDao md = new MatchDao(em);
        JPAMatch match = md.find(matchId);
        if(match != null){
            em.remove(match);
        }
        em.getTransaction().commit();
        em.getTransaction().begin();
        
        MatchRestMapper matchRestMapper = new MatchRestMapper();
        MatchJPAToSPMapper matchJPAToSPMapper = new MatchJPAToSPMapper();
        MatchDao matchDao = new MatchDao(em);
        ChampionJPAToSPMapper championJPAToSPMapper = new ChampionJPAToSPMapper();
        ChampionDao championDao = new ChampionDao(em);
        MasteryJPAToSPMapper masteryJPAToSPMapper = new MasteryJPAToSPMapper();
        MasteryDao masteryDao = new MasteryDao(em);
        RuneJPAToSPMapper runeJPAToSPMapper = new RuneJPAToSPMapper();
        RuneDao runeDao = new RuneDao(em);

        Set<JPAChampion> jpaChampions = championDao.findAll();
        Map<Integer, JPAChampion> jpaChampMap = jpaChampions.stream().collect(Collectors.toMap(JPAChampion::getId, Function.identity()));
        Set<SPChampion> spChampions = championJPAToSPMapper.mapToSP(jpaChampions);
        Map<Integer, SPChampion> spChampMap = spChampions.stream().collect(Collectors.toMap(SPChampion::getId, Function.identity()));
        
        Set<JPAMastery> jpaMasteries = masteryDao.findAll();
        Map<Integer, JPAMastery> jpaMasMap = jpaMasteries.stream().collect(Collectors.toMap(JPAMastery::getId, Function.identity()));
        Set<SPMastery> spMasteries = masteryJPAToSPMapper.mapToSP(jpaMasteries);
        Map<Integer, SPMastery> spMasMap = spMasteries.stream().collect(Collectors.toMap(SPMastery::getId, Function.identity()));
        
        Set<JPARune> jpaRunes = runeDao.findAll();
        Map<Integer, JPARune> jpaRuneMap = jpaRunes.stream().collect(Collectors.toMap(JPARune::getId, Function.identity()));
        Set<SPRune> spRunes = runeJPAToSPMapper.mapToSP(jpaRunes);
        Map<Integer, SPRune> spRuneMap = spRunes.stream().collect(Collectors.toMap(SPRune::getId, Function.identity()));

        MatchDetail matchDetail = handler.getMatch(matchId).get();
        SPMatch spm = matchRestMapper.mapToSP(matchDetail, spChampMap, spMasMap, spRuneMap);
        JPAMatch jpam = matchJPAToSPMapper.mapToJPA(spm, jpaChampMap, jpaMasMap, jpaRuneMap);
        matchDao.create(jpam);
        
        EntityManagerManager.closeSession(em);
    }
}
