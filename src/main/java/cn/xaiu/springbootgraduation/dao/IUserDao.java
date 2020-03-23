package cn.xaiu.springbootgraduation.dao;

import cn.xaiu.springbootgraduation.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUserDao {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user(username,password,uname,birthday,sex,telephone,email,createDate,permission,sts) values(#{username},#{password},#{uname},#{birthday},#{sex},#{telephone},#{email},#{createDate},#{permission},#{sts})")
    void save(User user);
    @Select("select * from user where username = #{username} and password =#{password}")
    User checkUsernameAndPassword(@Param("username") String username, @Param("password") String password);


}
