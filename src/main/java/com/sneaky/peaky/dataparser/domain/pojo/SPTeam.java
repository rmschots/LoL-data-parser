package com.sneaky.peaky.dataparser.domain.pojo;

import java.util.List;
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
public class SPTeam extends AbstractSPObject{
    private Long createDate;
    private String fullId;
    private Long lastGameDate;
    private Long lastJoinDate;
    private Long lastJoinedRankedTeamQueueDate;
    //private List<MatchHistorySummary> matchHistory = new ArrayList<>();
    //private MessageOfDay messageOfDay;
    private Long modifyDate;
    private String name;
    
    private List<SPSummoner> roster;
    private Long secondLastJoinDate;
    private String status;
    private String tag;
    //private TeamStatSummary teamStatSummary;
    private Long thirdLastJoinDate;
}
