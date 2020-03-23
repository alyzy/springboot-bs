package cn.xaiu.springbootgraduation.interceptors;

import cn.xaiu.springbootgraduation.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptors implements HandlerInterceptor {

    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        User user = (User)request.getSession().getAttribute("users");
        Map<String,Object> map =new HashMap<>();
        if(user==null){
            response.sendRedirect(request.getContextPath()+"/page/loginPage");
            return false;
        }
       return true;
    }
}
