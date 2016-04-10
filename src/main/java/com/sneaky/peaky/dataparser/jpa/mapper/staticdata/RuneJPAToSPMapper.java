package com.sneaky.peaky.dataparser.jpa.mapper.staticdata;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPRune;
import com.sneaky.peaky.dataparser.jpa.mapper.BidirectionalJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.CollectionBiDirectionalJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPARune;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Roel Mangelschots
 */
public class RuneJPAToSPMapper implements BidirectionalJPAToSPMapper<JPARune, SPRune>, CollectionBiDirectionalJPAToSPMapper<Set<JPARune>, Set<SPRune>> {

    @Override
    public JPARune mapToJPA(SPRune spr) {
        return JPARune.builder()
                .id(spr.getId())
                .name(spr.getName())
                .description(spr.getDescription())
                .build();
    }

    @Override
    public SPRune mapToDomain(JPARune jpar) {
        return SPRune.builder()
                .id(jpar.getId())
                .name(jpar.getName())
                .description(jpar.getDescription())
                .build();
    }

    @Override
    public Set<JPARune> mapToJPA(Set<SPRune> spCollection) {
        Set<JPARune> runes = new HashSet<>();
        spCollection.stream().forEach((rune) -> {
            runes.add(mapToJPA(rune));
        });
        return runes;
    }

    @Override
    public Set<SPRune> mapToSP(Set<JPARune> jpaCollection) {
        Set<SPRune> runes = new HashSet<>();
        jpaCollection.stream().forEach((rune) -> {
            runes.add(mapToDomain(rune));
        });
        return runes;
    }

}