package com.sneaky.peaky.dataparser.domain.mapper;

import com.sneaky.peaky.dataparser.domain.pojo.SPMiniSeries;
import net.boreeas.riotapi.com.riotgames.leagues.pojo.MiniSeries;

/**
 *
 * @author Roel
 */
public class MiniSeriesRestMapper implements RestMapper<SPMiniSeries, MiniSeries>{

    @Override
    public SPMiniSeries mapToSP(MiniSeries miniSeries) {
        return SPMiniSeries.builder()
                .losses(miniSeries.getLosses())
                .progress(miniSeries.getProgress())
                .target(miniSeries.getTarget())
                .timeLeftToPlayMillis(miniSeries.getTimeLeftToPlayMillis())
                .wins(miniSeries.getWins())
                .build();
    }
    
}
