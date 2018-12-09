package com.litbo.hospital.user.controller;

import com.litbo.hospital.common.utils.WordToPinYin;
import com.litbo.hospital.common.utils.poi.ImportExcelUtil;
import com.litbo.hospital.common.utils.poi.User;
import com.litbo.hospital.result.Result;
import com.litbo.hospital.user.bean.EqPm;
import com.litbo.hospital.user.bean.EqZjly;
import com.litbo.hospital.user.dao.DictDao;
import com.litbo.hospital.user.dao.PmDao;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static com.litbo.hospital.common.utils.poi.ListToListMap.listToMap;
import static com.litbo.hospital.common.utils.poi.ListToListMap.parseMap2Object;
@RestController
@RequestMapping("/import")
public class addPm {


    @Autowired
    private PmDao pmDao;

    @RequestMapping("/add")
    public Result add() {
    /* public static void main (String args[]){*/
        File file = new File("C:\\Users\\li66\\Desktop\\医院设备管理\\user.xlsx");
        Workbook workbook = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheetAt = workbook.getSheetAt(0);
            Row row = sheetAt.getRow(0);
            int rowNum = sheetAt.getLastRowNum() + 1;
            short cellNum = row.getLastCellNum();
            /*int rowIsNull = getRowIsNull(row, rowNum);
            System.out.println(rowIsNull);*/
            List<String> list = ImportExcelUtil.readTitlesToExcel(workbook, sheetAt, row, cellNum);
            List<List<Object>> lists = ImportExcelUtil.readRowsToExcel(workbook, sheetAt, row, rowNum);
            //System.out.println(list);
            for (List<Object> objectList : lists) {
                //System.out.println(objectList);
                // System.out.println(JSONObject.toJSON(objectList));
            }
            System.out.println(listToMap(lists, list));
            List<Map<String, Object>> mapList = listToMap(lists, list);
            for (Map<String, Object> map : mapList) {
                /*User user = parseMap2Object(map, User.class);*/
                EqPm eqPm = parseMap2Object(map,EqPm.class);
                String pid = eqPm.getEqPmId().substring(0,eqPm.getEqPmId().length()-2);
                eqPm.setPid(pid);

                String pym =  WordToPinYin.toPinYin(eqPm.getEqPmName());
                eqPm.setPym(pym);
                System.out.println(eqPm);

                pmDao.addPm(eqPm);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
