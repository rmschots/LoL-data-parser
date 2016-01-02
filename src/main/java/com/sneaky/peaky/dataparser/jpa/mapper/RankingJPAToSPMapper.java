package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPRanking;
import com.sneaky.peaky.dataparser.jpa.pojo.JPARanking;

/**
 *
 * @author Roel
 */
public class RankingJPAToSPMapper implements BidirectionalJPAToSPMapper<JPARanking, SPRanking> {

    public JPARanking mapToJPA(SPRanking spRanking, long revisionDate) {
        return createBasicJPARanking(spRanking)
                .revisionDate(revisionDate)
                .build();
    }

    @Override
    public JPARanking mapToJPA(SPRanking spRanking) {
        return createBasicJPARanking(spRanking)
                .revisionDate(System.currentTimeMillis())
                .build();
    }

    private JPARanking.JPARankingBuilder createBasicJPARanking(SPRanking spRanking) {
        return JPARanking.builder()
                .isFreshBlood(spRanking.isFreshBlood())
                .isHotStreak(spRanking.isHotStreak())
                .isInactive(spRanking.isInactive())
                .isVeteran(spRanking.isVeteran())
                .lastPlayed(spRanking.getLastPlayed())
                .leagueName(spRanking.getLeagueName())
                .leaguePoints(spRanking.getLeaguePoints())
                .miniSeries(spRanking.getMiniSeries()==null?null:(new MiniSeriesJPAToSPMapper()).mapToJPA(spRanking.getMiniSeries()))
                .playerOrTeamId(spRanking.getPlayerOrTeamId())
                .playerOrTeamName(spRanking.getPlayerOrTeamName())
                .queueType(spRanking.getQueueType())
                .rank(spRanking.getRank())
                .tier(spRanking.getTier())
                .wins(spRanking.getWins());
    }

    @Override
    public SPRanking mapToDomain(JPARanking jpaRanking) {
        return SPRanking.builder()
                .isFreshBlood(jpaRanking.isFreshBlood())
                .isHotStreak(jpaRanking.isHotStreak())
                .isInactive(jpaRanking.isInactive())
                .isVeteran(jpaRanking.isVeteran())
                .lastPlayed(jpaRanking.getLastPlayed())
                .leagueName(jpaRanking.getLeagueName())
                .leaguePoints(jpaRanking.getLeaguePoints())
                .miniSeries((new MiniSeriesJPAToSPMapper()).mapToDomain(jpaRanking.getMiniSeries()))
                .playerOrTeamId(jpaRanking.getPlayerOrTeamId())
                .playerOrTeamName(jpaRanking.getPlayerOrTeamName())
                .queueType(jpaRanking.getQueueType())
                .rank(jpaRanking.getRank())
                .tier(jpaRanking.getTier())
                .wins(jpaRanking.getWins())
                .build();
    }

}
