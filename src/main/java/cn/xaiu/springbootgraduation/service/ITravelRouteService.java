package cn.xaiu.springbootgraduation.service;

import cn.xaiu.springbootgraduation.domain.TravelRoute;

import java.util.List;

public interface ITravelRouteService {

    List<TravelRoute> listAll();

    void addOne(TravelRoute travelRoute);

    TravelRoute getOne(String routeId);

    TravelRoute getOneByName(String name);

    void updateOne(String routeId, String toString);
}
