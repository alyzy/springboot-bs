package cn.xaiu.springbootgraduation.config;


import cn.xaiu.springbootgraduation.interceptors.LoginInterceptors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {




    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("page/index");
    }
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/**").addResourceLocations("file:\\idea_projrct\\springboot-graduation\\src\\main\\resources\\static\\img\\");
    }


   public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptors()).addPathPatterns("/**").excludePathPatterns("/","/page/**","/css/**","/img/**","/webjars/**");
    }
}
