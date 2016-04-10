package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.StaticDataDAO;
import com.sneaky.peaky.dataparser.dao.SummonerMatchDAO;
import com.sneaky.peaky.dataparser.domain.mapper.SummonerMatchRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.SPSummonerMatch;
import com.sneaky.peaky.dataparser.jpa.mapper.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.SummonerMatchJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummonerMatch;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
import net.boreeas.riotapi.rest.MatchSummary;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchService {

    public void fetchMatchesForSummoners(ThrottledApiHandler handler, List<Long> summonerIds) throws InterruptedException, ExecutionException {
        SummonerMatchRestMapper matchRestMapper = new SummonerMatchRestMapper();
        SummonerMatchJPAToSPMapper matchJPAToSPMapper = new SummonerMatchJPAToSPMapper();
        ChampionJPAToSPMapper championJPAToSPMapper = new ChampionJPAToSPMapper();
        StaticDataDAO staticDataDAO = new StaticDataDAO();
        SummonerMatchDAO summonerMatchDAO = new SummonerMatchDAO();

        for (Long summonerId : summonerIds) {
            Long startTime = summonerMatchDAO.findMostRecentStartTimeForSummoner(summonerId);
            List<MatchSummary> matchSummary = handler.getMatchHistory(summonerId, new String[0], new QueueType[]{QueueType.RANKED_SOLO_5x5, QueueType.RANKED_TEAM_5x5}, startTime).get();

            List<JPAChampion> jpaChampions = staticDataDAO.findAll();
            Map<Integer, JPAChampion> jpaChampMap = jpaChampions.stream().collect(Collectors.toMap(JPAChampion::getId, Function.identity()));
            List<SPChampion> spChampions = championJPAToSPMapper.mapToSP(jpaChampions);
            Map<Integer, SPChampion> spChampMap = spChampions.stream().collect(Collectors.toMap(SPChampion::getId, Function.identity()));

            List<SPSummonerMatch> spMatches = matchRestMapper.mapToSP(matchSummary, spChampMap, 23278439L);
            List<JPASummonerMatch> jpaMatches = matchJPAToSPMapper.mapToJPA(spMatches, jpaChampMap);

            for (JPASummonerMatch match : jpaMatches) {
                summonerMatchDAO.create(match);
            }
        }

    }
}
