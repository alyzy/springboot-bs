package cn.xaiu.springbootgraduation.domain;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayPublicPoJo {
    private String url;
    private String APP_ID;
    private String APP_PRIVATE_KEY;
    private String FORMAT;
    private String CHARSET;
    private String ALIPAY_PUBLIC_KEY;
    private String SIGN_TYPE;

}
