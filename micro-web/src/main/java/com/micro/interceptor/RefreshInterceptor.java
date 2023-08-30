package com.micro.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.micro.constant.RedisConstant;
import com.micro.entity.UUser;
import com.micro.utils.ThreadUserUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RefreshInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;

    public RefreshInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    /*
    *
     * note redis中的用户信息续约
     * @date: 2023-08-26 10:51
     * @param:
     * @return:
     **/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // note 获取请求头中的token
        String token = request.getHeader("authorization");

        log.info("RefreshInterceptor，过滤器token,{}, {}", token, request.getRequestURI());
        if (StrUtil.isBlank(token)) {
            return true;
        }
        // note 获取redis中的用户信息
        String key = RedisConstant.LOGIN_USER_INFO + token;
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
        if (map.isEmpty()) {
            return true;
        }

        stringRedisTemplate.expire(key, RedisConstant.USER_TTL, TimeUnit.HOURS);
        return true;
    }
}
