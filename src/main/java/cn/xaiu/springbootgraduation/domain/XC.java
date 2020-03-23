package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import javax.persistence.Table;
import java.util.UUID;

@Data
@Table(name="xc")
public class XC {
    private String xname;
    private String hotPlace;
    private Long price;
    private String xid;
    private String path;

    public XC(String xname, String hotplace, Long price, String xid,String path) {
        this.xname = xname;
        this.hotPlace = hotplace;
        this.price = price;
        this.xid = UUID.randomUUID().toString();
        this.path=path;
    }
}
