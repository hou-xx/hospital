package com.litbo.hospital.security.dao;

import com.litbo.hospital.security.bean.FwHt;
import com.litbo.hospital.security.vo.HtVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zjc
 * @create 2018-11-29 19:32
 */
@Mapper
public interface FwHtDao {

    @Insert("insert into fw_ht (id, ht_name, ht_time, \n" +
            "      ht_lx, ht_price, sbcs_id, \n" +
            "      he_wxsdb, ht_yfdb, ht_zffs, \n" +
            "      ht_jfly, ht_bxks_time, ht_bxjs_time, \n" +
            "      ht_xy_time, ht_bxfw, ht_hpjf, \n" +
            "      ht_fjURL, eq_id, ht_status, \n" +
            "      ht_ms)\n" +
            "    values (#{id,jdbcType=VARCHAR}, #{htName,jdbcType=VARCHAR}, #{htTime,jdbcType=TIMESTAMP}, \n" +
            "      #{htLx,jdbcType=VARCHAR}, #{htPrice,jdbcType=DECIMAL}, #{sbcsId,jdbcType=INTEGER}, \n" +
            "      #{heWxsdb,jdbcType=VARCHAR}, #{htYfdb,jdbcType=VARCHAR}, #{htZffs,jdbcType=VARCHAR}, \n" +
            "      #{htJfly,jdbcType=VARCHAR}, #{htBxksTime,jdbcType=TIMESTAMP}, #{htBxjsTime,jdbcType=TIMESTAMP}, \n" +
            "      #{htXyTime,jdbcType=INTEGER}, #{htBxfw,jdbcType=VARCHAR}, #{htHpjf,jdbcType=INTEGER}, \n" +
            "      #{htFjurl,jdbcType=VARCHAR}, #{eqId,jdbcType=INTEGER}, #{htStatus,jdbcType=INTEGER}, \n" +
            "      #{htMs,jdbcType=LONGVARCHAR})")
    public int addFwHt(FwHt fwHt);

    //合同查询
    @Select("SELECT\n" +
            "ht.id,\n" +
            "ht.ht_name,\n" +
            "ht.ht_price,\n" +
            "ht.ht_time,\n" +
            "cs.sbcs_name\n" +
            "\n" +
            "FROM\n" +
            "dbo.fw_ht AS ht ,\n" +
            "dbo.eq_cs AS cs\n" +
            "WHERE\n" +
            "ht.sbcs_id = cs.sbcs_id")
    public List<HtVo> getAllFwHt();



}
