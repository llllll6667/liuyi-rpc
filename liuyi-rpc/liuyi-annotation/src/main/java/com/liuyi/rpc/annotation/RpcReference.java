package com.liuyi.rpc.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcReference {

    /**
     * 版本号
     */
    String version() default "1.0.0";

    /**
     * 注册中心类型
     */
    String registryType() default "zookeeper";

    /**
     * 注册中心地址
     */
    String registryAddress() default "127.0.0.1:2181";

    /**
     * 负载均衡类型
     */
    String loadBalanceType() default "zkconsistenthash";

    /**
     * 序列化类型
     */
    String serializationType() default "protosbuff";

    /**
     * 超时时间
     */
    long timeout() default 5000;

    /**
     * 是否异步执行
     */
    boolean async() default false;

    /**
     * 是否单向调用
     */
    boolean oneWay() default false;

    /**
     * 代理类型
     */
    String proxy() default "jdk";

    /**
     * 服务分组
     */
    String group() default "";


}
