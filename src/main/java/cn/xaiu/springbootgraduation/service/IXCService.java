package cn.xaiu.springbootgraduation.service;

import cn.xaiu.springbootgraduation.domain.TravelRoute;
import cn.xaiu.springbootgraduation.domain.User;
import cn.xaiu.springbootgraduation.domain.XC;

import java.util.List;

public interface IXCService {

    void addXC(XC xc);

    List<XC> listAll();

    XC getOneByName(String name);

    void addOne(XC xc);


}
