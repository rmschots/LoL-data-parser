package com.sneaky.peaky.dataparser.jpa.pojo;

import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "matchteam")
public class JPAMatchteam extends AbstractJPAObject{
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private JPAChampion bannedChampion1;
    @ManyToOne
    private JPAChampion bannedChampion2;
    @ManyToOne
    private JPAChampion bannedChampion3;
    
    private Integer baronKills;
    private Long dominionVictoryScore;
    private Integer dragonKills;
    private Boolean firstBaron;
    private Boolean firstBlood;
    private Boolean firstDragon;
    private Boolean firstInhibitor;
    private Boolean firstRiftHerald;
    private Boolean firstTower;
    private Integer inhibitorKills;
    private Integer riftHeraldKills;
    private Integer teamId;
    private Integer towerKills;
    private Integer vilemawKills;
    private Boolean winner;

    public JPAMatchteam() {
    }
    
}
