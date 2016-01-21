/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import dao.WasinformedbyDAO;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.Wasinformedby;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author Humberto
 */
@ManagedBean(name = "wasinformedbyBean")
@ViewScoped
public class WasinformedbyBean {

    Wasinformedby wasinformedby = new Wasinformedby();

    List wasinformedbys = new ArrayList();

    //construtor
    public WasinformedbyBean() {
        wasinformedbys = new WasinformedbyDAO().buscarTodas();
        wasinformedby = new Wasinformedby();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new WasinformedbyDAO().persistir(wasinformedby);
        wasinformedbys = new WasinformedbyDAO().buscarTodas();
        wasinformedby = new Wasinformedby();
    }

    public void exclude(ActionEvent actionEvent) {
        new WasinformedbyDAO().remover(wasinformedby);
        wasinformedbys = new WasinformedbyDAO().buscarTodas();
        wasinformedby = new Wasinformedby();
    }

    //getters and setters
    public Wasinformedby getWasinformedby() {
        return wasinformedby;
    }

    public void setWasinformedby(Wasinformedby wasinformedby) {
        this.wasinformedby = wasinformedby;
    }

    public List getWasinformedbys() {
        return wasinformedbys;
    }

    public void setWasinformedbys(List wasinformedbys) {
        this.wasinformedbys = wasinformedbys;
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
