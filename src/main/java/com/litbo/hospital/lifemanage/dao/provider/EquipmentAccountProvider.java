package com.litbo.hospital.lifemanage.dao.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * 电子台账Provider
 *
 * @author Administrator on 2018-12-29
 */
public class EquipmentAccountProvider {
    /**
     * 查询电子台账
     *
     * @param category            是否是医学装备 68是 非68不是
     * @param state               状态
     * @param departmentId        部门id
     * @param equipmentPinyinCode 设备拼音码
     * @param departmentCoding    院内编码
     * @param equipmentNumber     设备编码
     * @return sql
     */
    public String selectEquipmentAccount(String category, String state, String departmentId, String equipmentPinyinCode, String departmentCoding, String equipmentNumber) {
        SQL sql = new SQL();
        sql.SELECT("dbo.eq_info.eq_id,\n" +
                "dbo.eq_info.eq_sbbh,\n" +
                "dbo.eq_info.eq_name,\n" +
                "dbo.eq_info.eq_zcbh as eqYq,\n" +
                "dbo.eq_info.eq_gg,\n" +
                "dbo.eq_info.eq_xh,\n" +
                "dbo.s_bm.bm_name as eqBmName,\n" +
                "dbo.eq_info.eq_tzlb,\n" +
                "dbo.eq_info.eq_qysj,\n" +
                "dbo.eq_info.eq_synx,\n" +
                "dbo.eq_info.eq_syzt as state");
        sql.FROM("dbo.eq_info");
        sql.INNER_JOIN("dbo.s_bm ON dbo.eq_info.eq_bmid = dbo.s_bm.bm_id");
        sql.WHERE("dbo.eq_info.eq_sbbh IS NOT NULL AND\n" +
                "dbo.eq_info.eq_zcbh IS NOT NULL AND\n" +
                "dbo.eq_info.eq_tzlb IS NOT NULL\n");
        if (StringUtils.isNotBlank(category)) {
            //医学装备 68是 非68不是
            if (category.equals("68")) {
                sql.WHERE("LEFT(dbo.eq_info.eq_pm_id,2) = 68");
            } else {
                sql.WHERE("LEFT(dbo.eq_info.eq_pm_id,2) <> 68");
            }
        }
        if (StringUtils.isNotBlank(state)) {
            sql.WHERE("dbo.eq_info.eq_syzt = #{state,jdbcType=VARCHAR}");
        }
        if (StringUtils.isNotBlank(departmentId)) {
            sql.WHERE("dbo.eq_info.eq_bmid = #{departmentId,jdbcType=VARCHAR}");
        }
        if (StringUtils.isNotBlank(equipmentPinyinCode)) {
            sql.WHERE("dbo.eq_info.eq_pym like #{equipmentPinyinCode,jdbcType=VARCHAR}");
        }
        if (StringUtils.isNotBlank(departmentCoding)) {
            sql.WHERE("dbo.eq_info.eq_zcbh like #{departmentCoding,jdbcType=VARCHAR}");
        }

        if (StringUtils.isNotBlank(equipmentNumber)) {
            sql.WHERE("dbo.eq_info.eq_sbbh = #{equipmentNumber,jdbcType=VARCHAR}");
        }
        return sql.toString();
    }

    /**
     * 科室设备综合查询
     *
     * @param state               状态
     * @param equipmentPinyinCode 设备拼音码
     * @param departmentCoding    院内编码
     * @param eqCxflId            设备分类Id
     * @return sql
     */
    public String selectKsEq(String state, String equipmentPinyinCode, String departmentCoding, String eqCxflId) {
        SQL sql = new SQL();
        sql.SELECT("dbo.eq_info.eq_id,\n" +
                "dbo.eq_info.eq_zcbh,\n" +
                "dbo.eq_info.eq_sbbh,\n" +
                "dbo.eq_info.eq_name,\n" +
                "dbo.eq_info.eq_gg,\n" +
                "dbo.eq_info.eq_xh,\n" +
                "dbo.eq_info.eq_qysj,\n" +
                "dbo.eq_cxfl.eq_cxfl_name,\n" +
                "dbo.eq_info.eq_bxqx,\n" +
                "dbo.eq_info.eq_cgrq,\n" +
                "dbo.eq_info.eq_syzt,\n" +
                "dbo.s_bm.bm_name,\n" +
                "dbo.eq_info.eq_price,\n" +
                "dbo.eq_info.eq_tgks,\n" +
                "dbo.eq_cs.sbcs_name");
        sql.FROM("dbo.eq_info");
        sql.INNER_JOIN("dbo.eq_cxfl ON dbo.eq_info.eq_cxfl_id = dbo.eq_cxfl.eq_cxfl_id\n" +
                "LEFT JOIN dbo.s_bm ON dbo.eq_info.eq_bmid = dbo.s_bm.bm_id\n" +
                "LEFT JOIN dbo.eq_cs ON dbo.eq_info.sbcs_id_scs = dbo.eq_cs.sbcs_id");
        if (StringUtils.isNotBlank(state)) {
            sql.WHERE("dbo.eq_info.eq_syzt = #{state,jdbcType=VARCHAR}");
        }
        if (StringUtils.isNotBlank(equipmentPinyinCode)) {
            sql.WHERE("dbo.eq_info.eq_pym like #{equipmentPinyinCode,jdbcType=VARCHAR}");
        }
        if (StringUtils.isNotBlank(departmentCoding)) {
            sql.WHERE("dbo.eq_info.eq_zcbh like #{departmentCoding,jdbcType=VARCHAR}");
        }
        if (StringUtils.isNotBlank(eqCxflId)) {
            sql.WHERE("dbo.eq_info.eq_cxfl_id = #{eqCxflId,jdbcType=VARCHAR}");
        }
        sql.WHERE("dbo.eq_info.eq_sbbh IS NOT NULL AND dbo.eq_info.eq_zcbh IS NOT NULL");
        return sql.toString();
    }

    public static void main(String[] args) {
        EquipmentAccountProvider s = new EquipmentAccountProvider();
        String s1 = s.selectKsEq(null, null, null, null);
        System.out.println(s1);

    }
}
