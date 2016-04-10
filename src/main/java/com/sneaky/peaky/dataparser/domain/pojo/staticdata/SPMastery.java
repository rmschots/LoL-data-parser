package com.sneaky.peaky.dataparser.domain.pojo.staticdata;

import com.sneaky.peaky.dataparser.domain.pojo.AbstractSPObject;
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
public class SPMastery extends AbstractSPObject{
    private Integer id;
    private String name;
}