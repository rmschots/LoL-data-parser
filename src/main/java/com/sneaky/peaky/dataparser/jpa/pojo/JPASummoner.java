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
@Table(name = "summoner",
        uniqueConstraints = @UniqueConstraint(columnNames = {"summonerId", "revisionDate"}))
public class JPASummoner extends AbstractJPAObject {
    @Id
    @GeneratedValue
    private Long id;
    
    private Long summonerId;
    private String name;
    private Integer profileIconId;
    private Long revisionDate;
    private Long summonerLevel;

    public JPASummoner() {
    }
}
