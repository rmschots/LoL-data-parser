package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roel
 */
public class SummonerJPAToSPMapper implements BidirectionalJPAToSPMapper<JPASummoner, SPSummoner>, ListBiDirectionalJPAToSPMapper<List<JPASummoner>,List<SPSummoner>>{

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

    @Override
    public List<JPASummoner> mapToJPA(List<SPSummoner> spCollection) {
        ArrayList<JPASummoner> resultList = new ArrayList<>();
        for(SPSummoner sPSummoner : spCollection){
            resultList.add(mapToJPA(sPSummoner));
        }
        return resultList;
    }

    @Override
    public List<SPSummoner> mapToSP(List<JPASummoner> jpaCollection) {
        ArrayList<SPSummoner> resultList = new ArrayList<>();
        for(JPASummoner jPASummoner : jpaCollection){
            resultList.add(mapToDomain(jPASummoner));
        } 
        return resultList;
    }
    
}
