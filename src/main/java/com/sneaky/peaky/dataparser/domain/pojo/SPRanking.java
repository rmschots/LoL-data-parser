package com.sneaky.peaky.dataparser.domain.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 *
 * @author Roel
 */
@Getter
@Setter
@Builder
public class SPRanking extends AbstractSPObject{
    private Boolean isFreshBlood;
    private Boolean isHotStreak;
    private Boolean isInactive;
    private Boolean isVeteran;
    private Long lastPlayed;
    private String leagueName;
    private Integer leaguePoints;
    private SPMiniSeries miniSeries;
    private String playerOrTeamId;
    private Boolean isTeam;
    private String playerOrTeamName;
    private String queueType;
    private Integer rank;
    private String tier;
    private Integer wins;
    private Long revisionDate;
}
