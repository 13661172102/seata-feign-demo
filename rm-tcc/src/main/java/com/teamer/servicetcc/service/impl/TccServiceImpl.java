package com.teamer.servicetcc.service.impl;

import com.teamer.servicetcc.dao.TccDAO;
import com.teamer.servicetcc.service.TccService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class TccServiceImpl implements  TccService {
    protected static final Logger log = LoggerFactory.getLogger(TccServiceImpl.class);

    private int id;
    @Autowired
    TccDAO tccDAO;

    /**
     * tcc服务t（try）方法
     * 根据实际业务场景选择实际业务执行逻辑或者资源预留逻辑
     *
     * @param params - name
     * @return String
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String insert(Map<String, Object> params) {
        log.info("xid = " + RootContext.getXID());
        //params.put("123","456");
        //todo 实际的操作，或操作MQ、redis等
        int affectedRowNum = tccDAO.insert(params);
        if (affectedRowNum != 0){
            this.id = (Integer) params.get("id");
        }

        //放开以下注解抛出异常
        //throw new RuntimeException("服务tcc测试回滚");
        return "success";
    }

    /**
     * tcc服务 confirm方法
     * 若一阶段采用资源预留，在二阶段确认时要提交预留的资源
     *
     * @param context 上下文
     * @return boolean
     */
    @Override
    public boolean commitTcc(BusinessActionContext context) {
        log.info("xid = " + context.getXid() + "提交成功");
        return true;
    }

    /**
     * tcc 服务 cancel方法
     *
     * @param context 上下文
     * @return boolean
     */
    @Override
    public boolean cancel(BusinessActionContext context) {
        log.info("xid = {} 取消成功", context.getXid());
        tccDAO.delete(this.id);
        return true;
    }

    @Override
    public String update(Map<String, String> params) {
        throw new RuntimeException();
        /*params.put("name","dkdkdkd");
        tccDAO.update(params);
        return "success";*/
    }

    @Override
    public boolean commitTccUpdate(BusinessActionContext context) {
        return false;
    }

    @Override
    public boolean cancelUpdate(BusinessActionContext context) {
        return false;
    }
}
