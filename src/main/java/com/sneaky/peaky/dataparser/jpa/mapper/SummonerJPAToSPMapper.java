package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;

/**
 *
 * @author Roel
 */
public class SummonerJPAToSPMapper implements BidirectionalJPAToSPMapper<JPASummoner, SPSummoner>{

    @Override
    public JPASummoner mapToJPA(SPSummoner summoner) {
        return JPASummoner.builder()
                .summonerId(summoner.getSummonerId())
                .name(summoner.getName())
                .profileIconId(summoner.getProfileIconId())
                .revisionDate(summoner.getRevisionDate())
                .summonerLevel(summoner.getSummonerLevel())
                .build();
    }

    @Override
    public SPSummoner mapToDomain(JPASummoner jpaSummoner) {
        return SPSummoner.builder()
                .name(jpaSummoner.getName())
                .profileIconId(jpaSummoner.getProfileIconId())
                .revisionDate(jpaSummoner.getRevisionDate())
                .summonerId(jpaSummoner.getSummonerId())
                .summonerLevel(jpaSummoner.getSummonerLevel())
                .build();
    }
    
}
