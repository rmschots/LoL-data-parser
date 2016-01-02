package com.sneaky.peaky.dataparser.domain.pojo;

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
public class SPSummoner extends AbstractSPObject{
    private Long summonerId;
    private String name;
    private Integer profileIconId;
    private Long revisionDate;
    private Long summonerLevel;
}
