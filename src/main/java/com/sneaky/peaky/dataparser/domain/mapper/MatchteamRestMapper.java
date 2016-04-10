package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.SPMatchteam;
import java.util.Map;
import net.boreeas.riotapi.com.riotgames.platform.game.BannedChampion;
import net.boreeas.riotapi.rest.PostMatchTeamOverview;

/**
 *
 * @author Roel Mangelschots
 */
public class MatchteamRestMapper implements RestMapper<SPMatchteam, PostMatchTeamOverview>{

    @Override
    public SPMatchteam mapToSP(PostMatchTeamOverview restObject) {
        return createBasic(restObject)
                .build();
    }
    
    public SPMatchteam mapToSP(PostMatchTeamOverview restObject, Map<Integer, SPChampion> championMap) {
        SPMatchteam.SPMatchteamBuilder builder = createBasic(restObject);
        for(BannedChampion bs : restObject.getBans()){
            switch(bs.getPickTurn()){
                case 1:
                case 2:
                    builder.bannedChampion1(championMap.get(bs.getChampionId()));
                    break;
                case 3:
                case 4:
                    builder.bannedChampion2(championMap.get(bs.getChampionId()));
                    break;
                case 5:
                case 6:
                    builder.bannedChampion3(championMap.get(bs.getChampionId()));
                    break;
            }
        }
        return builder.build();
    }
    
    private SPMatchteam.SPMatchteamBuilder createBasic(PostMatchTeamOverview restObject){
        return SPMatchteam.builder()
                .baronKills(restObject.getBaronKills())
                .dominionVictoryScore(restObject.getDominionVictoryScore())
                .dragonKills(restObject.getDragonKills())
                .firstBaron(restObject.isFirstBaron())
                .firstBlood(restObject.isFirstBlood())
                .firstDragon(restObject.isFirstDragon())
                .firstInhibitor(restObject.isFirstInhibitor())
                .firstRiftHerald(restObject.isFirstRiftHerald())
                .firstTower(restObject.isFirstTower())
                .inhibitorKills(restObject.getInhibitorKills())
                .riftHeraldKills(restObject.getRiftHeraldKills())
                .teamId(restObject.getTeamId())
                .towerKills(restObject.getTowerKills())
                .vilemawKills(restObject.getVilemawKills())
                .winner(restObject.isWinner());
    }
    
}
