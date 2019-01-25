package com.litbo.hospital.security.dao;

import com.litbo.hospital.security.bean.FwPjqlZjb;
import com.litbo.hospital.security.vo.FwPjqlZjbVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface FwPjqlZjbDao {
    @Insert( {"<script>",
            "insert into fw_pjql_zjb(pjql_id, pj_id, pj_count) values ",
            "<foreach collection='fwPjqlZjbs' item='item' index='index' separator=','>",
            "(#{item.pjqlId}, #{item.pjId}, #{item.pjCount})", "</foreach>",
            "</script>"
    })
    int insertFwPjqlZjbList(@Param("fwPjqlZjbs") List<FwPjqlZjb> fwPjqlZjbs);

    @Select("select * from fw_pjql_zjb where pjql_id=#{id}")
    List<FwPjqlZjb> listFwPjqlByBjqlId(Integer id);
    @SelectProvider(type=com.litbo.hospital.security.dao.sqlprovider.FwPjqlZjbProvider.class,method="listFwPjqlZjb")
    List<FwPjqlZjbVo> listFwPjqlZjb(@Param("start") Date start, @Param("end") Date end, @Param("pjName") String pjName);
}
