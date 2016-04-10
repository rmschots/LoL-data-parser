package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.EntityManagerManager;
import com.sneaky.peaky.dataparser.dao.staticdata.ChampionDao;
import com.sneaky.peaky.dataparser.dao.SummonerMatchDao;
import com.sneaky.peaky.dataparser.domain.mapper.SummonerMatchRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.SPSummonerMatch;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.SummonerMatchJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummonerMatch;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
import net.boreeas.riotapi.rest.MatchSummary;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchService {

    public void fetchMatchesForSummoners(ThrottledApiHandler handler, List<Long> summonerIds) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        
        SummonerMatchRestMapper matchRestMapper = new SummonerMatchRestMapper();
        SummonerMatchJPAToSPMapper matchJPAToSPMapper = new SummonerMatchJPAToSPMapper();
        ChampionJPAToSPMapper championJPAToSPMapper = new ChampionJPAToSPMapper();
        ChampionDao staticDataDAO = new ChampionDao(em);
        SummonerMatchDao summonerMatchDAO = new SummonerMatchDao(em);

        for (Long summonerId : summonerIds) {
            Long startTime = summonerMatchDAO.findMostRecentStartTimeForSummoner(summonerId);
            Set<MatchSummary> matchSummary = new HashSet<>(handler.getMatchHistory(summonerId, new String[0], new QueueType[]{QueueType.RANKED_SOLO_5x5, QueueType.RANKED_TEAM_5x5}, startTime).get());

            Set<JPAChampion> jpaChampions = staticDataDAO.findAll();
            Map<Integer, JPAChampion> jpaChampMap = jpaChampions.stream().collect(Collectors.toMap(JPAChampion::getId, Function.identity()));
            Set<SPChampion> spChampions = championJPAToSPMapper.mapToSP(jpaChampions);
            Map<Integer, SPChampion> spChampMap = spChampions.stream().collect(Collectors.toMap(SPChampion::getId, Function.identity()));

            Set<SPSummonerMatch> spMatches = matchRestMapper.mapToSP(matchSummary, spChampMap, 23278439L);
            Set<JPASummonerMatch> jpaMatches = matchJPAToSPMapper.mapToJPA(spMatches, jpaChampMap);

            for (JPASummonerMatch match : jpaMatches) {
                summonerMatchDAO.create(match);
            }
        }

        EntityManagerManager.closeSession(em);
    }
}
