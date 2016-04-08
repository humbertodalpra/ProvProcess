/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import dao.InstanceDAO;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.Instance;
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
@ManagedBean(name = "instanceBean")
@SessionScoped
public class InstanceBean {

    Instance instance = new Instance();

    List instances = new ArrayList();

    List activities = new ArrayList();

    //construtor
    public InstanceBean() {
        instances = new InstanceDAO().buscarTodas();
        activities = new InstanceDAO().buscarInstancias();
        instance = new Instance();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new InstanceDAO().persistir(instance);
        instances = new InstanceDAO().buscarTodas();
        instance = new Instance();
    }

    public List<Instance> buscar(int act) {
        InstanceDAO instanceDAO = new InstanceDAO();
        return instanceDAO.buscarInstance(act);   
    }

    public void exclude(ActionEvent actionEvent) {
        new InstanceDAO().remover(instance);
        instances = new InstanceDAO().buscarTodas();
        instance = new Instance();
    }

    //getters and setters
    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public List getInstances() {
        return instances;
    }

    public void setInstances(List instances) {
        this.instances = instances;
    }

    public List getActivities() {
        return activities;
    }

    public void setActivities(List activities) {
        this.activities = activities;
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
