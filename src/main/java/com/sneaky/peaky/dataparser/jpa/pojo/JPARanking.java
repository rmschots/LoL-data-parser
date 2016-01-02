/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private boolean isFreshBlood;
    private boolean isHotStreak;
    private boolean isInactive;
    private boolean isVeteran;
    private long lastPlayed;
    private String leagueName;
    private int leaguePoints;
    @OneToOne
    private JPAMiniSeries miniSeries;
    private JPASummoner player;
    private String playerOrTeamId;
    private String playerOrTeamName;
    private String queueType;
    private Integer rank;
    private String tier;
    private int wins;
    private long revisionDate;

    public JPARanking() {
    }
    
}
