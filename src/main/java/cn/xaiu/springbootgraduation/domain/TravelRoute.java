package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@Table(name="travelroute")
public class TravelRoute {
    private String routeId;


    //pid为1为国内线路，2为省内线路
    private Integer tid;

    public String getKind() {
        if(this.tid==1){
            this.kind="国内线路";
        }else{
            this.kind="省内线路";
        }
        return kind;
    }

    @Transient
    private String kind;
    private String imagePath;
    private String travelRouteName;
    //促销活动
    private String promotionActivity;
    private Date closeDate;
    private Long originalPrice;
    //现价
    private Long currentPrice;

    public TravelRoute() {
    }

    public TravelRoute(String routeId, Integer tid, String imagePath, String travelRouteName, String promotionActivity, Date closeDate, Long originalPrice, Long currentPrice) {
        this.routeId = UUID.randomUUID().toString();
        this.tid = tid;
        this.kind = kind;
        this.imagePath = imagePath;
        this.travelRouteName = travelRouteName;
        this.promotionActivity = promotionActivity;
        //---------------------------------------------
        Calendar ca = Calendar.getInstance();
        ca.setTime(closeDate);
        ca.add(Calendar.HOUR_OF_DAY	, 24*10+1);
        closeDate = ca.getTime();
        this.closeDate = closeDate;

        //---------------------------------------------
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
    }

    public void setCloseDate(Date closeDate) {

    }
}

