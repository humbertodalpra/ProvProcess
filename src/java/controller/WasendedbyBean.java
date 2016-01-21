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
import dao.WasendedbyDAO;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.Wasendedby;
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
@ManagedBean(name = "wasendedbyBean")
@ViewScoped
public class WasendedbyBean {

    Wasendedby wasendedby = new Wasendedby();

    List wasendedbys = new ArrayList();

    //construtor
    public WasendedbyBean() {
        wasendedbys = new WasendedbyDAO().buscarTodas();
        wasendedby = new Wasendedby();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new WasendedbyDAO().persistir(wasendedby);
        wasendedbys = new WasendedbyDAO().buscarTodas();
        wasendedby = new Wasendedby();
    }

    public void exclude(ActionEvent actionEvent) {
        new WasendedbyDAO().remover(wasendedby);
        wasendedbys = new WasendedbyDAO().buscarTodas();
        wasendedby = new Wasendedby();
    }

    //getters and setters
    public Wasendedby getWasendedby() {
        return wasendedby;
    }

    public void setWasendedby(Wasendedby wasendedby) {
        this.wasendedby = wasendedby;
    }

    public List getWasendedbys() {
        return wasendedbys;
    }

    public void setWasendedbys(List wasendedbys) {
        this.wasendedbys = wasendedbys;
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
