package cn.xaiu.springbootgraduation.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String uname;
    private Long telephone;
    private String sex;
    private String birthday;
    private Date createDate;
    private Long sts;
    private String str;

    public String getStr() {
        if(this.sts==1){
            this.str="[管理员用户]";
        }
        this.str="[普通用户]";
        return str;
    }
}
