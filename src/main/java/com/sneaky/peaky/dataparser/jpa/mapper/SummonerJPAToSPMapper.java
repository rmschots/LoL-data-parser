package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;
import net.boreeas.riotapi.rest.Summoner;

/**
 *
 * @author Roel
 */
public class SummonerJPAToSPMapper implements BidirectionalJPAToSPMapper<JPASummoner, SPSummoner>{

    @Override
    public JPASummoner mapToJPA(SPSummoner summoner) {
        return JPASummoner.builder()
                .id(summoner.getId())
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
                .id(jpaSummoner.getId())
                .name(jpaSummoner.getName())
                .profileIconId(jpaSummoner.getProfileIconId())
                .revisionDate(jpaSummoner.getRevisionDate())
                .summonerId(jpaSummoner.getSummonerId())
                .summonerLevel(jpaSummoner.getSummonerLevel())
                .build();
    }
    
}
