package com.litbo.hospital.security.service;

import com.github.pagehelper.PageInfo;
import com.litbo.hospital.security.bean.FwFp;

public interface FwFpService {
    PageInfo listFwFpByWaitExamine(int pageNum, int pageSize, String fpHm, String eqName, String wxDh);

    Integer insertFwFp(FwFp fp);

    PageInfo listFwFpByApplyApproval(int pageNum, int pageSize, String fpHm, String eqName, String wxDh);
}
