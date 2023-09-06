package com.teamer.servicetcc.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.util.Map;

/**
 * 这里定义tcc的接口
 * 一定要定义在接口上
 * 我们使用springCloud的远程调用
 * 那么这里使用LocalTCC便可
 *
 * @author tanzj
 */
@LocalTCC
public interface TccService {

    /**
     * 定义两阶段提交
     * name = 该tcc的bean名称,全局唯一
     * commitMethod = commit 为二阶段确认方法
     * rollbackMethod = rollback 为二阶段取消方法
     * BusinessActionContextParameter注解 传递参数到二阶段中
     *
     * @param params  -入参
     * @return String
     */
    @TwoPhaseBusinessAction(name = "insert", commitMethod = "commitTcc", rollbackMethod = "cancel", useTCCFence = true)
    String insert(
            @BusinessActionContextParameter(paramName = "params") Map<String, Object> params
    );

    /**
     * 确认方法、可以另命名，但要保证与commitMethod一致
     * context可以传递try方法的参数
     *
     * @param context 上下文
     * @return boolean
     */
    boolean commitTcc(BusinessActionContext context);

    /**
     * 二阶段回滚方法
     *
     * @param context 上下文
     * @return boolean
     */
    boolean cancel(BusinessActionContext context);


    @TwoPhaseBusinessAction(name = "update", commitMethod = "commitTccUpdate", rollbackMethod = "cancelUpdate", useTCCFence = true)
    String update(Map<String, String> params);

    boolean commitTccUpdate(BusinessActionContext context);

    /**
     * 二阶段回滚方法
     *
     * @param context 上下文
     * @return boolean
     */
    boolean cancelUpdate(BusinessActionContext context);
}
