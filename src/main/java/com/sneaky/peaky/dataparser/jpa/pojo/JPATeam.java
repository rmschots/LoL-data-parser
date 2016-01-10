package com.sneaky.peaky.dataparser.jpa.pojo;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "team",
        uniqueConstraints = @UniqueConstraint(columnNames = {"teamId","revisionDate"}))
public class JPATeam extends AbstractJPAObject{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long createDate;
    private String teamId;
    private Long lastGameDate;
    private Long lastJoinDate;
    private Long lastJoinedRankedTeamQueueDate;
    //private List<MatchHistorySummary> matchHistory = new ArrayList<>();
    //private MessageOfDay messageOfDay;
    private Long revisionDate;
    private String name;
    
    @ElementCollection
    @CollectionTable(name = "team_summoners", joinColumns = @JoinColumn(name = "teamId"))
    @Column
    private List<Long> summonerIds;
    
    private Long secondLastJoinDate;
    private String status;
    private String tag;
    //private TeamStatSummary teamStatSummary;
    private Long thirdLastJoinDate;

    public JPATeam() {
    }
}
