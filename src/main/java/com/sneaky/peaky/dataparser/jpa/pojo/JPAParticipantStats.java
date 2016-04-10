package com.sneaky.peaky.dataparser.jpa.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "participant_stats")
public class JPAParticipantStats extends AbstractJPAObject {
    @Id
    @GeneratedValue
    private Long id;
    
    private Long assists;
    private Long deaths;
    private Long doubleKills;
    private Boolean firstBloodAssist;
    private Boolean firstBloodKill;
    private Boolean firstInhibitorAssist;
    private Boolean firstInhibitorKill;
    private Boolean firstTowerAssist;
    private Boolean firstTowerKill;
    private Long goldEarned;
    private Long goldSpent;
    private Long inhibitorKills;
    private Long item0;
    private Long item1;
    private Long item2;
    private Long item3;
    private Long item4;
    private Long item5;
    private Long item6;
    private Long killingSprees;
    private Long kills;
    private Long largestCriticalStrike;
    private Long largestKillingSpree;
    private Long largestMultiKill;
    private Long magicDamageDealt;
    private Long magicDamageDealtToChampions;
    private Long magicDamageTaken;
    private Long minionsKilled;
    private Long neutralMinionsKilled;
    private Long neutralMinionsKilledEnemyJungle;
    private Long neutralMinionsKilledTeamJungle;
    private Long nodeCapture;
    private Long nodeCaptureAssist;
    private Long nodeNeutralize;
    private Long nodeNeutralizeAssist;
    private Long objectivePlayerScore;
    private Long pentaKills;
    private Long physicalDamageDealt;
    private Long physicalDamageDealtToChampions;
    private Long physicalDamageTaken;
    private Long quadraKills;
    private Long sightWardsBoughtInGame;
    private Long teamObjective;
    private Long totalDamageDealt;
    private Long totalDamageDealtToChampions;
    private Long totalDamageTaken;
    private Long totalHeal;
    private Long totalPlayerScore;
    private Long totalScoreRank;
    private Long totalTimeCrowdControlDealt;
    private Long totalUnitsHealed;
    private Long towerKills;
    private Long tripleKills;
    private Long trueDamageDealt;
    private Long trueDamageDealtToChampions;
    private Long trueDamageTaken;
    private Long unrealKills;
    private Long visionWardsBoughtInGame;
    private Long wardsKilled;
    private Long wardsPlaced;
    private Boolean winner;

    public JPAParticipantStats() {
    }
    
}
