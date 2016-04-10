package com.sneaky.peaky.dataparser.domain.pojo;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPMastery;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPRune;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import net.boreeas.riotapi.constants.LeagueTier;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
public class SPParticipant extends AbstractSPObject{
    private Long summonerId;
    private SPChampion champion;
    private LeagueTier highestAchievedSeasonTier;
    private Set<SPMastery> masteries;
    private Integer participantId;
    private Set<SPRune> runes;
    private Integer spell1Id;
    private Integer spell2Id;
    private SPParticipantStats stats;
    private Integer teamId;
}
