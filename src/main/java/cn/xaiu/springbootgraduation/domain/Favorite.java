package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Favorite {

    private Long fids;
    private String username;
    private String travle_route_name;
    private Date scsj;
    private Long xj;
}
