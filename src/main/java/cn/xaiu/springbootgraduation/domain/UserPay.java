package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="user_pay")
public class UserPay {
    private String username;
    private Long out_trade_no;

}
