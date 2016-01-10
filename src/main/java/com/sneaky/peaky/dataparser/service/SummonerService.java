/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.RankingDao;
import com.sneaky.peaky.dataparser.dao.SummonerDao;
import com.sneaky.peaky.dataparser.dao.TeamDao;
import com.sneaky.peaky.dataparser.domain.mapper.SummonerRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import com.sneaky.peaky.dataparser.jpa.mapper.SummonerJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import net.boreeas.riotapi.rest.Summoner;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel
 */
public class SummonerService {

    public void fetchAllSummoners(ThrottledApiHandler handler) throws InterruptedException, ExecutionException {
        RankingDao rankingDao = new RankingDao();
        TeamDao teamDao = new TeamDao();

        Set<Long> rankedIds = rankingDao.findAllSummonerIds();
        rankedIds.addAll(teamDao.findAllSummonersOfTeams());
        ArrayList<Long> idList = new ArrayList<>(rankedIds);

        Integer[][] summIds = new Integer[(int) Math.ceil(idList.size() / 40.0)][];
        for (int i = 0; i < idList.size(); i++) {
            if (i % 40 == 0) {
                if (i + 40 < idList.size()) {
                    summIds[i / 40] = new Integer[40];
                } else {
                    summIds[i / 40] = new Integer[Math.min(40, idList.size() - i)];
                }
            }
            summIds[i / 40][i % 40] = Math.toIntExact(idList.get(i));
        }

        SummonerRestMapper restMapper = new SummonerRestMapper();
        SummonerJPAToSPMapper jpaMapper = new SummonerJPAToSPMapper();
        SummonerDao summonerDao = new SummonerDao();

        for (Integer[] ids : summIds) {
            Map<Integer, Summoner> summonerMap = handler.getSummoners(ids).get();

            for (Summoner summoner : summonerMap.values()) {
                if (summonerDao.findBySummonerId(summoner.getId(), summoner.getRevisionDate()) == null) {
                    SPSummoner sPSummoner = restMapper.mapToSP(summoner);
                    JPASummoner jPASummoner = jpaMapper.mapToJPA(sPSummoner);
                    summonerDao.create(jPASummoner);
                }
            }
        }
    }

    public void fetchSummoner(ThrottledApiHandler handler, Long summonerId) throws InterruptedException, ExecutionException {
        SummonerRestMapper restMapper = new SummonerRestMapper();
        SummonerJPAToSPMapper jpaMapper = new SummonerJPAToSPMapper();
        SummonerDao summonerDao = new SummonerDao();

        Summoner summoner = handler.getSummoner(summonerId.toString()).get();
        SPSummoner sPSummoner = restMapper.mapToSP(summoner);
        JPASummoner jPASummoner = jpaMapper.mapToJPA(sPSummoner);
        summonerDao.create(jPASummoner);
    }
}