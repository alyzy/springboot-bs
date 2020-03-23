package cn.xaiu.springbootgraduation.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IAlldao {

    @Insert("insert into pay_route values(#{outTradeNo},#{routeId})")
    void insertPR(@Param("outTradeNo") Long outTradeNo,@Param("routeId") String routeId);

    @Insert("insert into user_pay values(#{username},#{outTradeNo})")
    void insertUP(@Param("username") String username,@Param("outTradeNo") Long outTradeNo);
}
