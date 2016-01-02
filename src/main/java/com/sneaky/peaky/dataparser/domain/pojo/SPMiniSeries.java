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
    private int wins;
    private int losses;
    private String progress;
    private int target;
    private long timeLeftToPlayMillis;
    
}
