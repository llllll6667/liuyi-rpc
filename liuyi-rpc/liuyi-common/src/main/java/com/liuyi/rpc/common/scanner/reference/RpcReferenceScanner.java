package com.liuyi.rpc.common.scanner.reference;

import com.liuyi.rpc.annotation.RpcReference;
import com.liuyi.rpc.common.scanner.ClassScanner;
import javafx.scene.effect.DropShadow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RpcReferenceScanner extends ClassScanner {
    private static final Logger logger = LoggerFactory.getLogger(RpcReferenceScanner.class);



    /**
     * 扫描指定包下的类，并筛选使用@RpcService注解标注的类
     */
    public static Map<String, Object> doScannerWithRpcReferenceAnnotationFilter(/*String host, int port, */ String scanPackage/*, RegistryService registryService*/) throws Exception{
        HashMap<String, Object> handlerMap = new HashMap<>();
        List<String> classNameList = getClassNameList(scanPackage, true);
        if(classNameList == null || classNameList.isEmpty()){
            return handlerMap;
        }
        classNameList.stream().forEach((className) ->{
            try {
                Class<?> clazz = Class.forName(className);
                Field[] declaredFields = clazz.getDeclaredFields();
                Stream.of(declaredFields).forEach((field -> {
                    RpcReference rpcReference = field.getAnnotation(RpcReference.class);
                    if(rpcReference != null){
                        //TODO 处理后序逻辑，将@RpcReference注解标注的接口引用代理对象，放入全局缓存中
                        logger.info("@RpcReference注解扫描信息:{}",clazz.getName());
                    }
                }));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return handlerMap;
    }
}
