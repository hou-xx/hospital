package com.litbo.hospital.lifemanage.checkBeforeUse.service;

import com.github.pagehelper.PageInfo;
import com.litbo.hospital.lifemanage.checkBeforeUse.vo.*;
import com.litbo.hospital.operational_data_monitoring.software_interface.vo.EqInfoVO;

import java.util.List;


public interface SpecificationService {
    PageInfo<EqInfoVO> searchEqInfos(Integer pageNum, Integer pageSize);

    PageInfo<UserVo> searchUsers(Integer pageNum,Integer pageSize);

    PageInfo<UserVo> searchAppointUsers(Integer pageNum,Integer pageSize,String name);

    PageInfo<EqInfoVO2> searchAppointEqInfos(Integer pageNum, Integer pageSize, String sbbh, String eqName, String pym, String bmName);

    Integer saveStandard(SaveStandardVO saveStandardVO);

    Integer saveStandardProject(Integer standardId, List<SaveStandardProjectVO> list);

    PageInfo<SearchStandardVO> searchAllStandards(Integer pageNum, Integer pageSize,Integer result);

    SearchStandardVO searchStandard(Integer standardId);

    String updateStandardResult(Integer standardId,Integer result);

}
