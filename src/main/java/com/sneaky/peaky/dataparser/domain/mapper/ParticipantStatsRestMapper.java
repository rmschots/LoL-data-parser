package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPParticipantStats;
import net.boreeas.riotapi.rest.Participant;
import net.boreeas.riotapi.rest.ParticipantStats;

/**
 *
 * @author Roel Mangelschots
 */
public class ParticipantStatsRestMapper implements RestMapper<SPParticipantStats, ParticipantStats>{

    @Override
    public SPParticipantStats mapToSP(ParticipantStats restObject) {
        return SPParticipantStats.builder()
                .assists(restObject.getAssists())
                .deaths(restObject.getDeaths())
                .doubleKills(restObject.getDoubleKills())
                .firstBloodAssist(restObject.isFirstBloodAssist())
                .firstBloodKill(restObject.isFirstBloodKill())
                .firstInhibitorAssist(restObject.isFirstInhibitorAssist())
                .firstInhibitorKill(restObject.isFirstInhibitorKill())
                .firstTowerAssist(restObject.isFirstTowerAssist())
                .firstTowerKill(restObject.isFirstTowerKill())
                .goldEarned(restObject.getGoldEarned())
                .goldSpent(restObject.getGoldSpent())
                .inhibitorKills(restObject.getInhibitorKills())
                .item0(restObject.getItem0())
                .item1(restObject.getItem1())
                .item2(restObject.getItem2())
                .item3(restObject.getItem3())
                .item4(restObject.getItem4())
                .item5(restObject.getItem5())
                .item6(restObject.getItem6())
                .killingSprees(restObject.getKillingSprees())
                .kills(restObject.getKills())
                .largestCriticalStrike(restObject.getLargestCriticalStrike())
                .largestKillingSpree(restObject.getLargestKillingSpree())
                .largestMultiKill(restObject.getLargestMultiKill())
                .magicDamageDealt(restObject.getMagicDamageDealt())
                .magicDamageDealtToChampions(restObject.getMagicDamageDealtToChampions())
                .magicDamageTaken(restObject.getMagicDamageTaken())
                .minionsKilled(restObject.getMinionsKilled())
                .neutralMinionsKilled(restObject.getNeutralMinionsKilled())
                .neutralMinionsKilledEnemyJungle(restObject.getNeutralMinionsKilledEnemyJungle())
                .neutralMinionsKilledTeamJungle(restObject.getNeutralMinionsKilledTeamJungle())
                .nodeCapture(restObject.getNodeCapture())
                .nodeCaptureAssist(restObject.getNodeCaptureAssist())
                .nodeNeutralize(restObject.getNodeNeutralize())
                .nodeNeutralizeAssist(restObject.getNodeNeutralizeAssist())
                .objectivePlayerScore(restObject.getObjectivePlayerScore())
                .pentaKills(restObject.getPentaKills())
                .physicalDamageDealt(restObject.getPhysicalDamageDealt())
                .physicalDamageDealtToChampions(restObject.getPhysicalDamageDealtToChampions())
                .physicalDamageTaken(restObject.getPhysicalDamageTaken())
                .quadraKills(restObject.getQuadraKills())
                .sightWardsBoughtInGame(restObject.getSightWardsBoughtInGame())
                .teamObjective(restObject.getTeamObjective())
                .totalDamageDealt(restObject.getTotalDamageDealt())
                .totalDamageDealtToChampions(restObject.getTotalDamageDealtToChampions())
                .totalDamageTaken(restObject.getTotalDamageTaken())
                .totalHeal(restObject.getTotalHeal())
                .totalPlayerScore(restObject.getTotalPlayerScore())
                .totalScoreRank(restObject.getTotalScoreRank())
                .totalTimeCrowdControlDealt(restObject.getTotalTimeCrowdControlDealt())
                .totalUnitsHealed(restObject.getTotalUnitsHealed())
                .towerKills(restObject.getTowerKills())
                .tripleKills(restObject.getTripleKills())
                .trueDamageDealt(restObject.getTrueDamageDealt())
                .trueDamageDealtToChampions(restObject.getTrueDamageDealtToChampions())
                .trueDamageTaken(restObject.getTrueDamageTaken())
                .unrealKills(restObject.getUnrealKills())
                .visionWardsBoughtInGame(restObject.getVisionWardsBoughtInGame())
                .wardsKilled(restObject.getWardsKilled())
                .wardsPlaced(restObject.getWardsPlaced())
                .winner(restObject.isWinner())
                .build();
    }
    
}
