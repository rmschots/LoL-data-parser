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
    private boolean isFreshBlood;
    private boolean isHotStreak;
    private boolean isInactive;
    private boolean isVeteran;
    private long lastPlayed;
    private String leagueName;
    private int leaguePoints;
    private SPMiniSeries miniSeries;
    private String playerOrTeamId;
    private String playerOrTeamName;
    private String queueType;
    private int rank;
    private String tier;
    private int wins;
}
