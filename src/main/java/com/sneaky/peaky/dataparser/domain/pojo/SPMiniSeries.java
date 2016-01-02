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
public class SPMiniSeries extends AbstractSPObject{
    private Integer wins;
    private Integer losses;
    private String progress;
    private Integer target;
    private Long timeLeftToPlayMillis;
    
}
