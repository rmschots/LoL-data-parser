package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.SPSummonerMatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.boreeas.riotapi.rest.MatchSummary;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchRestMapper implements RestMapper<SPSummonerMatch, MatchSummary>, ListRestMapper<List<SPSummonerMatch>, List<MatchSummary>> {

    @Override
    public SPSummonerMatch mapToSP(MatchSummary match) {
        return createBasic(match)
                .build();
    }

    public SPSummonerMatch mapToSP(MatchSummary match, Map<Integer, SPChampion> championMap, Long summonerId) {
        return createBasic(match)
                .champion(championMap.get(match.getChampion()))
                .summonerId(summonerId)
                .build();
    }

    private SPSummonerMatch.SPSummonerMatchBuilder createBasic(MatchSummary match) {
        return SPSummonerMatch.builder()
                .id(match.getMatchId())
                .startTime(match.getTimestamp())
                .region(match.getRegion())
                .queue(match.getQueue())
                .season(match.getSeason())
                .role(match.getRole())
                .lane(match.getLane());
    }

    @Override
    public List<SPSummonerMatch> mapToSP(List<MatchSummary> restObject) {
        List<SPSummonerMatch> resultList = new ArrayList<>();
        for (MatchSummary ms : restObject) {
            resultList.add(mapToSP(ms));
        }
        return resultList;
    }

    public List<SPSummonerMatch> mapToSP(List<MatchSummary> restObject, Map<Integer, SPChampion> championMap, Long summonerId) {
        List<SPSummonerMatch> resultList = new ArrayList<>();
        for (MatchSummary ms : restObject) {
            resultList.add(mapToSP(ms, championMap, summonerId));
        }
        return resultList;
    }

}
