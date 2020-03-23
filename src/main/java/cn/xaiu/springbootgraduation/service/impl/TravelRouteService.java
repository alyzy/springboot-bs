package cn.xaiu.springbootgraduation.service.impl;

import cn.xaiu.springbootgraduation.dao.ITravelRouteDao;
import cn.xaiu.springbootgraduation.domain.TravelRoute;
import cn.xaiu.springbootgraduation.service.ITravelRouteService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@Transactional
public class TravelRouteService implements ITravelRouteService {

    @Autowired
    private RedisTemplate rtRedisTemplate;
    @Autowired
    private ITravelRouteDao iTravelRouteDao;
    public List<TravelRoute> listAll() {

            return iTravelRouteDao.selectAll();

    }

    public void addOne(TravelRoute travelRoute) {
        try {

            iTravelRouteDao.insert(travelRoute);
        }catch (Exception e){
            e.printStackTrace();
            log.error("向数据库中插入数据。。。");
        }

    }

    @Override
    public TravelRoute getOne(String routeId) {
        TravelRoute travelRoute=null;
        try {
             travelRoute = iTravelRouteDao.getOne(routeId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询异常");
        }
        return travelRoute;
    }

    @Override
    public TravelRoute getOneByName(String name) {
         log.info("查询reids数据库是否存在");
        TravelRoute travelRoute=null;
        try {
             travelRoute = (TravelRoute) rtRedisTemplate.opsForValue().get(name);

            if (travelRoute == null) {

                 travelRoute = iTravelRouteDao.getOneByName("%" + name + "%");
                rtRedisTemplate.opsForValue().set(name, travelRoute);
                log.info("查询数据库，存入缓存，key:" + name + "value:" + travelRoute);

            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("缓存插入数据失败");
        }



        return travelRoute;
    }


    public void updateOne(String routeId, String str) {
        StringBuilder stringBuilder=new StringBuilder();
        TravelRoute travelRoute = iTravelRouteDao.getOne(routeId);
        String imagePath = travelRoute.getImagePath();
        stringBuilder.append(imagePath).append(str);
        iTravelRouteDao.updateOne(routeId,stringBuilder.toString());

    }


}
