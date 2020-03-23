package cn.xaiu.springbootgraduation.dao;

import cn.xaiu.springbootgraduation.domain.AlipayProductPoJo;
import cn.xaiu.springbootgraduation.domain.Hotel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IHotelDao extends tk.mybatis.mapper.common.Mapper<Hotel> {

}
