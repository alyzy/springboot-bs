package cn.xaiu.springbootgraduation.service;

import cn.xaiu.springbootgraduation.domain.Hotel;
import cn.xaiu.springbootgraduation.domain.TravelRoute;

import java.util.List;

public interface IHotelService {
    void addHotel(Hotel hotel);

    Hotel getOneByName(String name);

    List<Hotel> listAll();

    TravelRoute getOne(String routeId);

    void addOne(Hotel hotel);
}
