package cn.xaiu.springbootgraduation.controller;


import cn.xaiu.springbootgraduation.dao.IHotelDao;
import cn.xaiu.springbootgraduation.dao.IXCDao;
import cn.xaiu.springbootgraduation.domain.AlipayProductPoJo;
import cn.xaiu.springbootgraduation.domain.Hotel;
import cn.xaiu.springbootgraduation.domain.TravelRoute;
import cn.xaiu.springbootgraduation.domain.User;
import cn.xaiu.springbootgraduation.domain.XC;
import cn.xaiu.springbootgraduation.service.IAlipayService;
import cn.xaiu.springbootgraduation.service.IHotelService;
import cn.xaiu.springbootgraduation.service.ITravelRouteService;
import cn.xaiu.springbootgraduation.service.IXCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private IAlipayService iAlipayService;
    @Autowired
    private IHotelService iHotelService;
    @Autowired
    private ITravelRouteService iTravelRouteService;
    @Autowired
    private IXCService ixcService;
    @RequestMapping("/main/fav")
    public String mainFav(){

        return "main/fav";
    }
    @RequestMapping("/main/pay")
    public String mainAdd(Model model, HttpServletRequest request){

        User users = (User) request.getSession().getAttribute("users");
        String name = request.getParameter("rolename");
        if("".equals(name) || name==null) {
            List<AlipayProductPoJo> alipayProductPoJos = iAlipayService.listAll(users.getUsername());

            model.addAttribute("alipayProductPoJos", alipayProductPoJos);
        }else {
            AlipayProductPoJo alipayProductPoJo = iAlipayService.getOneByName(name);
            model.addAttribute("alipayProductPoJos", alipayProductPoJo);
        }
        return "main/pay";
    }
    @RequestMapping("/main/editTravel")
    public String mainEdit(@RequestParam("routeId") String routeId,Model model){
        TravelRoute TravelRoute = iTravelRouteService.getOne(routeId);
        model.addAttribute("TravelRoute",TravelRoute);
        return "main/edit/edit";
    }



    @RequestMapping("/main/adminList")
    public String adminList(Model model,HttpServletRequest request){
        log.info("进入管理员界面的景区管理界面。。。");
        String name = request.getParameter("roleName");

        if("".equals(name) || name==null) {
            List<TravelRoute>    travelRoute = iTravelRouteService.listAll();
            model.addAttribute("travelRoutes", travelRoute);
        }else {
            TravelRoute     travelRoute = iTravelRouteService.getOneByName(name);
            model.addAttribute("travelRoutes", travelRoute);
        }
        return "main/travelAdmin";
    }

    @RequestMapping("/main/addTravel")
    public String addTravel(){
        log.info("进入addTravel方法。。。");

        return "main/add/addTravel";
    }
    //酒店管理模块------------------------------------------------------------------------
    @RequestMapping("/main/adminHotelList")
    public String adminHotelList( Model model,HttpServletRequest request){
        log.info("进入管理员界面的酒店管理界面。。。");
        String name = request.getParameter("roleName");

        if("".equals(name) || name==null) {
            List<Hotel>    hotel = iHotelService.listAll();
            model.addAttribute("hotels", hotel);
        }else {
            Hotel     hotel = iHotelService.getOneByName(name);
            model.addAttribute("hotels", hotel);
        }

        return "main/adminHotelList";


    }
    @RequestMapping("/main/editHotel")
    public String editHotel(@RequestParam("routeId") String routeId,Model model){
        TravelRoute TravelRoute = iHotelService.getOne(routeId);
        model.addAttribute("TravelRoute",TravelRoute);
        return "main/edit/editHotel";
    }
    @RequestMapping("/main/addHotel")
    public String addHotel(){
        log.info("进入addHotel方法。。。");

        return "main/add/addHotel";
    }
    //美食小吃-------------------------------------------------------------------------------------
    @RequestMapping("/main/adminXCList")
    public String adminXCList( Model model,HttpServletRequest request){
        log.info("进入管理员界面的美食小吃管理界面。。。");
        String name = request.getParameter("roleName");

        if("".equals(name) || name==null) {
            List<XC>    xc = ixcService.listAll();
            model.addAttribute("xcs", xc);
        }else {
            XC     xc = ixcService.getOneByName(name);
            model.addAttribute("xcs", xc);
        }

        return "main/adminXCList";


    }
    @RequestMapping("/main/addXC")
    public String addXC(){
        log.info("进入addXC方法。。。");

        return "main/add/addXC";
    }
}
