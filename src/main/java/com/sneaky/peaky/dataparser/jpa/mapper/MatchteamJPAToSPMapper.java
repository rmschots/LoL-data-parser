package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPMatchteam;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAMatchteam;
import java.util.Map;

/**
 *
 * @author Roel Mangelschots
 */
public class MatchteamJPAToSPMapper implements BidirectionalJPAToSPMapper<JPAMatchteam, SPMatchteam>{

    @Override
    public JPAMatchteam mapToJPA(SPMatchteam spObject) {
        return createBasic(spObject).build();
    }
    
    public JPAMatchteam mapToJPA(SPMatchteam spObject, Map<Integer, JPAChampion> championMap) {
        return createBasic(spObject)
                .bannedChampion1(championMap.get(spObject.getBannedChampion1().getId()))
                .bannedChampion2(championMap.get(spObject.getBannedChampion2().getId()))
                .bannedChampion3(championMap.get(spObject.getBannedChampion3().getId()))
                .build();
    }

    @Override
    public SPMatchteam mapToDomain(JPAMatchteam jpaObject) {
        ChampionJPAToSPMapper championJPAToSPMapper = new ChampionJPAToSPMapper();
        return SPMatchteam.builder()
                .bannedChampion1(championJPAToSPMapper.mapToDomain(jpaObject.getBannedChampion1()))
                .bannedChampion2(championJPAToSPMapper.mapToDomain(jpaObject.getBannedChampion2()))
                .bannedChampion3(championJPAToSPMapper.mapToDomain(jpaObject.getBannedChampion3()))
                .baronKills(jpaObject.getBaronKills())
                .dominionVictoryScore(jpaObject.getDominionVictoryScore())
                .dragonKills(jpaObject.getDragonKills())
                .firstBaron(jpaObject.getFirstBaron())
                .firstBlood(jpaObject.getFirstBlood())
                .firstDragon(jpaObject.getFirstDragon())
                .firstInhibitor(jpaObject.getFirstInhibitor())
                .firstRiftHerald(jpaObject.getFirstRiftHerald())
                .firstTower(jpaObject.getFirstTower())
                .inhibitorKills(jpaObject.getInhibitorKills())
                .riftHeraldKills(jpaObject.getRiftHeraldKills())
                .teamId(jpaObject.getTeamId())
                .towerKills(jpaObject.getTowerKills())
                .vilemawKills(jpaObject.getVilemawKills())
                .winner(jpaObject.getWinner())
                .build();
    }

    private JPAMatchteam.JPAMatchteamBuilder createBasic(SPMatchteam spObject) {
        return JPAMatchteam.builder()
                .baronKills(spObject.getBaronKills())
                .dominionVictoryScore(spObject.getDominionVictoryScore())
                .dragonKills(spObject.getDragonKills())
                .firstBaron(spObject.getFirstBaron())
                .firstBlood(spObject.getFirstBlood())
                .firstDragon(spObject.getFirstDragon())
                .firstInhibitor(spObject.getFirstInhibitor())
                .firstRiftHerald(spObject.getFirstRiftHerald())
                .firstTower(spObject.getFirstTower())
                .inhibitorKills(spObject.getInhibitorKills())
                .riftHeraldKills(spObject.getRiftHeraldKills())
                .teamId(spObject.getTeamId())
                .towerKills(spObject.getTowerKills())
                .vilemawKills(spObject.getVilemawKills())
                .winner(spObject.getWinner());
    }

}
