package com.sneaky.peaky.dataparser.service;

import com.sneaky.peaky.dataparser.dao.EntityManagerManager;
import com.sneaky.peaky.dataparser.dao.staticdata.ChampionDao;
import com.sneaky.peaky.dataparser.dao.staticdata.MasteryDao;
import com.sneaky.peaky.dataparser.dao.staticdata.RuneDao;
import com.sneaky.peaky.dataparser.domain.mapper.staticdata.ChampionRestMapper;
import com.sneaky.peaky.dataparser.domain.mapper.staticdata.MasteryRestMapper;
import com.sneaky.peaky.dataparser.domain.mapper.staticdata.RuneRestMapper;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPChampion;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPMastery;
import com.sneaky.peaky.dataparser.domain.pojo.staticdata.SPRune;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.ChampionJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.MasteryJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.mapper.staticdata.RuneJPAToSPMapper;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAChampion;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPAMastery;
import com.sneaky.peaky.dataparser.jpa.pojo.staticdata.JPARune;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import net.boreeas.riotapi.rest.ChampData;
import net.boreeas.riotapi.rest.ChampionList;
import net.boreeas.riotapi.rest.MasteryList;
import net.boreeas.riotapi.rest.RuneList;
import net.boreeas.riotapi.rest.ThrottledApiHandler;

/**
 *
 * @author Roel Mangelschots
 */
public class StaticDataService {

    public void fetchAllChampions(ThrottledApiHandler handler) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        
        ChampionRestMapper championRestMapper = new ChampionRestMapper();
        ChampionJPAToSPMapper championJPAToSPMapper = new ChampionJPAToSPMapper();
        ChampionDao championDao = new ChampionDao(em);

        Set<JPAChampion> champions = championDao.findAll();
        Map<Integer, JPAChampion> champMap = champions.stream().collect(Collectors.toMap(JPAChampion::getId, Function.identity()));
        
        ChampionList championList = handler.getChampionListDto(ChampData.TAGS).get();
        Set<SPChampion> spChampions = championRestMapper.mapToSP(championList.getChampions());
        Set<JPAChampion> jPAChampions = championJPAToSPMapper.mapToJPA(spChampions);
        jPAChampions.stream().forEach((jpac) -> {
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
                    championDao.update(orig);
                }
            }else{
                championDao.create(jpac);
            }
        });
        
        EntityManagerManager.closeSession(em);
    }
    
    public void fetchAllMasteries(ThrottledApiHandler handler) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        
        MasteryRestMapper masteryRestMapper = new MasteryRestMapper();
        MasteryJPAToSPMapper masteryJPAToSPMapper = new MasteryJPAToSPMapper();
        MasteryDao masteryDao = new MasteryDao(em);

        Set<JPAMastery> masteries = masteryDao.findAll();
        Map<Integer, JPAMastery> masteryMap = masteries.stream().collect(Collectors.toMap(JPAMastery::getId, Function.identity()));
        
        MasteryList masteryList = handler.getMasteries().get();
        Set<SPMastery> spMasteries = masteryRestMapper.mapToSP(masteryList.getData().values());
        Set<JPAMastery> jpaMasteries = masteryJPAToSPMapper.mapToJPA(spMasteries);
        jpaMasteries.stream().forEach((jpar) -> {
            if(masteryMap.containsKey(jpar.getId())){
                JPAMastery orig = masteryMap.get(jpar.getId());
                boolean update = false;
                if(!orig.getName().equals(jpar.getName())){
                    orig.setName(jpar.getName());
                    update = true;
                }
                if(update){
                    masteryDao.update(orig);
                }
            }else{
                masteryDao.create(jpar);
            }
        });
        
        EntityManagerManager.closeSession(em);
    }
    
    public void fetchAllRunes(ThrottledApiHandler handler) throws InterruptedException, ExecutionException {
        EntityManager em = EntityManagerManager.openSession();
        
        RuneRestMapper runeRestMapper = new RuneRestMapper();
        RuneJPAToSPMapper runeJPAToSPMapper = new RuneJPAToSPMapper();
        RuneDao runeDao = new RuneDao(em);

        Set<JPARune> runes = runeDao.findAll();
        Map<Integer, JPARune> runeMap = runes.stream().collect(Collectors.toMap(JPARune::getId, Function.identity()));
        
        RuneList runeList = handler.getRuneList().get();
        Set<SPRune> spRunes = runeRestMapper.mapToSP(runeList.getData().values());
        Set<JPARune> jpaRunes = runeJPAToSPMapper.mapToJPA(spRunes);
        jpaRunes.stream().forEach((jpar) -> {
            if(runeMap.containsKey(jpar.getId())){
                JPARune orig = runeMap.get(jpar.getId());
                boolean update = false;
                if(!orig.getName().equals(jpar.getName())){
                    orig.setName(jpar.getName());
                    update = true;
                }
                if(!orig.getDescription().equals(jpar.getDescription())){
                    orig.setDescription(jpar.getDescription());
                    update = true;
                }
                if(update){
                    runeDao.update(orig);
                }
            }else{
                runeDao.create(jpar);
            }
        });
        
        EntityManagerManager.closeSession(em);
    }
}
