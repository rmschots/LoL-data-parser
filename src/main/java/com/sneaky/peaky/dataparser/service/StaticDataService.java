package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.StaticDataDAO;
import com.sneaky.peaky.dataparser.domain.mapper.ChampionRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.SPChampion;
import com.sneaky.peaky.dataparser.jpa.mapper.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.JPAChampion;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.boreeas.riotapi.rest.ChampData;
import net.boreeas.riotapi.rest.ChampionList;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel Mangelschots
 */
public class StaticDataService {

    public void fetchAllChampions(ThrottledApiHandler handler) throws InterruptedException, ExecutionException {
        ChampionRestMapper championRestMapper = new ChampionRestMapper();
        ChampionJPAToSPMapper championJPAToSPMapper = new ChampionJPAToSPMapper();
        StaticDataDAO staticDataDAO = new StaticDataDAO();

        List<JPAChampion> champions = staticDataDAO.findAll();
        Map<Integer, JPAChampion> champMap = champions.stream().collect(Collectors.toMap(JPAChampion::getId, Function.identity()));
        
        ChampionList championList = handler.getChampionListDto(ChampData.TAGS).get();
        List<SPChampion> spChampions = championRestMapper.mapToSP(championList.getChampions());
        List<JPAChampion> jPAChampions = championJPAToSPMapper.mapToJPA(spChampions);
        for (JPAChampion jpac : jPAChampions) {
            if(champMap.containsKey(jpac.getId())){
                JPAChampion orig = champMap.get(jpac.getId());
                boolean update = false;
                if(!orig.getName().equals(jpac.getName())){
                    orig.setName(jpac.getName());
                    update = true;
                }
                if(!orig.getTagList().equals(jpac.getTagList())){
                    orig.setTagList(jpac.getTagList());
                    update = true;
                }
                if(update){
                    staticDataDAO.update(orig);
                }
            }else{
                staticDataDAO.create(jpac);
            }
        }
    }
}
