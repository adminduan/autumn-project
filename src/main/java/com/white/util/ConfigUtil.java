package com.white.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author duanlsh
 * @description TODO
 * @date 2019/7/16
 */
@Component
@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class ConfigUtil {

    //签名
    private String jwtIssuer;
    //签名
    private String jwtSign;

}
