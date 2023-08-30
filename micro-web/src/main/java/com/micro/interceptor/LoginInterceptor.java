package com.micro.interceptor;

import com.micro.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /*
    *
     * note 登录拦截器，查看用户是否登录
     * @date: 2023-08-25 22:08
     * @param:
     * @return:
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        String uid = request.getHeader("uid");
        log.info("loginInterceptor，用户token:{}, 用户ID：{}", token, uid);

        String key = RedisConstant.LOGIN_USER_INFO + token;
        String id = (String)stringRedisTemplate.opsForHash().get(key, "id");
        if (!uid.equals(id)){
            response.setStatus(123456);
            return false;
        }
        return true;
    }
}
