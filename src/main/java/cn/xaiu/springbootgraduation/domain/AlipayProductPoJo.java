package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Table(name="alipayproduct")
public class AlipayProductPoJo {
    private Long outTradeNo;
    private Long totalAmount;
    private String subject;
    private String body;
    private Long tradeNo;



    private Date createDate;


    private Date payDate;
    //A为支付，B为待支付
    private String sts;
}
