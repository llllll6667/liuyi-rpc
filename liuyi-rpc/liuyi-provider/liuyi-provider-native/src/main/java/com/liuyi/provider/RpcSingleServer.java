package com.liuyi.provider;

import com.liuyi.provider.common.server.base.BaseServer;
import com.liuyi.rpc.common.scanner.server.RpcServiceScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcSingleServer extends BaseServer {
    private final Logger logger = LoggerFactory.getLogger(RpcSingleServer.class.getName());

    public RpcSingleServer(String serverAddress,String sacnPackage) {
        //调用父类构造方法
        super(serverAddress);

        try {
            this.handlerMap = RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService(sacnPackage);
        } catch (Exception e) {
            logger.error("Rpc Server init error",e);
        }
    }
}
