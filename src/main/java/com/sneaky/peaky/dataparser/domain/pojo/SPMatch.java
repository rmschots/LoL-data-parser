package com.sneaky.peaky.dataparser.domain.pojo;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
import net.boreeas.riotapi.constants.Season;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
public class SPMatch extends AbstractSPObject{
    private Long id;
    
    private Long matchCreation;
    private Long matchDuration;
    private String matchVersion;
    private Set<SPParticipant> participants;
    private QueueType queueType;
    private String region;
    private Season season;
    private SPMatchteam team1;
    private SPMatchteam team2;
}
