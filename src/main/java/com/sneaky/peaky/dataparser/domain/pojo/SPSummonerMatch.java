package com.sneaky.peaky.dataparser.domain.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
import net.boreeas.riotapi.constants.Lane;
import net.boreeas.riotapi.constants.Role;
import net.boreeas.riotapi.constants.Season;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
public class SPSummonerMatch extends AbstractSPObject{
    private Long id;
    private Long summonerId;
    
    private Long startTime;
    private SPChampion champion;
    private String region;
    private QueueType queue;
    private Season season;
    private Role role;
    private Lane lane;
}
