package com.sneaky.peaky.dataparser.jpa.pojo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import net.boreeas.riotapi.com.riotgames.team.dto.Roster;

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
    
    private Long createDate;
    private String fullId;
    private Long lastGameDate;
    private Long lastJoinDate;
    private Long lastJoinedRankedTeamQueueDate;
    //private List<MatchHistorySummary> matchHistory = new ArrayList<>();
    //private MessageOfDay messageOfDay;
    private Long modifyDate;
    private String name;
    @ManyToMany
    private List<JPASummoner> roster;
    private Long secondLastJoinDate;
    private String status;
    private String tag;
    //private TeamStatSummary teamStatSummary;
    private Long thirdLastJoinDate;

    public JPATeam() {
    }
}
