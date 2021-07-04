package com.huy8895.dictionaryapi.config.aop;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(0)
public @interface RedisCache {
}