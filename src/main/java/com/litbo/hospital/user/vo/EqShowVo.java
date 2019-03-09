package com.litbo.hospital.user.vo;

import lombok.Data;

import java.util.Date;

/**
*   用于展示的数据
* @version :
* @author : ljl
**/
@Data
public class EqShowVo {

    private Integer eqId;

    private String eqSbbh;

    private String eqPym;

    private String eqName;

    private String eqZcbh;

    private String eqPmName;

    private String eqGg;

    private String eqXh;

    private String bmName;

    private String eqPp;

    private String eqPrice;

    private Date eqQysj;

    private String eqSyzt;
}
