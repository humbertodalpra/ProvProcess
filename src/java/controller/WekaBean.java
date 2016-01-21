/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.faces.bean.ViewScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import weka.Weka;

/**
 *
 * @author Humberto
 */
@ManagedBean(name = "wekaBean")
@ViewScoped
public class WekaBean implements Serializable {
    public static String caminhoDados;
  
    public List<String> getWekaList() throws Exception {
        List<String> result = new ArrayList<>();
        caminhoDados = "/C:/Users/Humberto/Desktop/data.arff";
        Weka weka = new Weka(caminhoDados);

        weka.leDados();
        //weka.imprimeDados();

        String[] x = weka.JCBA().split("\n");
        for (String i : x) {
            if (i.indexOf("conf:") > 0) {
                try {
                    String p1 = (Integer.parseInt(i.substring(i.indexOf("conf:") + 6, i.indexOf("),"))) * 100) + "%"; // value conf
                    String p2 = i.split(" ")[0]; // value firt part to space 
                    p2 = p2.replace("=", " ");
                    p2 = p2.substring(p2.indexOf("\t") + 1, p2.length());
                    String pr = p1 + " of instances with " + p2 + " ware in system error.";
                    result.add(pr + "\n");
                } catch (Exception e) {
                    result.add("Line not classified:" + i + "\n");
                }
            }
        }
        return result;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";

        // pdf.add(Image.getInstance(logo));
    }

}
