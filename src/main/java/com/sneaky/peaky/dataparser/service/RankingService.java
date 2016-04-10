package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.EntityManagerManager;
import com.sneaky.peaky.dataparser.dao.RankingDao;
import com.sneaky.peaky.dataparser.domain.mapper.RankingRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.SPRanking;
import com.sneaky.peaky.dataparser.jpa.mapper.RankingJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.JPARanking;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import net.boreeas.riotapi.com.riotgames.leagues.pojo.LeagueItem;
import net.boreeas.riotapi.com.riotgames.leagues.pojo.LeagueList;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
import net.boreeas.riotapi.constants.LeagueTier;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel
 */
public class RankingService {

    public void fetchChallengerRanks(ThrottledApiHandler handler, QueueType queueType) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        
        boolean isTeam = (queueType==QueueType.RANKED_TEAM_3x3 || queueType == QueueType.RANKED_TEAM_5x5);
        
        RankingRestMapper rankingRestMapper = new RankingRestMapper();
        RankingJPAToSPMapper rankingJPAToSPMapper = new RankingJPAToSPMapper();
        RankingDao rankingDao = new RankingDao(em);
        long revisionDate = System.currentTimeMillis();
        LeagueList leagueList = handler.getChallenger(queueType).get();        
        for (LeagueItem leagueItem : leagueList.getEntries()) {
            leagueItem.setQueueType(queueType.name());
            leagueItem.setTier(LeagueTier.CHALLENGER.name());
            SPRanking spRanking = rankingRestMapper.mapToSP(leagueItem, isTeam, revisionDate);
            JPARanking jpaRanking = rankingJPAToSPMapper.mapToJPA(spRanking);
            rankingDao.create(jpaRanking);
        }
        
        EntityManagerManager.closeSession(em);
    }
}
