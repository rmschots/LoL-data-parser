package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPMastery;
import com.sneaky.peaky.dataparser.domain.pojo.SPMatch;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPRune;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.boreeas.riotapi.rest.MatchDetail;
import net.boreeas.riotapi.rest.Participant;
import net.boreeas.riotapi.rest.ParticipantIdentity;
import org.javatuples.Pair;

/**
 *
 * @author Roel Mangelschots
 */
public class MatchRestMapper implements RestMapper<SPMatch, MatchDetail>{

    @Override
    public SPMatch mapToSP(MatchDetail restObject) {
        return createBasic(restObject).build();
    }
    
    public SPMatch mapToSP(MatchDetail restObject, Map<Integer, SPChampion> championMap, Map<Integer, SPMastery> masteryMap, Map<Integer, SPRune> runeMap) {
        MatchteamRestMapper matchteamRestMapper = new MatchteamRestMapper();
        Set<Pair<ParticipantIdentity,Participant>> pPairs = new HashSet<>();
        Map<Integer, Participant> pMap = restObject.getParticipants().stream().collect(Collectors.toMap(Participant::getParticipantId, Function.identity()));
        restObject.getParticipantIdentities().stream().forEach((identity) -> {
            pPairs.add(Pair.with(identity, pMap.get(identity.getParticipantId())));
        });
        ParticipantRestMapper participantRestMapper = new ParticipantRestMapper();
        return createBasic(restObject)
                .team1(matchteamRestMapper.mapToSP(restObject.getTeams().get(0), championMap))
                .team2(matchteamRestMapper.mapToSP(restObject.getTeams().get(1), championMap))
                .participants(participantRestMapper.mapToSP(pPairs, championMap, masteryMap, runeMap))
                .build();
    }
    
    private SPMatch.SPMatchBuilder createBasic(MatchDetail restObject){
        return SPMatch.builder()
                .id(restObject.getMatchId())
                .matchCreation(restObject.getMatchCreation().getTime())
                .matchDuration(restObject.getMatchDuration())
                .matchVersion(restObject.getMatchVersion())
                .queueType(restObject.getQueue())
                .region(restObject.getRegion())
                .season(restObject.getSeason());
    }
    
    
    
    
}
