package com.sneaky.peaky.dataparser.jpa.mapper.staticdata;

import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPMastery;
import com.sneaky.peaky.dataparser.jpa.mapper.BidirectionalJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.CollectionBiDirectionalJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAMastery;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Roel Mangelschots
 */
public class MasteryJPAToSPMapper implements BidirectionalJPAToSPMapper<JPAMastery, SPMastery>, CollectionBiDirectionalJPAToSPMapper<Set<JPAMastery>, Set<SPMastery>> {

    @Override
    public JPAMastery mapToJPA(SPMastery spm) {
        return JPAMastery.builder()
                .id(spm.getId())
                .name(spm.getName())
                .build();
    }

    @Override
    public SPMastery mapToDomain(JPAMastery jpam) {
        return SPMastery.builder()
                .id(jpam.getId())
                .name(jpam.getName())
                .build();
    }

    @Override
    public Set<JPAMastery> mapToJPA(Set<SPMastery> spCollection) {
        Set<JPAMastery> masteries = new HashSet<>();
        spCollection.stream().forEach((mastery) -> {
            masteries.add(mapToJPA(mastery));
        });
        return masteries;
    }

    @Override
    public Set<SPMastery> mapToSP(Set<JPAMastery> jpaCollection) {
        Set<SPMastery> masteries = new HashSet<>();
        jpaCollection.stream().forEach((mastery) -> {
            masteries.add(mapToDomain(mastery));
        });
        return masteries;
    }

}