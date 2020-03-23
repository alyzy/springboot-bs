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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.UUID;
@Slf4j
@Controller
@SuppressWarnings("ALL")
public class UploadController {
    @Autowired
    private ITravelRouteService iTravelRouteService;
    @Autowired
    private IHotelService iHotelService;
    @Autowired
    private IXCService ixcService;
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "file") MultipartFile[] multipartFiles,
                         HttpServletRequest request) {

       /* if(multipartFile.length == 0) {
            return "404";
        }*/
        String routeId = request.getParameter("routeId");
        String travelRouteName = request.getParameter("travelRouteName");

        //------------------------------------------------------------------------
        //得到源文件名称
        StringBuilder sb=new StringBuilder();
        for (MultipartFile multipartFile:multipartFiles){
            String originalFilename = multipartFile.getOriginalFilename();
            String str = originalFilename.substring(originalFilename.lastIndexOf("."));

            String filename= UUID.randomUUID().toString()+str;

            log.info("生成的文件名称为："+filename);
            String path = new String("src/main/resources/static/img/upload/");

            // 目录不存在新建目录
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            log.info("上传文件夹的路径为："+filePath.getAbsolutePath());
            try {
                // 转存文件
                File newFile = new File(filePath.getAbsolutePath() + File.separator + filename);

                multipartFile.transferTo(newFile);
                sb.append(",").append("/img/upload/").append(filename);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }




        if(StringUtils.isEmpty(routeId)){
            String promotionActivity = request.getParameter("promotionActivity");
            Long originalPrice = Long.valueOf(request.getParameter("originalPrice"));
            Long currentPrice = Long.valueOf(request.getParameter("currentPrice"));

            iTravelRouteService.addOne(new TravelRoute("",2,sb.toString(),travelRouteName,promotionActivity,new Date(),originalPrice,currentPrice));

        }
        System.out.println(sb.toString());
       // iTravelRouteService.updateOne();
        return "redirect:/main/adminList";
    }

    /**
     * 酒店
     * @param multipartFiles
     * @param request
     * @return
     */
    @PostMapping(value = "/upload/hotel")
    public String uploadHotel(@RequestParam(value = "file") MultipartFile[] multipartFiles,
                         HttpServletRequest request) {

       /* if(multipartFile.length == 0) {
            return "404";
        }*/
        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");

        //------------------------------------------------------------------------
        //得到源文件名称
        StringBuilder sb=new StringBuilder();
        log.info("length"+multipartFiles.length);
        for (MultipartFile multipartFile:multipartFiles){
            String originalFilename = multipartFile.getOriginalFilename();
            String str = originalFilename.substring(originalFilename.lastIndexOf("."));

            String filename= UUID.randomUUID().toString()+str;

            log.info("生成的文件名称为："+filename);
            String path = new String("src/main/resources/static/img/upload/");

            // 目录不存在新建目录
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            log.info("上传文件夹的路径为："+filePath.getAbsolutePath());
            try {
                // 转存文件
                File newFile = new File(filePath.getAbsolutePath() + File.separator + filename);

                multipartFile.transferTo(newFile);
                sb.append("/img/upload/").append(filename).append(",");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }




        if(StringUtils.isEmpty(hotelId)){
            String hotelXq = request.getParameter("hotelXq");
            Long price = Long.valueOf(request.getParameter("price"));
            String addr = request.getParameter("addr");


            iHotelService.addOne(new Hotel("",hotelName,hotelXq,price,sb.toString(),addr));

            return "redirect:/main/adminHotelList";
        }
        System.out.println(sb.toString());
        iTravelRouteService.updateOne(hotelId,sb.toString());
        return "redirect:/main/adminHotelList";
    }
    @PostMapping(value = "/upload/xc")
    public String uploadXC(@RequestParam(value = "file") MultipartFile[] multipartFiles,
                              HttpServletRequest request) {

       /* if(multipartFile.length == 0) {
            return "404";
        }*/
        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");

        //------------------------------------------------------------------------
        //得到源文件名称
        StringBuilder sb=new StringBuilder();
        log.info("length"+multipartFiles.length);
        for (MultipartFile multipartFile:multipartFiles){
            String originalFilename = multipartFile.getOriginalFilename();
            String str = originalFilename.substring(originalFilename.lastIndexOf("."));

            String filename= UUID.randomUUID().toString()+str;

            log.info("生成的文件名称为："+filename);
            String path = new String("src/main/resources/static/img/upload/");

            // 目录不存在新建目录
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            log.info("上传文件夹的路径为："+filePath.getAbsolutePath());
            try {
                // 转存文件
                File newFile = new File(filePath.getAbsolutePath() + File.separator + filename);

                multipartFile.transferTo(newFile);
                sb.append("/img/upload/").append(filename).append(",");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }




        if(StringUtils.isEmpty(hotelId)){
            String xname = request.getParameter("xname");
            Long price = Long.valueOf(request.getParameter("price"));
            String hotplace = request.getParameter("hotplace");


            ixcService.addOne(new XC(xname,hotplace,price,"",sb.toString()));

            return "redirect:/main/adminHotelList";
        }
        System.out.println(sb.toString());
        //ixcService.updateOne(hotelId,sb.toString());
        return "redirect:/main/adminHotelList";
    }




}
