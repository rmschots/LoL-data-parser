package com.sneaky.peaky.dataparser.jpa.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "summoner")
public class JPASummoner extends AbstractJPAObject {
    @Id
    @GeneratedValue
    private Long id;
    
    private Long summonerId;
    private String name;
    private Integer profileIconId;
    private Long revisionDate;
    private Long summonerLevel;
}
