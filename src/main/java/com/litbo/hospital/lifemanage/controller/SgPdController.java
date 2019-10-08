package com.litbo.hospital.lifemanage.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.litbo.hospital.lifemanage.bean.vo.SgPdVO;
import com.litbo.hospital.lifemanage.service.SgPdSeverice;
import com.litbo.hospital.metering.vo.PageVo;
import com.litbo.hospital.result.Result;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/lifeManage")
@Api(tags = "离线盘点")
public class SgPdController {
    @Autowired
    private SgPdSeverice sgPdService;

    /**
     * 上传盘点数据
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/uploadPd")
//    @RequestMapping(value = "/uploadPd")
    @ResponseBody
    public Result uploadPdScan(@RequestParam("multipartFile") MultipartFile multipartFile,@RequestParam String pdJhid) {

        try {
            System.out.println(pdJhid);
            System.out.println("上传");
            System.out.println("上传");
            System.out.println("上传");
            if (!multipartFile.isEmpty()) {
                SgPdVO sgPdVO;
                byte[] bytes = multipartFile.getBytes();
                String s = new String(bytes, 0, bytes.length);
                System.out.println(s);
                sgPdVO = JSON.parseObject(s, SgPdVO.class);
                return Result.success(sgPdService.insertPdId(sgPdVO,pdJhid));
            } else {
                return Result.error("这是一个空文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success();


    }

    /**
     * 插入盘点扫描到的所有编号
     *
     * @param record
     */
    @PostMapping(value = "/insertSgPdId")
//    public Result insertSgPd(@RequestParam SgPdVO record) {
        public Result insertSgPd(@RequestParam SgPdVO record) {
        System.out.println(record.toString());
        System.out.println("iiiiiiiiiiiiiii");
        System.out.println("iiiiiiiiiiiiiii");
        System.out.println("iiiiiiiiiiiiiii");
        return Result.success(sgPdService.insertJhCz(record));
    }

    /**
     * 返回盘点数据的状态
     *
     * @param
     * @return
     */
    @RequestMapping("/selectAllData")
    public Result selectAllData(@RequestParam(name = "pdJhid") String pdJhid
                                , @RequestParam (name = "pageNum",defaultValue = "1")Integer pageNum,
                                  @RequestParam (name = "pageSize",defaultValue = "15")Integer pageSize
    ) {

        PageHelper.startPage(pageNum,pageSize);
//        List<Object> str = sgPdService.selectAllData(pdJhid);
//       Object object= sgPdService.selectAllData(pdJhid);
//        PageInfo info = new PageInfo(str);
//
//        PageVo vo = new PageVo();
//        if(!str.isEmpty()){
//            vo.setCode(0);
//            vo.setMsg("success");
//            vo.setData(vo.new DataEntity((int) info.getTotal(),str));
//            System.out.println(vo);
//            return vo;
//        }
//        System.out.println(vo);
//        vo.setMsg("没有查询到设备信息");
//        vo.setCode(0);
//        vo.setData(vo.new DataEntity((int) info.getTotal(),str));
        return Result.success(sgPdService.selectAllData(pdJhid));
    }

}
