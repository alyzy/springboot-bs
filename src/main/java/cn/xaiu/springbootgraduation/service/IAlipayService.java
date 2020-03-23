package cn.xaiu.springbootgraduation.service;

import cn.xaiu.springbootgraduation.domain.AlipayProductPoJo;
import cn.xaiu.springbootgraduation.domain.TravelRoute;
import cn.xaiu.springbootgraduation.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public interface IAlipayService {

    void addOnePay(AlipayProductPoJo alipayProductPoJo, String routeId, User user);

    void updateOne(String outTradeNo, String trade_no, Date payDate);

    List<AlipayProductPoJo> listAll(String username);


    AlipayProductPoJo getOneByName(String name);
}