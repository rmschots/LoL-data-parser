package com.sneaky.peaky.dataparser.jpa.pojo;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import net.boreeas.riotapi.com.riotgames.platform.game.QueueType;
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
@Table(name = "match_general", indexes = {
        @Index(columnList = "matchCreation", name = "match_match_creation_hidx")
})
public class JPAMatch extends AbstractJPAObject{
    @Id
    private Long id;
    
    private Long matchCreation;
    private Long matchDuration;
    private String matchVersion;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<JPAParticipant> participants;
    @Enumerated(EnumType.STRING)
    private QueueType queueType;
    private String region;
    @Enumerated(EnumType.STRING)
    private Season season;
    @OneToOne(cascade = CascadeType.ALL)
    private JPAMatchteam team1;
    @OneToOne(cascade = CascadeType.ALL)
    private JPAMatchteam team2;
    

    public JPAMatch() {
    }
    
}
