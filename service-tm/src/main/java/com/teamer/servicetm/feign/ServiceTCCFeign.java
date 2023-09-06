package com.teamer.servicetm.feign;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 使用TCC模式往tcc服务插入一条记录
 *
 * @author tanzj
 */
@FeignClient(value = "rm-tcc")
@LocalTCC
public interface ServiceTCCFeign {

    /**
     * 往tcc服务插入一条记录
     *
     * @param params -name
     * @return String
     */
    @PostMapping(value = "/tcc-insert")
    String insertTCC(Map<String, String> params);

    @PostMapping(value = "/tcc-update")
    String updateTCC(Map<String, String> params);

}
