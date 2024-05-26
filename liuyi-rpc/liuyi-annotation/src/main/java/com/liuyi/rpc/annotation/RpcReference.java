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
     * 接口的class
     */
    Class<?> interfaceClass() default Void.class;

    /**
     * 接口的class名称
     */
    String interfaceClassName() default "";

    /**
     * 版本号
     */
    String version() default "";

    /**
     * 服务分组
     */
    String group() default "";

}
