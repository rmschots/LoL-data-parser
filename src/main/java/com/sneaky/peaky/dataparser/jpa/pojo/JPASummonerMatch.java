package com.sneaky.peaky.dataparser.jpa.pojo;

import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
import net.boreeas.riotapi.constants.Lane;
import net.boreeas.riotapi.constants.Role;
import net.boreeas.riotapi.constants.Season;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "summonermatch", indexes = {
        @Index(columnList = "summonerId", name = "summonermatch_summoner_id_hidx"),
        @Index(columnList = "startTime", name = "summonermatch_start_time_hidx")
})
public class JPASummonerMatch extends AbstractJPAObject{
    @Id
    private Long id;
    
    private Long summonerId;
    private Long startTime;
    @ManyToOne
    private JPAChampion champion;
    private String region;
    @Enumerated(EnumType.STRING)
    private QueueType queue;
    @Enumerated(EnumType.STRING)
    private Season season;
    @Enumerated(EnumType.STRING)
    private Role gameRole;
    @Enumerated(EnumType.STRING)
    private Lane lane;

    public JPASummonerMatch() {
    }
}
