package com.litbo.hospital.common.utils.poi;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class InsertPDF {
    private static final String TEMPLATE_PATH="C:\\Users\\97304\\Desktop\\template.pdf";
    private static final String out_PATH="C:\\Users\\97304\\Desktop\\result.pdf";
    public static void main(String[] args){
        Map map=new HashMap<String,String>();
        map.put("name", "张三");
        map.put("gender", "dog");
        map.put("man1", "√");
        try {
            new InsertPDF().convertTransData(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕");
    }
    /**
     * 将数据转换为输入字节流
     * */
    protected InputStream convertTransData(Map map)
            throws Exception {
        if (map == null || map.isEmpty())
            return null;
        try {
            InputStream in =new FileInputStream(TEMPLATE_PATH);
            ByteArrayOutputStream out =
                    (ByteArrayOutputStream)generate(
                            new PdfReader(in),
                            map);
            ByteArrayInputStream ret =
                    new ByteArrayInputStream(out.toByteArray());
            //将pdf字节流输出到文件流
            OutputStream fos = new FileOutputStream(out_PATH);
            fos.write(out.toByteArray());
            fos.close();
            out.close();
            return ret;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     *  将数据，填入pdf表单域
     *
     * */
    public static OutputStream generate(PdfReader template, Map data)
            throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfStamper stamp = new PdfStamper(template, out);
            AcroFields form = stamp.getAcroFields();

            // set the field values in the pdf form
            for (Iterator it = data.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                String value = (String) data.get(key);
                form.setFieldProperty(key, "textfont", bfChinese, null);
                form.setField(key, value);
            }
            stamp.setFormFlattening(true);
            stamp.close();
            template.close();
            return out;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
