package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPMastery;
import com.sneaky.peaky.dataparser.domain.pojo.SPParticipant;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPRune;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.boreeas.riotapi.rest.Participant;
import net.boreeas.riotapi.rest.ParticipantIdentity;
import net.boreeas.riotapi.rest.ParticipantMastery;
import net.boreeas.riotapi.rest.Rune;
import org.javatuples.Pair;

/**
 *
 * @author Roel Mangelschots
 */
public class ParticipantRestMapper implements RestMapper<SPParticipant, Pair<ParticipantIdentity, Participant>>, CollectionRestMapper<Set<SPParticipant>, Set<Pair<ParticipantIdentity, Participant>>>{

    @Override
    public SPParticipant mapToSP(Pair<ParticipantIdentity, Participant> restObject) {
        return createBasic(restObject).build();
    }
    
    public SPParticipant mapToSP(Pair<ParticipantIdentity, Participant> restObject, Map<Integer, SPChampion> championMap, Map<Integer, SPMastery> masteryMap, Map<Integer, SPRune> runeMap) {
        Participant participant = restObject.getValue1();
        Set<SPMastery> masteries = new HashSet<>();
        participant.getMasteries().stream().forEach((m) -> {
            masteries.add(masteryMap.get(m.getMasteryId()));
        });
        Set<SPRune> runes = new HashSet<>();
        participant.getRunes().stream().forEach((rune) -> {
            runes.add(runeMap.get(rune.getRuneId()));
        });
        return createBasic(restObject)
                .champion(championMap.get(participant.getChampionId()))
                .masteries(masteries)
                .runes(runes)
                .build();
    }
    
    public SPParticipant.SPParticipantBuilder createBasic(Pair<ParticipantIdentity, Participant> restObject) {
        ParticipantStatsRestMapper participantStatsRestMapper = new ParticipantStatsRestMapper();
        ParticipantIdentity participantIdentity = restObject.getValue0();
        Participant participant = restObject.getValue1();
        return SPParticipant.builder()
                .summonerId(participantIdentity.getPlayer().getSummonerId())
                .highestAchievedSeasonTier(participant.getHighestAchievedSeasonTier())
                .participantId(participant.getParticipantId())
                .spell1Id(participant.getSpell1Id())
                .spell2Id(participant.getSpell2Id())
                .stats(participantStatsRestMapper.mapToSP(participant.getStats()))
                .teamId(participant.getTeamId());
    }

    @Override
    public Set<SPParticipant> mapToSP(Set<Pair<ParticipantIdentity, Participant>> restObject) {
        Set<SPParticipant> participants = new HashSet<>();
        for(Pair<ParticipantIdentity, Participant> pPair : restObject){
            participants.add(mapToSP(pPair));
        }
        return participants;
    }
    
    public Set<SPParticipant> mapToSP(Set<Pair<ParticipantIdentity, Participant>> restObject, Map<Integer, SPChampion> championMap, Map<Integer, SPMastery> masteryMap, Map<Integer, SPRune> runeMap) {
        Set<SPParticipant> participants = new HashSet<>();
        for(Pair<ParticipantIdentity, Participant> pPair : restObject){
            participants.add(mapToSP(pPair, championMap, masteryMap, runeMap));
        }
        return participants;
    }
    
}
