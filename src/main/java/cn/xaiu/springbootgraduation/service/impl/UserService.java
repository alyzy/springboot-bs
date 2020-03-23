package cn.xaiu.springbootgraduation.service.impl;

import cn.xaiu.springbootgraduation.dao.IUserDao;
import cn.xaiu.springbootgraduation.domain.User;
import cn.xaiu.springbootgraduation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Slf4j
public class UserService implements IUserService {
@Autowired
    private IUserDao iUserDao;

    public Boolean regist(User user) {
        log.info("进入UserService的regist方法");
        try {
            User u = iUserDao.findByUsername(user.getUsername());
            //判断u是否为null
            if (u != null) {
                //用户名存在，注册失败
                return false;
            }
            //2.保存用户信息
            user.setCreateDate(new Date());
            iUserDao.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error("调用UserService的regist方法异常");
        }
        return false;
    }


    public Boolean login(User user) {
        User u=null;
        try {
           u= iUserDao.checkUsernameAndPassword(user.getUsername(), user.getPassword());

        }catch (Exception e){
            log.error("调用checkUsernameAndPassword方法异常");
            e.printStackTrace();
        }
        if(u==null){
            return false;
        }
        return true;
    }

    @Override
    public User findOne(User user) {
      return   iUserDao.checkUsernameAndPassword(user.getUsername(), user.getPassword());
    }


}
