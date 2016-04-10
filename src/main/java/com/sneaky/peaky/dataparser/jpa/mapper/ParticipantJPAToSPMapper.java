package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPParticipant;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.MasteryJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.RuneJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAMastery;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAParticipant;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPARune;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Roel Mangelschots
 */
public class ParticipantJPAToSPMapper implements BidirectionalJPAToSPMapper<JPAParticipant, SPParticipant>, CollectionBiDirectionalJPAToSPMapper<Set<JPAParticipant>, Set<SPParticipant>> {

    @Override
    public JPAParticipant mapToJPA(SPParticipant spObject) {
        return createBasic(spObject).build();
    }
    
    public JPAParticipant mapToJPA(SPParticipant spObject, Map<Integer, JPAChampion> championMap, Map<Integer, JPAMastery> masteryMap, Map<Integer, JPARune> runeMap) {
        Set<JPAMastery> masteries = new HashSet<>();
        spObject.getMasteries().stream().forEach((spm) -> {
            masteries.add(masteryMap.get(spm.getId()));
        });
        Set<JPARune> runes = new HashSet<>();
        spObject.getRunes().stream().forEach((spr) -> {
            runes.add(runeMap.get(spr.getId()));
        });
        return createBasic(spObject)
                .champion(championMap.get(spObject.getChampion().getId()))
                .masteries(masteries)
                .runes(runes)
                .build();
    }

    @Override
    public SPParticipant mapToDomain(JPAParticipant jpaObject) {
        ParticipantStatsJPAToSPMapper participantStatsJPAToSPMapper = new ParticipantStatsJPAToSPMapper();
        ChampionJPAToSPMapper championJPAToSPMapper = new ChampionJPAToSPMapper();
        MasteryJPAToSPMapper masteryJPAToSPMapper = new MasteryJPAToSPMapper();
        RuneJPAToSPMapper runeJPAToSPMapper = new RuneJPAToSPMapper();
        return SPParticipant.builder()
                .summonerId(jpaObject.getSummonerId())
                .champion(championJPAToSPMapper.mapToDomain(jpaObject.getChampion()))
                .highestAchievedSeasonTier(jpaObject.getHighestAchievedSeasonTier())
                .masteries(masteryJPAToSPMapper.mapToSP(jpaObject.getMasteries()))
                .participantId(jpaObject.getParticipantId())
                .runes(runeJPAToSPMapper.mapToSP(jpaObject.getRunes()))
                .spell1Id(jpaObject.getSpell1Id())
                .spell2Id(jpaObject.getSpell2Id())
                .stats(participantStatsJPAToSPMapper.mapToDomain(jpaObject.getStats()))
                .teamId(jpaObject.getTeamId())
                .build();
    }
    
    private JPAParticipant.JPAParticipantBuilder createBasic(SPParticipant spObject){
        ParticipantStatsJPAToSPMapper participantStatsJPAToSPMapper = new ParticipantStatsJPAToSPMapper();
        return JPAParticipant.builder()
                .summonerId(spObject.getSummonerId())
                .highestAchievedSeasonTier(spObject.getHighestAchievedSeasonTier())
                .participantId(spObject.getParticipantId())
                .spell1Id(spObject.getSpell1Id())
                .spell2Id(spObject.getSpell2Id())
                .stats(participantStatsJPAToSPMapper.mapToJPA(spObject.getStats()))
                .teamId(spObject.getTeamId());
    }

    @Override
    public Set<JPAParticipant> mapToJPA(Set<SPParticipant> spCollection) {
        Set<JPAParticipant> set = new HashSet<>();
        spCollection.stream().forEach((spp) -> {
            set.add(mapToJPA(spp));
        });
        return set;
    }
    
    public Set<JPAParticipant> mapToJPA(Set<SPParticipant> spCollection, Map<Integer, JPAChampion> championMap, Map<Integer, JPAMastery> masteryMap, Map<Integer, JPARune> runeMap) {
        Set<JPAParticipant> set = new HashSet<>();
        spCollection.stream().forEach((spp) -> {
            set.add(mapToJPA(spp, championMap, masteryMap, runeMap));
        });
        return set;
    }

    @Override
    public Set<SPParticipant> mapToSP(Set<JPAParticipant> jpaCollection) {
        Set<SPParticipant> set = new HashSet<>();
        jpaCollection.stream().forEach((jpap) -> {
            set.add(mapToDomain(jpap));
        });
        return set;
    }

}
