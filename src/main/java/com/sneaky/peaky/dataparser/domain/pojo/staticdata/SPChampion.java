package com.sneaky.peaky.dataparser.domain.pojo.staticdata;

import com.sneaky.peaky.dataparser.domain.pojo.AbstractSPObject;
import java.util.Set;
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
public class SPChampion extends AbstractSPObject{
    private Integer id;
    private String name;
    private Set<String> tagList;
}
