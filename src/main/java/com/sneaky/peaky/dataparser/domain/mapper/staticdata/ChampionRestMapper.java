package com.sneaky.peaky.dataparser.domain.mapper.staticdata;

import com.sneaky.peaky.dataparser.domain.mapper.CollectionRestMapper;
import com.sneaky.peaky.dataparser.domain.mapper.RestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.boreeas.riotapi.rest.Champion;

/**
 *
 * @author Roel Mangelschots
 */
public class ChampionRestMapper implements RestMapper<SPChampion, Champion>, CollectionRestMapper<Set<SPChampion>, Collection<Champion>>{

    @Override
    public SPChampion mapToSP(Champion champion) {
        return SPChampion.builder()
                .id(champion.getId())
                .name(champion.getKey())
                .tagList(new HashSet<>(champion.getTags()))
                .build();
    }

    @Override
    public Set<SPChampion> mapToSP(Collection<Champion> champions) {
        Set<SPChampion> resultList = new HashSet<>();
        for(Champion champion : champions){
            resultList.add(mapToSP(champion));
        }
        return resultList;
    }
    
}
