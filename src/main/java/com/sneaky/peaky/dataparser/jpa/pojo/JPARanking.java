package com.sneaky.peaky.dataparser.jpa.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "ranking",
        uniqueConstraints = @UniqueConstraint(columnNames = {"playerOrTeamId","revisionDate"}))
public class JPARanking extends AbstractJPAObject{
    @Id
    @GeneratedValue
    private Long id;
    
    private Boolean isFreshBlood;
    private Boolean isHotStreak;
    private Boolean isInactive;
    private Boolean isVeteran;
    private Long lastPlayed;
    private String leagueName;
    private Integer leaguePoints;
    @OneToOne
    private JPAMiniSeries miniSeries;
    private JPASummoner player;
    private String playerOrTeamId;
    private String playerOrTeamName;
    private String queueType;
    private Integer rank;
    private String tier;
    private Integer wins;
    private Long revisionDate;

    public JPARanking() {
    }
    
}
