package com.sneaky.peaky.dataparser;

import com.sneaky.peaky.dataparser.dao.TeamDao;
import com.sneaky.peaky.dataparser.jpa.mapper.SummonerJPAToSPMapper;
import com.sneaky.peaky.dataparser.domain.mapper.SummonerRestMapper;
import com.sneaky.peaky.dataparser.service.RankingService;
import com.sneaky.peaky.dataparser.service.SummonerService;
import com.sneaky.peaky.dataparser.service.TeamService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import net.boreeas.riotapi.Shard;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
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

    public static void main(String[] args) throws InterruptedException, ExecutionException, Exception {
        Main m = new Main();
        m.test();
    }
    
    private final RankingService rankingService = new RankingService();
    private final SummonerService summonerService = new SummonerService();
    private final TeamService teamService = new TeamService();

    public void test() throws InterruptedException, ExecutionException, Exception {
        Limit limit1 = new Limit(10, 10, TimeUnit.SECONDS);
        Limit limit2 = new Limit(500, 10, TimeUnit.MINUTES);
        ThrottledApiHandler handler = new ThrottledApiHandler(Shard.EUW, API_KEY, limit1, limit2);
        
//        RankedTeam team = handler.getTeam("TEAM-f07ed190-f0d9-11e4-b090-c81f66db96d8").get();
//        System.out.println(team.getName());
//        Summoner summ = handler.getSummoner("Morsu").get();
//        System.out.println(summ.getId());
        
//        summ = handler.getSummoner("Je suis kaas").get();
//        System.out.println(summ.getId());
        
//        rankingService.fetchChallengerRanks(handler, QueueType.RANKED_SOLO_5x5);
        summonerService.fetchAllSummoners(handler);
        
//        rankingService.fetchChallengerRanks(handler, QueueType.RANKED_TEAM_5x5);
//        teamService.fetchAllTeams(handler);
//        RankingDao rankingDao = new RankingDao();
//        
//        List<JPARanking> rankings = rankingDao.findAll();
//        for(JPARanking jPARanking : rankings){
//            System.out.println(jPARanking.getPlayerOrTeamName());
//        }
        
//        List<String> teamIds = rankingDao.findAllTeamIds();
//        for(String s : teamIds){
//            System.out.println(s);
//        }

//        Summoner summoner = handler.getSummoner("Je suis kaas").get();
//        SPSummoner sPSummoner = summonerRestMapper.mapToSP(summoner);
//
//        SummonerDao summonerDao = new SummonerDao();
//        JPASummoner found = summonerDao.find(sPSummoner.getSummonerId(), sPSummoner.getRevisionDate());
//        if (found == null) {
//            JPASummoner jPASummoner = summonerJPAToSPMapper.mapToJPA(sPSummoner);
//            summonerDao.create(jPASummoner);
//        }
//        List<JPASummoner> jPASummoners = summonerDao.findAll();
//        jPASummoners.stream().forEach((jpas) -> {
//            System.out.println(jpas);
//        });

        System.exit(0);
    }
}
