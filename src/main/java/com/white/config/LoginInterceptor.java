package com.white.config;

import com.auth0.jwt.interfaces.Claim;
import com.white.bean.vo.SimpleUserVo;
import com.white.constants.Constants;
import com.white.enums.WhiteExceptionEnum;
import com.white.util.JwtUtil;
import com.white.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("error")) {
            return true;
        }
        String token = request.getHeader(Constants.HEADER_TOKEN);
        System.out.println(requestURI);
        ValidateUtil.isTrue(StringUtils.isEmpty(token), WhiteExceptionEnum.HEADER_TOKEN_ERROR);
        System.out.println(">> " + token);
        SimpleUserVo simpleUserVo = Constants.sessionMap.get(token);
        ValidateUtil.isTrue(simpleUserVo == null, WhiteExceptionEnum.USER_LOGIN_EXPIRE);
        System.out.println("------------");
        Constants.put(simpleUserVo);
        //查看token是否过期
        jwtUtil.verifyToken(token);


        Constants.put(simpleUserVo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
