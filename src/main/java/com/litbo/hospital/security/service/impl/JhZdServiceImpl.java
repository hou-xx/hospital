package com.litbo.hospital.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.litbo.hospital.result.Result;
import com.litbo.hospital.security.bean.JhZd;
import com.litbo.hospital.security.dao.JhZdDao;
import com.litbo.hospital.security.service.JhZdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zjc
 * @create 2018-11-29 14:52
 * 计划service层
 */
@Service
public class JhZdServiceImpl implements JhZdService {

    @Autowired
    private JhZdDao jhZdDao;

    /**
     * 计划添加
     * @param jhZd
     * @return
     */
    @Override
    @Transactional
    public Result addJhZd(JhZd jhZd) {
        int i = jhZdDao.addJhZd(jhZd);
        if(i>0){
            return Result.success();
        }
        return Result.error("添加失败");
    }

    /**
     * 假话查询
     * @return
     */
    public Result listJhZd(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<JhZd> pageInfo = new PageInfo<>(jhZdDao.jhzdList());
        return Result.success(pageInfo);
    }
}
