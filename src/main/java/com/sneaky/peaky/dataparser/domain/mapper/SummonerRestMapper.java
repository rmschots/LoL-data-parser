package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import java.util.ArrayList;
import java.util.List;
import net.boreeas.riotapi.com.riotgames.team.dto.TeamMemberInfo;
import net.boreeas.riotapi.rest.Summoner;

/**
 *
 * @author Roel
 */
public class SummonerRestMapper implements RestMapper<SPSummoner, Summoner>, CollectionRestMapper<List<SPSummoner>, List<TeamMemberInfo>>{

    @Override
    public SPSummoner mapToSP(Summoner summoner) {
        return SPSummoner.builder()
                .summonerId(summoner.getId())
                .name(summoner.getName())
                .profileIconId(summoner.getProfileIconId())
                .revisionDate(summoner.getRevisionDate())
                .summonerLevel(summoner.getSummonerLevel())
                .build();
    }

    @Override
    public List<SPSummoner> mapToSP(List<TeamMemberInfo> restObject) {
        List<SPSummoner> resultList = new ArrayList<>();
        for(TeamMemberInfo info : restObject){
            SPSummoner sps = SPSummoner.builder()
                    .name(info.getPlayerName())
                    .summonerId(info.getPlayerId())
                    .build();
            resultList.add(sps);
        }
        return resultList;
    }

    
}
