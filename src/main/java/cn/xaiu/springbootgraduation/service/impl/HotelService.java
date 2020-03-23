package cn.xaiu.springbootgraduation.service.impl;

import cn.xaiu.springbootgraduation.dao.IHotelDao;
import cn.xaiu.springbootgraduation.domain.Hotel;
import cn.xaiu.springbootgraduation.domain.TravelRoute;
import cn.xaiu.springbootgraduation.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelService implements IHotelService {
    @Autowired
    private IHotelDao iHotelDao;

    public void addHotel(Hotel hotel) {
        iHotelDao.insert(hotel);
    }


    public Hotel getOneByName(String name) {
        return null;
    }


    public List<Hotel> listAll() {
        return iHotelDao.selectAll();
    }

    @Override
    public TravelRoute getOne(String routeId) {

        return null;
    }

    @Override
    public void addOne(Hotel hotel) {
        iHotelDao.insert(hotel);
    }
}
