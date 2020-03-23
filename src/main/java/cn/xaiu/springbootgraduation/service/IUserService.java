package cn.xaiu.springbootgraduation.service;

import cn.xaiu.springbootgraduation.domain.User;

public interface IUserService {
    /**
     * 注册账户
     * @param user
     * @return
     */
    Boolean regist(User user);

    Boolean login(User user);

    User findOne(User user);
}
