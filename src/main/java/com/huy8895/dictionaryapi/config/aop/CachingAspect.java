package com.huy8895.dictionaryapi.config.aop;

import com.google.gson.Gson;
import com.huy8895.dictionaryapi.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class CachingAspect {
    private final RedisService redisService;
    private final CacheService cacheService;
    private final Gson gson;

    @Around("@annotation(RedisCache)")
    public Object RedisCache(ProceedingJoinPoint joinPoint) throws Throwable {

        // get the current method that is called , we need this to extract the method name
        Method method = getCurrentMethod(joinPoint);

        // Get all the method params
        Object[] parameters = joinPoint.getArgs();

        // Use the method name and params to create a key
        String key = CacheKeyGenerator.generateKey(method.getName(),parameters);

        // call cache service to get the value for the given key if not execute  method to get the return object to be cached
        Object returnObject = cacheService.cacheGet(key, method.getReturnType());
        if (returnObject != null)
            return returnObject;

        // execute method to get the return object
        returnObject = joinPoint.proceed(parameters);

        RedisCache RedisCache = method.getAnnotation(RedisCache.class);

        // cache the method return object to redis cache with the key generated
        cacheService.cachePut(key, gson.toJson(returnObject), RedisCache.expire());

        return returnObject;
    }

    private Method getCurrentMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
    
}
