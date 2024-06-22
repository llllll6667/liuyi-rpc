package com.liuyi.rpc.common.scanner.server;

import com.liuyi.rpc.annotation.RpcService;
import com.liuyi.rpc.common.scanner.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RpcServiceScanner extends ClassScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServiceScanner.class);

    /**
     * 扫描指定包下的类，并筛选使用@RpcService注解标注的类
     */
    public static Map<String,Object> doScannerWithRpcServiceAnnotationFilterAndRegistryService(
            /*String host,int port,*/String scanPackage /*,RegistryService registryService*/) throws Exception{
        HashMap<String, Object> handlerMap = new HashMap<>();
        List<String> classNameList = getClassNameList(scanPackage,true);
        if(classNameList == null || classNameList.isEmpty()){
            return handlerMap;
        }
        classNameList.stream().forEach((className) -> {
            try {
                Class<?> clazz = Class.forName(className);
                RpcService rpcService = clazz.getAnnotation(RpcService.class);
                if(rpcService != null){
                    //TODO 向注册中心注册服务元数据
                    LOGGER.info("@RpcService的类实例名称:{}",clazz.getName());
                    String serviceName = getServiceName(rpcService);
                    String key = serviceName.concat(rpcService.version()).concat(rpcService.group());
                    handlerMap.put(key,clazz.newInstance());
                }
            } catch (Exception e) {
                LOGGER.error("scan classes throws exception:{}",e);
            }
        });
        return handlerMap;
    }


    /**
     * 获取serviceName
     */
    private static String getServiceName(RpcService rpcService){
        //优先使用interfaceClass
        Class clazz = rpcService.interfaceClass();
        if (clazz == null || clazz == void.class){
            return rpcService.interfaceClassName();
        }
        String serviceName = clazz.getName();
        if (serviceName == null || serviceName.trim().isEmpty()){
            serviceName = rpcService.interfaceClassName();
        }
        return serviceName;
    }
}
