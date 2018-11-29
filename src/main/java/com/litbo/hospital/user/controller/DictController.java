package com.litbo.hospital.user.controller;

import com.litbo.hospital.result.Result;
import com.litbo.hospital.user.bean.*;
import com.litbo.hospital.user.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    DictService ds;


    @RequestMapping("/zjly")
    public Result listZjlys(){

        List<EqZjly> eqZjlys = ds.listZjlys();
        return Result.success(eqZjlys);
    }
    @RequestMapping("/jldw")
    public Result listJldw(){

        List<EqJldw> eqJldws = ds.listJldw();
        return Result.success(eqJldws);

    }

    @RequestMapping("/cxfl")
    public Result listCxfl(){

        List<EqCxfl> eqCxfls = ds.listCxfl();
        return  Result.success(eqCxfls);
    }
    @RequestMapping("/qdfs")
    public Result listQdfs(){

        List<EqQdfs> eqQdfs = ds.listQdfs();
        return Result.success(eqQdfs);
    }
    @RequestMapping("/gzlb")
    public Result listGzlb(){

        List<EqGzlb> eqGzlbs = ds.listGzlb();
        return Result.success(eqGzlbs);
    }


}
