package com.white.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.white.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author duanlsh
 * @description Jwt token信息
 * @date 2019/7/16
 */
@Component
@Slf4j
public class JwtUtil {

    @Autowired
    private ConfigUtil configUtil;

    /**
     * @author duanlsh
     * @description 生成 jwt token
     * @date 2019/7/16
     * @return java.lang.String
     **/
    public String generateToken(String userName, int userId){
        String token = JWT.create()
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withIssuer(configUtil.getJwtIssuer())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.JWT_TTL_MILLI))
                .sign(Algorithm.HMAC256(configUtil.getJwtSign()));
        return token;
    }

    /**
     * @author duanlsh
     * @description token 验证
     * @date 2019/7/16
     * @return java.util.Map<java.lang.String,com.auth0.jwt.interfaces.Claim>
     **/
    public Map<String, Claim> verifyToken(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(configUtil.getJwtSign())).build();
        DecodedJWT jwt = null;
        try {
            jwt = jwtVerifier.verify(token);
        } catch (Exception ex) {
            log.info(">>> token: {} verfyToken error", token, ex);
        }
        return jwt == null ? new HashMap<>(0) : jwt.getClaims();
    }


    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken("zhangsan", 12);
        System.out.println(token);

        System.out.println(jwtUtil.verifyToken(token));
    }

}
