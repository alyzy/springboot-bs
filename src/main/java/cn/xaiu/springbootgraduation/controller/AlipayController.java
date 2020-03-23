package cn.xaiu.springbootgraduation.controller;

import cn.xaiu.springbootgraduation.domain.AlipayProductPoJo;
import cn.xaiu.springbootgraduation.domain.AlipayPublicPoJo;
import cn.xaiu.springbootgraduation.domain.TravelRoute;
import cn.xaiu.springbootgraduation.domain.User;
import cn.xaiu.springbootgraduation.service.IAlipayService;
import cn.xaiu.springbootgraduation.service.ITravelRouteService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@Slf4j
public class AlipayController {

    @Autowired
    private AlipayPublicPoJo alipayPoJo;
    @Autowired
    private ITravelRouteService iTravelRouteService;
    @Autowired
    private IAlipayService iAlipayService;

    /**
     * 生成网页二维码的方法
     * @param response
     * @throws Exception
     */
    @RequestMapping("/pay")
    public void pay(HttpServletResponse response,@RequestParam("routeId") String routeId,HttpServletRequest request) throws Exception{
        log.info("支付方法");
        AlipayProductPoJo productPoJo=new AlipayProductPoJo();
        this.getAlipayProductPoJo(routeId,productPoJo,request);
        String par= "{\"out_trade_no\":\""+ productPoJo.getOutTradeNo() +"\","
                + "\"total_amount\":\""+ productPoJo.getTotalAmount() +"\","
                + "\"subject\":\""+ productPoJo.getSubject() +"\","
                + "\"body\":\""+ productPoJo.getBody() +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";

        AlipayClient alipayClient = new DefaultAlipayClient(alipayPoJo.getUrl(), alipayPoJo.getAPP_ID(), alipayPoJo.getAPP_PRIVATE_KEY(), alipayPoJo.getFORMAT(), alipayPoJo.getCHARSET(), alipayPoJo.getALIPAY_PUBLIC_KEY(), alipayPoJo.getSIGN_TYPE()); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:8081/examine");
        //alipayRequest.setNotifyUrl("http://localhost:8080/query");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent(par);//填充业务参数

            ;

        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + alipayPoJo.getCHARSET());
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

    }

    /**
     * 支付宝异步验证方法
     * @param map
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/query")
    public Map<String, Object> query(Map<String,Object> map)  throws Exception{
        map=new HashMap<>();
        AlipayClient alipayClient = null;
        AlipayTradeQueryRequest request = null;
        AlipayTradeQueryResponse response =null;

        try {
            alipayClient=new DefaultAlipayClient(alipayPoJo.getUrl(), alipayPoJo.getAPP_ID(), alipayPoJo.getAPP_PRIVATE_KEY(), alipayPoJo.getFORMAT(), alipayPoJo.getCHARSET(), alipayPoJo.getALIPAY_PUBLIC_KEY(), alipayPoJo.getSIGN_TYPE());
            request = new AlipayTradeQueryRequest();
            request.setBizContent("{" +
                    "    \"out_trade_no\":\"20150320010101015\"," +
                    "    \"trade_no\":\"2020022822001412801000138932\"" +
                    "  }");
            response =   alipayClient.execute(request);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(response.isSuccess()){
            map.put("msg","调用成功");
        } else {



        }
        return map;
    }

    /**
     * 同步成功回调方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/examine")
    public String examine(HttpServletRequest request, HttpServletResponse response) throws Exception{

        log.info("检查方法");
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }


        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayPoJo.getALIPAY_PUBLIC_KEY(),  alipayPoJo.getCHARSET(), alipayPoJo.getSIGN_TYPE()); // 调用SDK验证签名
        //验证签名通过

        if(signVerified){
            // 商户订单号
            String out_trade_no = params.get("out_trade_no");
            System.out.println(out_trade_no);
            // 支付宝交易号
            String trade_no = request.getParameter("trade_no").substring(10);
            String trade_status = request.getParameter("trade_status");
            log.info("支付状态"+trade_status);

            // 付款金额
            String total_amount = request.getParameter("total_amount");
            iAlipayService.updateOne(out_trade_no,trade_no,new Date());
            //支付成功，修复支付状态
            // payService.updateById(Integer.valueOf(out_trade_no));
            return "page/information";//跳转付款成功页面
        }else{
            return "no";//跳转付款失败页面
        }

    }
    private void getAlipayProductPoJo(String routeId, AlipayProductPoJo productPoJo,HttpServletRequest request){
        TravelRoute ts = iTravelRouteService.getOne(routeId);
        productPoJo.setTotalAmount(ts.getCurrentPrice());
        productPoJo.setBody(ts.getTravelRouteName());
        long Out_trade_no=1000000000000000l;
        Out_trade_no = (long) (Math.random() * Out_trade_no);
        productPoJo.setOutTradeNo(Out_trade_no);
        productPoJo.setSubject(ts.getTravelRouteName());
        productPoJo.setSts("B");
        productPoJo.setCreateDate(new Date());
        User user = (User)request.getSession().getAttribute("users");
        iAlipayService.addOnePay(productPoJo,routeId,user);
    }

}
