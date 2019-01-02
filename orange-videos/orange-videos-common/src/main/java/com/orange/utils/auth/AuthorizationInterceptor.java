package com.orange.utils.auth;

import com.orange.utils.enums.ResultEnum;
import com.orange.utils.exception.MyException;
import com.orange.utils.redis.RedisOperator;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    RedisOperator redis;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        //如果打上了AuthToken注解则需要验证token
        if(method.getAnnotation(AuthToken.class) != null || ((HandlerMethod) handler).getBeanType().getAnnotation(AuthToken.class) != null){
            String token = httpServletRequest.getHeader("authToken");
            String userid = httpServletRequest.getHeader("authUserId");
            if (StringUtils.isBlank(token) || StringUtils.isBlank(userid)){
                throw new MyException(ResultEnum.HTTPHEADER_ERROR);
            }
            String userToken = redis.get(userid);
            //System.out.println("token的值为："+userToken);
            if(!StringUtils.isBlank(userToken) && userToken.equals(token)){
                long ttl = redis.ttl(userid);
                long resettime = 60*60*24;
                long expiretime = 60*60*24*7;
                if(ttl<resettime){
                    redis.expire(userid,expiretime);
                }
                return true;
            }else{
                throw new MyException(ResultEnum.TOKEN_ERROR);
            }
        }
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }
}
