package com.litbo.hospital.security.service.impl;


import com.github.pagehelper.PageInfo;
import com.litbo.hospital.security.bean.FwFp;
import com.litbo.hospital.security.dao.FwFpDao;
import com.litbo.hospital.security.service.FwFpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FwFpServiceImpl implements FwFpService {
    @Autowired
    private FwFpDao fwFpDao;
    @Override
    public PageInfo listFwFpByWaitExamine() {
        return new PageInfo(fwFpDao.listFwFpByWaitExamine());
    }

    @Override
    public Integer insertFwFp(FwFp fp) {

        return fwFpDao.insertFwFp(fp);
    }
}
