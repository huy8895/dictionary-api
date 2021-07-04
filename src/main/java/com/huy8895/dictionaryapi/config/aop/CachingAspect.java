package com.huy8895.dictionaryapi.config.aop;

import com.huy8895.dictionaryapi.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class CachingAspect {
    private final RedisService redisService;
    @Before(value = "@annotation(com.huy8895.dictionaryapi.config.aop.RedisCache)")
    public void before(JoinPoint j){

    }

    @After(value = "@annotation(com.huy8895.dictionaryapi.config.aop.RedisCache)")
    public void after(JoinPoint j){

    }
}
