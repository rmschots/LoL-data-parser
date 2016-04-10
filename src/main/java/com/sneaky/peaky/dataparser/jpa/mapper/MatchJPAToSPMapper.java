package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPMatch;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAMastery;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAMatch;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPARune;
import java.util.Map;

/**
 *
 * @author Roel Mangelschots
 */
public class MatchJPAToSPMapper implements BidirectionalJPAToSPMapper<JPAMatch, SPMatch>{

    @Override
    public JPAMatch mapToJPA(SPMatch jpaObject) {
        return createBasic(jpaObject)
                .build();
    }
    
    public JPAMatch mapToJPA(SPMatch jpaObject, Map<Integer, JPAChampion> championMap, Map<Integer, JPAMastery> masteryMap, Map<Integer, JPARune> runeMap) {
        ParticipantJPAToSPMapper participantJPAToSPMapper = new ParticipantJPAToSPMapper();
        MatchteamJPAToSPMapper matchteamJPAToSPMapper = new MatchteamJPAToSPMapper();
        return createBasic(jpaObject)
                .participants(participantJPAToSPMapper.mapToJPA(jpaObject.getParticipants(), championMap, masteryMap, runeMap))
                .team1(matchteamJPAToSPMapper.mapToJPA(jpaObject.getTeam1(), championMap))
                .team2(matchteamJPAToSPMapper.mapToJPA(jpaObject.getTeam2(), championMap))
                .build();
    }
    
    private JPAMatch.JPAMatchBuilder createBasic(SPMatch jpaObject){
        return JPAMatch.builder()
                .id(jpaObject.getId())
                .matchCreation(jpaObject.getMatchCreation())
                .matchDuration(jpaObject.getMatchDuration())
                .matchVersion(jpaObject.getMatchVersion())
                .queueType(jpaObject.getQueueType())
                .region(jpaObject.getRegion())
                .season(jpaObject.getSeason());
    }

    @Override
    public SPMatch mapToDomain(JPAMatch jpaObject) {
        ParticipantJPAToSPMapper participantJPAToSPMapper = new ParticipantJPAToSPMapper();
        MatchteamJPAToSPMapper matchteamJPAToSPMapper = new MatchteamJPAToSPMapper();
        return SPMatch.builder()
                .id(jpaObject.getId())
                .matchCreation(jpaObject.getMatchCreation())
                .matchDuration(jpaObject.getMatchDuration())
                .matchVersion(jpaObject.getMatchVersion())
                .participants(participantJPAToSPMapper.mapToSP(jpaObject.getParticipants()))
                .queueType(jpaObject.getQueueType())
                .region(jpaObject.getRegion())
                .season(jpaObject.getSeason())
                .team1(matchteamJPAToSPMapper.mapToDomain(jpaObject.getTeam1()))
                .team2(matchteamJPAToSPMapper.mapToDomain(jpaObject.getTeam2()))
                .build();
    }
    
}
