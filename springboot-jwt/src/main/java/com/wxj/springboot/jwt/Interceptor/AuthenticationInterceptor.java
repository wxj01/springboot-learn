package com.wxj.springboot.jwt.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wxj.springboot.jwt.annotation.CheckToken;
import com.wxj.springboot.jwt.annotation.PassToken;
import com.wxj.springboot.jwt.entity.User;
import com.wxj.springboot.jwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 14:09
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserInfoService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String token = request.getHeader("token"); // 从http 请求头取出 token
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        // 检查是否有passToken 注释 ，有则无需进行token 校验
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken annotation = method.getAnnotation(PassToken.class);
            if(annotation.required()){
                return true;
            }
        }

        // 检查有没有CheckToken 的 注释
        if(method.isAnnotationPresent(CheckToken.class)){
            CheckToken annotation = method.getAnnotation(CheckToken.class);
            if(annotation.required()){
                // 执行认证
                if(token == null){
                    throw  new RuntimeException("无token,请重新登录");
                }

                // 获取 token 中的 user id
                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }catch (Exception e){
                    throw new RuntimeException("您的token已坏掉了,请重新登录获取token");
                }

                User user = userService.getUserInfoById(Integer.valueOf(userId));
                if(user == null){
                    throw new RuntimeException("用户不存在,请重新登录");
                }

                // 验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUI_PASSWORD())).build();
                try{
                    jwtVerifier.verify(token);
                }catch (InvalidClaimException e){
                    throw new RuntimeException("无效token,请重新登录获取token");
                }catch (TokenExpiredException e){
                    throw new RuntimeException("token已过期,请重新登录获取token");
                }catch (JWTVerificationException e){
                    throw new RuntimeException(e.getMessage());
                }
                return true;
            }

        }
        return true;
    }

}