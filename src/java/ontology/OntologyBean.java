/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import weka.Weka;
import static weka.teste.caminhoDados;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "ontologyBean")
@ViewScoped
public class OntologyBean implements Serializable {

    private List<String> ontologys;
    private List<String> ontologys2;

    public void load() throws IOException {
        try {
            new OntologyDAO().run();
 
            String caminhoDados = "/C:/Users/Humberto/Desktop/data.arff";
            new Weka(caminhoDados).run();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Information...", "Loaded Ontology and Weka.... "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public List<String> getOntology() {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys = ontologyDAO.buscartodos();

        return ontologys;
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

    public List<String> getInfluenced(String nome, String id) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys = ontologyDAO.buscarInfluenced(nome, id);
        return ontologys;
    }

    public List<String> getwasInfluencedBy(String nome, String id) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys2 = ontologyDAO.buscarwasInfluencedBy(nome, id);
        return ontologys2;
    }

    public List<String> getwasAssociatedWith(String nome, String id) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys2 = ontologyDAO.buscarwasAssociatedWith(nome, id);
        return ontologys2;
    }

    public List<String> getwasInformedBy(String nome, String id) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys2 = ontologyDAO.buscarwasInformedBy(nome, id);
        return ontologys2;
    }

    public List<String> getused(String nome, String id) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys2 = ontologyDAO.buscarused(nome, id);
        return ontologys2;
    }

    public List<String> getwasAttributedTo(String nome, String id) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys2 = ontologyDAO.buscarwasAttributedTo(nome, id);
        return ontologys2;
    }
}
