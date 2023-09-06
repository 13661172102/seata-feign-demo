package com.teamer.serviceat.service.impl;

import com.teamer.serviceat.dao.AtDAO;
import com.teamer.serviceat.service.AtService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AtServiceImpl implements AtService {
    protected static final Logger log = LoggerFactory.getLogger(AtServiceImpl.class);

    @Autowired
    AtDAO atDAO;

    @Override
    public String insert(Map<String, String> params) {
        log.info("------------------> xid = " + RootContext.getXID());
        atDAO.insert(params);
        return "success";
    }

    @Override
    public String update(Map<String, String> params) {
        throw new RuntimeException();
    }
}
