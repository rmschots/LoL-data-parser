package com.sneaky.peaky.dataparser.jpa.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPMiniSeries;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAMiniSeries;

/**
 *
 * @author Roel
 */
public class MiniSeriesJPAToSPMapper implements BidirectionalJPAToSPMapper<JPAMiniSeries, SPMiniSeries> {

    @Override
    public JPAMiniSeries mapToJPA(SPMiniSeries spObject) {
        return JPAMiniSeries.builder()
                .losses(spObject.getLosses())
                .progress(spObject.getProgress())
                .target(spObject.getTarget())
                .timeLeftToPlayMillis(spObject.getTimeLeftToPlayMillis())
                .wins(spObject.getWins())
                .build();
    }

    @Override
    public SPMiniSeries mapToDomain(JPAMiniSeries jpaObject) {
        return SPMiniSeries.builder()
                .losses(jpaObject.getLosses())
                .progress(jpaObject.getProgress())
                .target(jpaObject.getTarget())
                .timeLeftToPlayMillis(jpaObject.getTimeLeftToPlayMillis())
                .wins(jpaObject.getWins())
                .build();
    }

}
