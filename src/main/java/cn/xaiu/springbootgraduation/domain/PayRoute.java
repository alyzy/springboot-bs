package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="pay_route")
public class PayRoute {
    private Long out_trade_no;
    private String routeId;

}
