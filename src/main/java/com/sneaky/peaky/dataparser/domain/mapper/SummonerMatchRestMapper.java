package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.SPSummonerMatch;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.boreeas.riotapi.rest.MatchSummary;

/**
 *
 * @author Roel Mangelschots
 */
public class SummonerMatchRestMapper implements RestMapper<SPSummonerMatch, MatchSummary>, CollectionRestMapper<Set<SPSummonerMatch>, Set<MatchSummary>> {

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
    public Set<SPSummonerMatch> mapToSP(Set<MatchSummary> restObject) {
        Set<SPSummonerMatch> resultList = new HashSet<>();
        for (MatchSummary ms : restObject) {
            resultList.add(mapToSP(ms));
        }
        return resultList;
    }

    public Set<SPSummonerMatch> mapToSP(Set<MatchSummary> restObject, Map<Integer, SPChampion> championMap, Long summonerId) {
        Set<SPSummonerMatch> resultList = new HashSet<>();
        for (MatchSummary ms : restObject) {
            resultList.add(mapToSP(ms, championMap, summonerId));
        }
        return resultList;
    }

}
