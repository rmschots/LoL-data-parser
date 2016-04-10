package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.AbstractSPObject;
import com.sneaky.peaky.dataparser.domain.pojo.SPChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.AbstractJPAObject;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAChampion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Roel Mangelschots
 */
public class ChampionJPAToSPMapper implements BidirectionalJPAToSPMapper<JPAChampion, SPChampion>, ListBiDirectionalJPAToSPMapper<List<JPAChampion>, List<SPChampion>> {

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
    public List<JPAChampion> mapToJPA(List<SPChampion> spCollection) {
        List<JPAChampion> champions = new ArrayList<>();
        for(SPChampion champion : spCollection){
            champions.add(mapToJPA(champion));
        }
        return champions;
    }

    @Override
    public List<SPChampion> mapToSP(List<JPAChampion> jpaCollection) {
        List<SPChampion> champions = new ArrayList<>();
        for(JPAChampion champion : jpaCollection){
            champions.add(mapToDomain(champion));
        }
        return champions;
    }

}