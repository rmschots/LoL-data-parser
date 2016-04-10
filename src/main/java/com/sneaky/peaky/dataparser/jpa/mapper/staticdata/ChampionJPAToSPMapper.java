package com.sneaky.peaky.dataparser.jpa.mapper.staticdata;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.jpa.mapper.BidirectionalJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.CollectionBiDirectionalJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Roel Mangelschots
 */
public class ChampionJPAToSPMapper implements BidirectionalJPAToSPMapper<JPAChampion, SPChampion>, CollectionBiDirectionalJPAToSPMapper<Set<JPAChampion>, Set<SPChampion>> {

    @Override
    public JPAChampion mapToJPA(SPChampion spc) {
        return JPAChampion.builder()
                .id(spc.getId())
                .name(spc.getName())
                .tagList(spc.getTagList())
                .build();
    }

    @Override
    public SPChampion mapToDomain(JPAChampion jpac) {
        return SPChampion.builder()
                .id(jpac.getId())
                .name(jpac.getName())
                .tagList(jpac.getTagList())
                .build();
    }

    @Override
    public Set<JPAChampion> mapToJPA(Set<SPChampion> spCollection) {
        Set<JPAChampion> champions = new HashSet<>();
        spCollection.stream().forEach((champion) -> {
            champions.add(mapToJPA(champion));
        });
        return champions;
    }

    @Override
    public Set<SPChampion> mapToSP(Set<JPAChampion> jpaCollection) {
        Set<SPChampion> champions = new HashSet<>();
        jpaCollection.stream().forEach((champion) -> {
            champions.add(mapToDomain(champion));
        });
        return champions;
    }

}