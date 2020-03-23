package cn.xaiu.springbootgraduation.controller;

import cn.xaiu.springbootgraduation.domain.Hotel;
import cn.xaiu.springbootgraduation.domain.TravelRoute;
import cn.xaiu.springbootgraduation.domain.XC;
import cn.xaiu.springbootgraduation.service.IHotelService;
import cn.xaiu.springbootgraduation.service.ITravelRouteService;
import cn.xaiu.springbootgraduation.service.IXCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class BarController {
    @Autowired
    private ITravelRouteService iTravelRouteService;
    @Autowired
    private IHotelService iHotelService;
    @Autowired
    private IXCService ixcService;
    @GetMapping("/page/information")
    public String information(Model model){
        Map<String,Object> map=new HashMap<>();
        this.editPath(map);

        model.addAttribute("map",map);

        return "page/information";
    }
    @GetMapping("/page/index")
    public String index(Model model, HttpServletRequest request){
    log.info("进入index方法");
        HttpServletMapping httpServletMapping = request.getHttpServletMapping();
        System.out.println(httpServletMapping);
        String name = request.getParameter("name");
        if("".equals(name) || name==null) {
            Map<String,Object> map=new HashMap<>();
            this.editPath(map);
            model.addAttribute("map",map);

    }else {
        TravelRoute oneByName = iTravelRouteService.getOneByName(name);
            Map<String,Object> map=new HashMap<>();
            String[] path = oneByName.getImagePath().split(",");
            map.put(path[0],oneByName);
            model.addAttribute("map", map);
    }
        return "page/index";
    }
    @GetMapping("/page/ticket")
    public String ticket(Model model){
        List<Hotel> hotels = iHotelService.listAll();
        Map<String,Object> map=new HashMap<>();
        for (Hotel hotel:hotels){

            String str = hotel.getHotelImage();
            String[] im = str.split(",");
            map.put(im[0],hotel);

        }

        model.addAttribute("map",map);

        return "page/ticket";
    }
    @GetMapping("/page/xsHidden")
    public String xsHidden(Model model){
        List<XC> xcs = ixcService.listAll();
        Map<String,Object> map=new HashMap<>();
        for (XC xc:xcs){

            String str = xc.getPath();
            String[] im = str.split(",");
           map.put(im[0],xc);

        }

        model.addAttribute("map",map);
        return "page/scenery";
    }
    @GetMapping("/page/about")
    public String about(){

        return "page/about";
    }
    @RequestMapping("/page/loginPage")
    public String login(){
      log.info("进入loginPage方法");
        return "page/login";
    }
    @RequestMapping("/page/registerPage")
    public String registerPage(){
        log.info("进入registerPage方法");
        return "page/register";
    }
    @RequestMapping("/page/particulars")
    public String particulars(Model model,@RequestParam("routeId") String routeId){

        log.info("进入registerPage方法,查询的routeId为"+routeId);

        TravelRoute travel = iTravelRouteService.getOne(routeId);
        String[] img = travel.getImagePath().split(",");
        List<String> list=new ArrayList<>();
        for (int i=0;i<img.length;i++){
            list.add(img[i]);
        }
        model.addAttribute("list",list);
        model.addAttribute("travle",travel);
        model.addAttribute("first",img[0]);

        return "page/particulars";
    }
    @RequestMapping("/main")
    public String main(){

        return "main/index";
    }
    @ResponseBody
    @RequestMapping("/main/check")
    public Map check(){
        System.out.println("...........");
      boolean flag=true;
      Map<String,Object> map =new HashMap<>();
      map.put("msg",flag);
        return map;
    }


    public void editPath(Map<String,Object> map){
        List<TravelRoute> travelRoutes = iTravelRouteService.listAll();
        for (TravelRoute travelRoute:travelRoutes){

            String str = travelRoute.getImagePath();
            String[] im = str.split(",");
            map.put(im[0],travelRoute);

        }


    }

}
