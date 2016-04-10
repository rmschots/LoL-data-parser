package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummonerMatch;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummonerMatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchJPAToSPMapper implements BidirectionalJPAToSPMapper<JPASummonerMatch, SPSummonerMatch>, ListBiDirectionalJPAToSPMapper<List<JPASummonerMatch>, List<SPSummonerMatch>> {

    @Override
    public JPASummonerMatch mapToJPA(SPSummonerMatch spObject) {
        return createBasicJPA(spObject)
                .build();
    }
    
    public JPASummonerMatch mapToJPA(SPSummonerMatch spObject, Map<Integer, JPAChampion> jpaChampMap) {
        return createBasicJPA(spObject)
                .champion(jpaChampMap.get(spObject.getChampion().getId()))
                .build();
    }
    
    private JPASummonerMatch.JPASummonerMatchBuilder createBasicJPA(SPSummonerMatch spObject){
        return JPASummonerMatch.builder()
                .id(spObject.getId())
                .summonerId(spObject.getSummonerId())
                .startTime(spObject.getStartTime())
                .region(spObject.getRegion())
                .queue(spObject.getQueue())
                .season(spObject.getSeason())
                .gameRole(spObject.getRole())
                .lane(spObject.getLane());
    }

    @Override
    public SPSummonerMatch mapToDomain(JPASummonerMatch jpaObject) {
        ChampionJPAToSPMapper mapper = new ChampionJPAToSPMapper();
        return SPSummonerMatch.builder()
                .id(jpaObject.getId())
                .summonerId(jpaObject.getSummonerId())
                .startTime(jpaObject.getStartTime())
                .champion(mapper.mapToDomain(jpaObject.getChampion()))
                .region(jpaObject.getRegion())
                .queue(jpaObject.getQueue())
                .season(jpaObject.getSeason())
                .role(jpaObject.getGameRole())
                .lane(jpaObject.getLane())
                .build();
    }

    @Override
    public List<JPASummonerMatch> mapToJPA(List<SPSummonerMatch> spCollection) {
        ArrayList<JPASummonerMatch> resultList = new ArrayList<>();
        for(SPSummonerMatch spMatch : spCollection){
            resultList.add(mapToJPA(spMatch));
        }
        return resultList;
    }
    
    public List<JPASummonerMatch> mapToJPA(List<SPSummonerMatch> spCollection, Map<Integer, JPAChampion> jpaChampMap) {
        ArrayList<JPASummonerMatch> resultList = new ArrayList<>();
        for(SPSummonerMatch spMatch : spCollection){
            resultList.add(mapToJPA(spMatch, jpaChampMap));
        }
        return resultList;
    }

    @Override
    public List<SPSummonerMatch> mapToSP(List<JPASummonerMatch> jpaCollection) {
        ArrayList<SPSummonerMatch> resultList = new ArrayList<>();
        for(JPASummonerMatch jpaMatch : jpaCollection){
            resultList.add(mapToDomain(jpaMatch));
        } 
        return resultList;
    }

}