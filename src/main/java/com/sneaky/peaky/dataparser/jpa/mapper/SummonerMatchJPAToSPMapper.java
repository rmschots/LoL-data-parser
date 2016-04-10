package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummonerMatch;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummonerMatch;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchJPAToSPMapper implements BidirectionalJPAToSPMapper<JPASummonerMatch, SPSummonerMatch>, CollectionBiDirectionalJPAToSPMapper<Set<JPASummonerMatch>, Set<SPSummonerMatch>> {

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
    public Set<JPASummonerMatch> mapToJPA(Set<SPSummonerMatch> spCollection) {
        Set<JPASummonerMatch> resultList = new HashSet<>();
        spCollection.stream().forEach((spMatch) -> {
            resultList.add(mapToJPA(spMatch));
        });
        return resultList;
    }
    
    public Set<JPASummonerMatch> mapToJPA(Set<SPSummonerMatch> spCollection, Map<Integer, JPAChampion> jpaChampMap) {
        Set<JPASummonerMatch> resultList = new HashSet<>();
        spCollection.stream().forEach((spMatch) -> {
            resultList.add(mapToJPA(spMatch, jpaChampMap));
        });
        return resultList;
    }

    @Override
    public Set<SPSummonerMatch> mapToSP(Set<JPASummonerMatch> jpaCollection) {
        Set<SPSummonerMatch> resultList = new HashSet<>();
        jpaCollection.stream().forEach((jpaMatch) -> {
            resultList.add(mapToDomain(jpaMatch));
        }); 
        return resultList;
    }

}