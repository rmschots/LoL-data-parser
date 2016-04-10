package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.EntityManagerManager;
import com.sneaky.peaky.dataparser.dao.RankingDao;
import com.sneaky.peaky.dataparser.dao.TeamDao;
import com.sneaky.peaky.dataparser.domain.mapper.TeamRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.SPTeam;
import com.sneaky.peaky.dataparser.jpa.mapper.TeamJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.JPATeam;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import net.boreeas.riotapi.rest.RankedTeam;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel
 */
public class TeamService {

    public void fetchAllTeams(ThrottledApiHandler handler) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        
        RankingDao rankingDao = new RankingDao(em);
        List<String> sString = rankingDao.findAllTeamIds();
        String[][] summIds = new String[(int) Math.ceil(sString.size() / 10.0)][];
        for (int i = 0; i < sString.size(); i++) {
            if (i % 10 == 0) {
                if (i + 10 > sString.size()) {
                    summIds[i / 10] = new String[10];
                } else {
                    summIds[i / 10] = new String[Math.min(10, sString.size() - i)];
                }
            }
            summIds[i / 10][i % 10] = sString.get(i);
        }

        TeamRestMapper restMapper = new TeamRestMapper();
        TeamJPAToSPMapper jpaMapper = new TeamJPAToSPMapper();
        TeamDao teamDao = new TeamDao(em);

        for (String[] ids : summIds) {
            Map<String, RankedTeam> summonerMap = handler.getTeams(ids).get();

            for (RankedTeam summoner : summonerMap.values()) {
                SPTeam spTeam = restMapper.mapToSP(summoner);
                JPATeam jpaTeam = jpaMapper.mapToJPA(spTeam);
                teamDao.create(jpaTeam);
            }
        }
        
        EntityManagerManager.closeSession(em);
    }

    public void fetchTeam(ThrottledApiHandler handler, String teamId) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        
        TeamRestMapper restMapper = new TeamRestMapper();
        TeamJPAToSPMapper jpaMapper = new TeamJPAToSPMapper();
        TeamDao teamDao = new TeamDao(em);

        RankedTeam team = handler.getTeam(teamId).get();
        SPTeam spTeam = restMapper.mapToSP(team);
        JPATeam jpaTeam = jpaMapper.mapToJPA(spTeam);
        teamDao.create(jpaTeam);
        
        EntityManagerManager.closeSession(em);
    }
}
