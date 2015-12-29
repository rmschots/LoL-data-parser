package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import net.boreeas.riotapi.rest.Summoner;

/**
 *
 * @author Roel
 */
public class SummonerRestMapper implements RestMapper<SPSummoner, Summoner>{

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
    
}
