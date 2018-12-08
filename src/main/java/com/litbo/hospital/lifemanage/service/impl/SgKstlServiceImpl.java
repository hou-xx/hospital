package com.litbo.hospital.lifemanage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.litbo.hospital.lifemanage.bean.SgKstl;
import com.litbo.hospital.lifemanage.bean.vo.SgKstlVO;
import com.litbo.hospital.lifemanage.dao.SgInfoMapper;
import com.litbo.hospital.lifemanage.dao.SgKstlMapper;
import com.litbo.hospital.lifemanage.dao.SgKstlUserMapper;
import com.litbo.hospital.lifemanage.dao.SgTlPmMapper;
import com.litbo.hospital.lifemanage.service.SgKstlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 科室讨论表Service实现类
 */
@Service
public class SgKstlServiceImpl implements SgKstlService {
    @Autowired
    private SgKstlMapper sgKstlMapper;
    @Autowired
    private SgKstlUserMapper sgKstlUserMapper;
    @Autowired
    private SgTlPmMapper sgTlPmMapper;
    @Autowired
    private SgInfoMapper sgInfoMapper;

    /**
     * 添加科室讨论信息
     *
     * @param sgKstl  科室讨论实体对象
     * @param usersId 参加讨论的人员集合
     * @param pmsId   设备品名ID集合
     * @return 添加是否成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean insertSgKstl(SgKstl sgKstl, List<String> usersId, List<Integer> pmsId) {
        //添加讨论表id
        sgKstl.setKstlId(UUID.randomUUID().toString());
        Integer integer = sgKstlMapper.insertSgKstl(sgKstl);

        int sgKstlUserNum = 0;
        for (String userId : usersId) {
            sgKstlUserNum += sgKstlUserMapper.insertSgKstlUser(sgKstl.getKstlId(), userId);
        }
        int sgTlPmNum = 0, sgInfoNum = 0;
        for (Integer pmId : pmsId) {
            sgTlPmNum += sgTlPmMapper.insertSgTlPm(sgKstl.getKstlId(), pmId, sgKstl.getKstlTime());
            // 使讨论的品名Id添加到申购表中
            sgInfoNum += sgInfoMapper.insertSgInfo(UUID.randomUUID().toString(), pmId);
        }
        return integer > 0 && usersId.size() == sgKstlUserNum && pmsId.size() == sgTlPmNum && pmsId.size() == sgInfoNum;
    }

    /**
     * 通过部门id查询讨论表的id
     *
     * @param bmId 部门ID
     * @return 部门所有的讨论ID
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> selectSgKstlIdsByBmId(String bmId) {
        return sgKstlMapper.selectSgKstlIdsByBmId(bmId);
    }

    /**
     * 通过部门id查询所有的品名ID
     *
     * @param bmId 部门ID
     * @return 部门讨论的所有需购买设备的品名列表
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Integer> selectSgTlPmPmIdsByBmId(String bmId) {
        List<String> tlIds = sgKstlMapper.selectSgKstlIdsByBmId(bmId);
        Set<Integer> pmIds = new HashSet<>();
        for (String tlId : tlIds) {
            pmIds.addAll(sgTlPmMapper.selectSgTlPmPmIdsByTlId(tlId));
        }
        return new ArrayList<>(pmIds);
    }

    /**
     * 通过品名id查询SgKstlVO
     *
     * @param pmId 品名id
     * @return SgKstlVO
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<SgKstlVO> selectSgKstlVOByPmIds(List<Integer> pmId) {
        return sgKstlMapper.selectSgKstlVOByPmIds(pmId);
    }

    /**
     * 显示部门下的所有讨论的设备
     *
     * @param userId   登陆人id
     * @param eqPmName 设备名称
     * @param eqPmJc   设备简称
     * @param pageNum  页数
     * @param pageSize 每页显示记录数
     * @return SgKstlVO
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<SgKstlVO> selectSgKstlSbs(String userId, String eqPmName, String eqPmJc, Integer pageNum, Integer pageSize) {
        //TODO 调用用户表方法 通过人员表id获取所在部门
        String bmId = "01001";
        List<Integer> pmList1 = selectSgTlPmPmIdsByBmId(bmId);
        //TODO 调用品名的模糊查询 获得查询到的品名ID
        List<Integer> pmList2 = new ArrayList<>();
        pmList2.add(1);
        pmList2.add(2);  //TODO 模拟数据

        // 把通过部门查询的品名list和模糊查询到的品名list 合成一个list 使用set去重
        Set<Integer> pmids = new HashSet<>();
        pmids.addAll(pmList1);
        pmids.addAll(pmList2);

        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(selectSgKstlVOByPmIds(new ArrayList<>(pmids)));
    }
}
