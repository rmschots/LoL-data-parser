package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPChampion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import net.boreeas.riotapi.rest.Champion;

/**
 *
 * @author Roel Mangelschots
 */
public class ChampionRestMapper implements RestMapper<SPChampion, Champion>, ListRestMapper<List<SPChampion>, Collection<Champion>>{

    @Override
    public SPChampion mapToSP(Champion champion) {
        return SPChampion.builder()
                .id(champion.getId())
                .name(champion.getKey())
                .tagList(new HashSet<>(champion.getTags()))
                .build();
    }

    @Override
    public List<SPChampion> mapToSP(Collection<Champion> champions) {
        List<SPChampion> resultList = new ArrayList<>();
        for(Champion champion : champions){
            resultList.add(mapToSP(champion));
        }
        return resultList;
    }
    
}
