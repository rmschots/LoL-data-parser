package com.sneaky.peaky.dataparser.domain.mapper.staticdata;

import com.sneaky.peaky.dataparser.domain.mapper.CollectionRestMapper;
import com.sneaky.peaky.dataparser.domain.mapper.RestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPRune;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.boreeas.riotapi.rest.Item;

/**
 *
 * @author Roel Mangelschots
 */
public class RuneRestMapper implements RestMapper<SPRune, Item>, CollectionRestMapper<Set<SPRune>, Collection<Item>>{

    @Override
    public SPRune mapToSP(Item item) {
        return SPRune.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .build();
    }

    @Override
    public Set<SPRune> mapToSP(Collection<Item> items) {
        Set<SPRune> resultList = new HashSet<>();
        for(Item item : items){
            resultList.add(mapToSP(item));
        }
        return resultList;
    }
    
}
