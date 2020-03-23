package cn.xaiu.springbootgraduation.dao;

import cn.xaiu.springbootgraduation.domain.AlipayProductPoJo;
import cn.xaiu.springbootgraduation.domain.TravelRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Mapper
public interface IAlipayDao extends tk.mybatis.mapper.common.Mapper<AlipayProductPoJo> {


    @Update("update alipayproduct set sts=#{sts},trade_no=#{on},pay_date=#{date} where out_trade_no = #{outTradeNo}")
    void updateOne(@Param("outTradeNo") Long outTradeNo, @Param("on") Long on, @Param("date") Date date,@Param("sts") String sts);

    @Select("select * from alipayproduct where body=#{body}")
    AlipayProductPoJo getByName(String body);
    @Select("select a.* from alipayproduct a ,user_pay u where u.out_trade_no= a.out_trade_no and u.username=#{username}")
    List<AlipayProductPoJo> getByNameIsUser(String username);
}
