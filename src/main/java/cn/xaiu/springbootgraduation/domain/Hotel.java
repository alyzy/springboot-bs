package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Hotel {
    private String hotelId;
    private String hotelName;
    private String hotelXq;
    private Long hotelPrice;
    private String hotelImage;
    private String hotelAddr;

    public Hotel(String hotelId, String hotelName, String hotelXq, Long hotelPrice, String hotelImage, String addr) {
        this.hotelId = UUID.randomUUID().toString();
        this.hotelName = hotelName;
        this.hotelXq = hotelXq;
        this.hotelPrice = hotelPrice;
        this.hotelImage = hotelImage;
        this.hotelAddr = addr;
    }
}
