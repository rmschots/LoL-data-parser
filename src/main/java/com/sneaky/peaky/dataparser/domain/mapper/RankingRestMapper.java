package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPRanking;
import net.boreeas.riotapi.com.riotgames.leagues.pojo.LeagueItem;

/**
 *
 * @author Roel
 */
public class RankingRestMapper implements RestMapper<SPRanking, LeagueItem>{

    @Override
    public SPRanking mapToSP(LeagueItem leagueItem) {
        return SPRanking.builder()
                .isFreshBlood(leagueItem.isFreshBlood())
                .isHotStreak(leagueItem.isHotStreak())
                .isInactive(leagueItem.isInactive())
                .isVeteran(leagueItem.isVeteran())
                .lastPlayed(leagueItem.getLastPlayed())
                .leagueName(leagueItem.getLeagueName())
                .leaguePoints(leagueItem.getLeaguePoints())
                .miniSeries((leagueItem.getMiniSeries()==null)? null : (new MiniSeriesRestMapper()).mapToSP(leagueItem.getMiniSeries()))
                .playerOrTeamId(leagueItem.getPlayerOrTeamId())
                .playerOrTeamName(leagueItem.getPlayerOrTeamName())
                .queueType(leagueItem.getQueueType().name())
                .rank(-1)
                .tier(leagueItem.getTier().name())
                .wins(leagueItem.getWins())
                .build();
    }
    
}