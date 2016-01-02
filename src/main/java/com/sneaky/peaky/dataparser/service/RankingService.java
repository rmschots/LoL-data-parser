package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.RankingDao;
import com.sneaky.peaky.dataparser.domain.mapper.RankingRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.SPRanking;
import com.sneaky.peaky.dataparser.jpa.mapper.RankingJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.JPARanking;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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
        RankingRestMapper rankingRestMapper = new RankingRestMapper();
        RankingJPAToSPMapper rankingJPAToSPMapper = new RankingJPAToSPMapper();
        RankingDao rankingDao = new RankingDao();
        long revisionDate = System.currentTimeMillis();
        LeagueList leagueList = handler.getChallenger(queueType).get();        
        for (LeagueItem leagueItem : leagueList.getEntries()) {
            leagueItem.setQueueType(queueType.name());
            leagueItem.setTier(LeagueTier.CHALLENGER.name());
            SPRanking sPRanking = rankingRestMapper.mapToSP(leagueItem);
            JPARanking jPARanking = rankingJPAToSPMapper.mapToJPA(sPRanking, revisionDate);
            rankingDao.create(jPARanking);
        }
    }
}
