/*
package cn.xaiu.springbootgraduation;


import cn.com.webxml.TrainTimeWebService;
import cn.com.webxml.TrainTimeWebServiceSoap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootGraduationApplicationTests {

    @Test
    void contextLoads() throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date currdate = format.parse("2018-10-10 15:33:25");


        System.out.println("现在的日期是：" + currdate);
                Calendar ca = Calendar.getInstance();
                 ca.add(Calendar.DATE, 10);// num为增加的天数，可以改变的
        System.out.println(ca.get(Calendar.DATE));
        System.out.println(ca.getTime());
                 currdate = ca.getTime();
                 String enddate = format.format(currdate);
               System.out.println("增加天数以后的日期：" + enddate);

    }
    @Test
    void test1() throws  Exception{
        String os = System.getProperty("os.name");


        System.out.println(os);

    }
    @Test
      void test123()  throws Exception{


        String url="http://ws.webxml.com.cn/WebServices/TrainTimeWebService.asmx/getStationAndTimeByStationName?StartStation="+"西安"+"ArriveStation="+"宝鸡";



        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);


httpPost.setHeader("Content-Type ","text/xml; charset=utf-8"
);
        // 创建post方式请求对象
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));

                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    class User{

        private  String StartStation="西安";
        private  String ArriveStation="宝鸡";

    }
    @Test
    void ghg() throws Exception{

        TrainTimeWebService trainTimeWebService = new TrainTimeWebService();
        TrainTimeWebServiceSoap trainTimeWebServiceSoap = trainTimeWebService.getTrainTimeWebServiceSoap();
        String versionTime = trainTimeWebServiceSoap.getVersionTime();
        trainTimeWebServiceSoap.getStationAndTimeByStationName("西安","宝鸡","");
        System.out.println(versionTime);
    }


}
*/
