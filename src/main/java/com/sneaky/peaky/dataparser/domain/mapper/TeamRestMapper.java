package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import com.sneaky.peaky.dataparser.domain.pojo.SPTeam;
import java.util.List;
import net.boreeas.riotapi.rest.RankedTeam;

/**
 *
 * @author Roel Mangelschots
 */
public class TeamRestMapper implements RestMapper<SPTeam, RankedTeam>{
    
    @Override
    public SPTeam mapToSP(RankedTeam rankedTeam) {
        SummonerRestMapper summonerRestMapper = new SummonerRestMapper();
        return SPTeam.builder()
                .createDate(rankedTeam.getCreateDate())
                .fullId(rankedTeam.getFullId())
                .lastGameDate(rankedTeam.getLastGameDate())
                .lastJoinDate(rankedTeam.getLastJoinDate())
                .lastJoinedRankedTeamQueueDate(rankedTeam.getLastJoinedRankedTeamQueueDate())
                .modifyDate(rankedTeam.getSecondLastJoinDate())
                .name(rankedTeam.getName())
                .roster((List<SPSummoner>) summonerRestMapper.mapToSP(rankedTeam.getRoster().getMemberList()))
                .secondLastJoinDate(rankedTeam.getSecondLastJoinDate())
                .status(rankedTeam.getStatus())
                .tag(rankedTeam.getTag())
                .thirdLastJoinDate(rankedTeam.getThirdLastJoinDate())
                .build();
    }
}
