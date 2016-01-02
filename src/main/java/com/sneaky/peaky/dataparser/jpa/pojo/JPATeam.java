package com.sneaky.peaky.dataparser.jpa.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
        uniqueConstraints = @UniqueConstraint(columnNames = {"fullId","modifyDate"}))
public class JPATeam extends AbstractJPAObject{
    @Id
    @GeneratedValue
    private Long id;
    
    private long createDate;
    private String fullId;
    private long lastGameDate;
    private long lastJoinDate;
    private long lastJoinedRankedTeamQueueDate;
    //private List<MatchHistorySummary> matchHistory = new ArrayList<>();
    //private MessageOfDay messageOfDay;
    private long modifyDate;
    private String name;
    //private Roster roster;
    private long secondLastJoinDate;
    private String status;
    private String tag;
    //private TeamStatSummary teamStatSummary;
    private long thirdLastJoinDate;

    public JPATeam() {
    }
}
