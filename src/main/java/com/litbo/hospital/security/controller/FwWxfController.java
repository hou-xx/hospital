package com.litbo.hospital.security.controller;

import com.github.pagehelper.PageInfo;
import com.litbo.hospital.result.Result;
import com.litbo.hospital.security.bean.FwWxf;
import com.litbo.hospital.security.service.FwWxfService;
import com.litbo.hospital.security.vo.FwIdSelectVo;
import com.litbo.hospital.security.vo.FwWxfIndexVo;
import com.litbo.hospital.security.vo.WxfIndexVo;
import com.litbo.hospital.security.vo.WxfListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zjc
 * @create 2018-12-09 9:47
 */
@RestController
@RequestMapping("/wxf")
public class FwWxfController {

    @Autowired
    private FwWxfService fwWxfService;

    @GetMapping("/wxfList")
    public Result wxfList(@RequestParam(required = false,defaultValue = "10") Integer pageSize,
                          @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        try {
            String userId = "1615925023";
            PageInfo<WxfListVo> pageInfo = fwWxfService.WxfList(userId, pageNum, pageSize);
            return Result.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("读取数据失败");
        }
    }

    /**
     * 维修费审核主页面
     * @param id
     * @return
     */
    @GetMapping("/wxfShIndex")
    public Result wxfShIndex(Integer id){
        try {
            WxfIndexVo wxfIndexVo = fwWxfService.wxfShIndex(id);
            return Result.success(wxfIndexVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("读取信息失败");
        }
    }

    @GetMapping("/wxfGetEq")
    public Result wxfGetEq(){
        try {
            String userId = "1615925038";
            List<FwIdSelectVo> list = fwWxfService.wxfGetEq(userId);
            return Result.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("读取信息失败");
        }
    }

    /**
     * 维修费申请主页面
     * @param fwId
     * @return
     */
    @GetMapping("/wxfIndex")
    public Result wxfIndex(String fwId){
        String userId = "1615925008";
        /**
         * SecurityUtils.getSubject().getSession().getAttribute();
         */
        try {
            FwWxfIndexVo wxfIndexVo = fwWxfService.fwWxfIndex(fwId, userId);
            return Result.success(wxfIndexVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("读取信息失败");
        }
    }

    /**
     * 维修费申请添加
     * @param fwWxf
     * @return
     */
    @PostMapping("/addWxf")
    public Result addWxf(FwWxf fwWxf){
        try {
            fwWxfService.addFwWxf(fwWxf);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加失败");
        }
    }

    @PostMapping("/updateWxf")
    public Result updateWxf(int status,String spyj,String fwId){
        try {
            //session获取
            String userId = "5";
            fwWxfService.updateFwWxf(userId,status,spyj,fwId);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("审核失败");
        }
    }

}
