package com.sneaky.peaky.dataparser.jpa.pojo;

import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAMastery;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPARune;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;
import net.boreeas.riotapi.constants.LeagueTier;

/**
 *
 * @author Roel Mangelschots
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "participant", indexes = {
        @Index(columnList = "summonerId", name = "participant_summoner_id_hidx")
})
public class JPAParticipant extends AbstractJPAObject{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long summonerId;
    @ManyToOne
    private JPAChampion champion;
    @Enumerated(EnumType.STRING)
    private LeagueTier highestAchievedSeasonTier;
    
    @ManyToMany
    private Set<JPAMastery> masteries;
    private Integer participantId;
    @Transient
    @ManyToMany
    private Set<JPARune> runes;
    private Integer spell1Id;
    private Integer spell2Id;
    @OneToOne(cascade = CascadeType.ALL)
    private JPAParticipantStats stats;
    private Integer teamId;

    public JPAParticipant() {
    }
    
    
    
}
