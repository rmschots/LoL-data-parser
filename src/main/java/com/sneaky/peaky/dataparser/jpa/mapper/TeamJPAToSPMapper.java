package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPSummoner;
import com.sneaky.peaky.dataparser.domain.pojo.SPTeam;
import com.sneaky.peaky.dataparser.jpa.pojo.JPASummoner;
import com.sneaky.peaky.dataparser.jpa.pojo.JPATeam;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roel
 */
public class TeamJPAToSPMapper implements BidirectionalJPAToSPMapper<JPATeam, SPTeam>{

    @Override
    public JPATeam mapToJPA(SPTeam team) {
        ArrayList<Long> summonerIds = new ArrayList<>();
        for(SPSummoner sPSummoner : team.getRoster()){
            summonerIds.add(sPSummoner.getSummonerId());
        }
        return JPATeam.builder()
                .createDate(team.getCreateDate())
                .lastGameDate(team.getCreateDate())
                .lastJoinDate(team.getLastJoinDate())
                .lastJoinedRankedTeamQueueDate(team.getLastJoinedRankedTeamQueueDate())
                .name(team.getName())
                .revisionDate(team.getModifyDate())
                .secondLastJoinDate(team.getSecondLastJoinDate())
                .status(team.getStatus())
                .summonerIds(summonerIds)
                .tag(team.getTag())
                .teamId(team.getFullId())
                .thirdLastJoinDate(team.getThirdLastJoinDate())
                .build();
    }

    @Override
    public SPTeam mapToDomain(JPATeam team) {
        return SPTeam.builder()
                .createDate(team.getCreateDate())
                .lastGameDate(team.getCreateDate())
                .lastJoinDate(team.getLastJoinDate())
                .lastJoinedRankedTeamQueueDate(team.getLastJoinedRankedTeamQueueDate())
                .name(team.getName())
                .modifyDate(team.getRevisionDate())
                .secondLastJoinDate(team.getSecondLastJoinDate())
                .status(team.getStatus())
                .tag(team.getTag())
                .fullId(team.getTeamId())
                .thirdLastJoinDate(team.getThirdLastJoinDate())
                .build();
    }
    
    public SPTeam mapToDomain(JPATeam team, List<JPASummoner> summoners) {
        SummonerJPAToSPMapper mapper = new SummonerJPAToSPMapper();
        SPTeam spTeam = mapToDomain(team);
        spTeam.setRoster(mapper.mapToSP(summoners));
        return spTeam;
    }
    
}
