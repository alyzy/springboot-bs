package cn.xaiu.springbootgraduation.controller;

import cn.xaiu.springbootgraduation.annotation.AddAop;
import cn.xaiu.springbootgraduation.domain.User;
import cn.xaiu.springbootgraduation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Controller
public class UserController {

    @Autowired
    private IUserService userService;
    /**
     * 注册方法
     * @return
     */

    @RequestMapping("/page/register")
    @ResponseBody
    public Map po(User user, @RequestParam("check")String check, HttpSession session, Map<String,Object> map){
        log.info("进入注册用户方法");
        //验证校验
        map =new HashMap<>();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //注册失败
            map.put("flag",false);
            map.put("errorMsg","验证码错误");

            return map;
        }

        boolean flag = userService.regist(user);

        //4.响应结果
        if(flag){
            //注册成功
            map.put("flag",flag);
        }else{
            //注册失败
            map.put("flag",flag);
            map.put("errorMsg","注册失败");

        }
        return map;
    }

    @AddAop
    @RequestMapping("page/login")
    @ResponseBody
    public Map login(User user,HttpSession session,Map<String,Object> map,@RequestParam("check") String check) throws Exception {
        log.info("进入登陆方法");

        map =new HashMap<>();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
           map.put("flag",false);
           map.put("errorMsg","验证码错误");
           return map;
        }
        User one = userService.findOne(user);
        Boolean status = userService.login(user);
        if(!status){
            map.put("flag",false);
            map.put("errorMsg","账户或密码错误");
        }else {
            map.put("flag",true);
            session.setAttribute("users",one);
        }
       return map;
    }
    @RequestMapping("/page/session")
    @ResponseBody
    public Map getSession(HttpSession session){
        log.info("1565");
        Map<String,Object> map =new HashMap<>();
        Object users = session.getAttribute("users");
        if(users==null){
            map.put("msg",true);
        }else {
            map.put("msg", false);
        }
        return map;
    }
}
