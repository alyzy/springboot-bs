package cn.xaiu.springbootgraduation.dao;

import cn.xaiu.springbootgraduation.domain.TravelRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 可以使用@MapperScan在启动类上标注
 */
@Mapper
public interface ITravelRouteDao extends tk.mybatis.mapper.common.Mapper<TravelRoute> {

   @Select("select * from travelroute where route_id=#{routeId}")
   TravelRoute getOne(String routeId);

   @Select("select * from travelroute where travel_route_name like #{name}")
   TravelRoute getOneByName(String name);
   @Update("update travelroute set image_path=#{imagePath} where route_id=#{routeId}")
    void updateOne(@Param("routeId") String routeId, @Param("imagePath") String imagePath);
}
