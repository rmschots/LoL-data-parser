package com.sneaky.peaky.dataparser.domain.pojo;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
public class SPMatchteam extends AbstractSPObject{
    private SPChampion bannedChampion1;
    private SPChampion bannedChampion2;
    private SPChampion bannedChampion3;
    
    private Integer baronKills;
    private Long dominionVictoryScore;
    private Integer dragonKills;
    private Boolean firstBaron;
    private Boolean firstBlood;
    private Boolean firstDragon;
    private Boolean firstInhibitor;
    private Boolean firstRiftHerald;
    private Boolean firstTower;
    private Integer inhibitorKills;
    private Integer riftHeraldKills;
    private Integer teamId;
    private Integer towerKills;
    private Integer vilemawKills;
    private Boolean winner;
}
