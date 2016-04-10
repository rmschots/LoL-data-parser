package com.sneaky.peaky.dataparser.domain.mapper.staticdata;

import com.sneaky.peaky.dataparser.domain.mapper.CollectionRestMapper;
import com.sneaky.peaky.dataparser.domain.mapper.RestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPMastery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.boreeas.riotapi.rest.Mastery;

/**
 *
 * @author Roel Mangelschots
 */
public class MasteryRestMapper implements RestMapper<SPMastery, Mastery>, CollectionRestMapper<Set<SPMastery>, Collection<Mastery>>{

    @Override
    public SPMastery mapToSP(Mastery mastery) {
        return SPMastery.builder()
                .id(mastery.getId())
                .name(mastery.getName())
                .build();
    }

    @Override
    public Set<SPMastery> mapToSP(Collection<Mastery> masteries) {
        Set<SPMastery> resultList = new HashSet<>();
        for(Mastery mastery : masteries){
            resultList.add(mapToSP(mastery));
        }
        return resultList;
    }
    
}