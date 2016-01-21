/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

import dao.ActivityDAO;
import dao.AgentDAO;
import dao.EntityDAO;
import dao.WasinformedbyDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Activity;
import model.Agent;
import model.Entity;
import model.Wasinformedby;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Evaluation;
import weka.classifiers.rules.car.JCBA;

/**
 *
 * @author Humberto
 */
public class Weka implements Runnable {

    public String caminhoDados;

    // As instancias do dado
    public Instances dados;

    public Weka(String caminhoDados) {
        this.caminhoDados = caminhoDados;
    }

    public void leDados() throws Exception {

        DataSource fonte = new DataSource(caminhoDados);
        dados = fonte.getDataSet();

        // seta o atributo classe caso o formato de dados nao contenha essa informacao
        if (dados.classIndex() == -1) {
            dados.setClassIndex(dados.numAttributes() - 1);
        }
    }

    public void imprimeDados() {

        // Imprime cada instância (linha) dos dados
        for (int i = 0; i < dados.numInstances(); i++) {
            Instance atual = dados.instance(i);
            System.out.println((i + 1) + ": " + atual + "\n");
        }
    }

    public String JCBA() throws Exception {
        List<String> results = new ArrayList<String>();
        // Cria uma nova instancia da arvore
        JCBA car = new JCBA();

        // Constrói classificador
        car.setCBA(false);
        car.buildClassifier(dados);

        // Imprime a arvore
        // System.out.println(car.toString().replaceAll("==>", "generated"));
        results.add(car.toString().replaceAll("==>", "generated"));

        // Avalia o resultado
        Evaluation avaliacao;
        avaliacao = new Evaluation(dados);
        avaliacao.evaluateModel(car, dados);
        //System.out.println("Instancias corretas: " + avaliacao.correct() + "\n");
        results.add("Instancias corretas: " + avaliacao.correct() + "\n");
System.out.println(results.toString());
        return results.toString();
    }

    @Override
    public void run() {
        String data = "";
        data += "@relation proveniencia-weka.filters.unsupervised.attribute.RELAGGS-Rfirst-last-C20-weka.filters.unsupervised.attribute.NumericToNominal-Rfirst-last-weka.filters.unsupervised.attribute.Remove-R60";
        data += "\n";
        data += "\n";

        data += "@attribute NumInstancia {";
        Activity act = new Activity();
        List acts = new ArrayList();
        acts = ActivityDAO.getInstance().buscarInstancias();
        for (Object act1 : acts) {
            act = (Activity) act1;
            data += act.getIdProcessInstance();
            data += ",";
        }
        data += "}";
        data += "\n";

        data += "@attribute Activity {";
        acts = ActivityDAO.getInstance().buscarNomeInstancias();
        for (Object act1 : acts) {
            act = (Activity) act1;

            data += act.getName().replace(" ", "");
            data += ",";
        }
        data += "}";
        data += "\n";

        data += "@attribute Type_Activity {";
        acts = ActivityDAO.getInstance().buscarTipoInstancias();
        for (Object act1 : acts) {
            act = (Activity) act1;
            if (!act.getTypeActivity().equals("-")) {
                data += act.getTypeActivity().replace(" ", "");
                data += ",";
            }
        }
        data += "}";
        data += "\n";

        data += "@attribute Priority {";
        acts = ActivityDAO.getInstance().buscarPrioridadeInstancias();
        for (Object act1 : acts) {
            act = (Activity) act1;
            if (!act.getPriority().equals("-")) {
                data += act.getPriority().replace(" ", "");
                data += ",";
            }
        }
        data += "}";
        data += "\n";

        /*data += "@attribute Start_Time {";
         acts = ActivityDAO.getInstance().buscarInicioInstancias();
         for (Object act1 : acts) {
         act = (Activity) act1;
         data += act.getStartTime();
         data += ",";
         }
         data += "}";
         data += "\n";

         data += "@attribute End_Time {";
         acts = ActivityDAO.getInstance().buscarFimInstancias();
         for (Object act1 : acts) {
         act = (Activity) act1;
         data += act.getEndTime();
         data += ",";
         }
         data += "}";
         data += "\n";
         */
        data += "@attribute Agent {";
        Agent agt = new Agent();
        List agts = new ArrayList();
        agts = AgentDAO.getInstance().buscarAgenteInstancia();
        for (Object agt1 : agts) {
            agt = (Agent) agt1;
            if (!agt.getName().equals("-")) {
                data += agt.getName();
                data += ",";
            }

        }
        data += "}";
        data += "\n";

        data += "@attribute Type_Agent {";
        agts = AgentDAO.getInstance().buscarAgenteTypeInstancia();
        for (Object agt1 : agts) {
            agt = (Agent) agt1;
            if (!agt.getTypeAgent().equals("-")) {
                data += agt.getTypeAgent().replace(" ", "");
                data += ",";
            }
        }
        data += "}";
        data += "\n";

        data += "@attribute Type_Entity {";
        Entity ent = new Entity();
        List ents = new ArrayList();
        ents = EntityDAO.getInstance().buscartipoentity();
        for (Object ent1 : ents) {
            ent = (Entity) ent1;
            if (!ent.getTypeEntity().equals("-")) {
                data += ent.getTypeEntity().replace(" ", "");
                data += ",";
            }
        }
        data += "}";
        data += "\n";

        data += "@attribute WasInformedBy {";

        Set<Integer> wibsFilter = new LinkedHashSet<>();
        WasinformedbyDAO.getInstance().buscarTodas().stream().forEach((i) -> {
            wibsFilter.add(i.getActivityidActivityInformant().getIdProcessInstance());
        });

        data = wibsFilter.stream().map((i) -> i + ",").reduce(data, String::concat);

        data += "}";
        data += "\n";
        data += "@attribute TipoDESD_name_Erro_no_Sistema_CNT {0,1}";
        data += "\n";
        data += "\n";
        data += "@data";
        data += "\n";

        int i = 0;
        agts = AgentDAO.getInstance().buscarTodas();
        ents = EntityDAO.getInstance().buscarTodas();
        acts = ActivityDAO.getInstance().buscarTodas();
        List<Wasinformedby> wibs = WasinformedbyDAO.getInstance().buscarTodas();
        for (Object act1 : acts) {
            act = (Activity) act1;
            data += act.getIdProcessInstance();
            data += ",";
            if (!act.getName().equals("-")) {
                data += act.getName().replace(" ", "");
            } else {
                data += "?";
            }
            data += ",";
            if (!act.getTypeActivity().equals("-")) {
                data += act.getTypeActivity().replace(" ", "");
            } else {
                data += "?";
            }
            data += ",";
            if (!act.getPriority().equals("-")) {
                data += act.getPriority().replace(" ", "");
            } else {
                data += "?";
            }

            data += ",";
            agt = (Agent) agts.get(i);
            if (!agt.getName().equals("-")) {
                data += agt.getName().replace(" ", "");
            } else {
                data += "?";
            }
            data += ",";
            if (!agt.getTypeAgent().equals("-")) {
                data += agt.getTypeAgent().replace(" ", "");
            } else {
                data += "?";
            }
            data += ",";

            ent = (Entity) ents.get(i);
            if (!ent.getTypeEntity().equals("-")) {
                data += ent.getTypeEntity().replace(" ", "");
            } else {
                data += "?";
            }
            data += ",";

            boolean valid = false;
            for (Object wib2 : wibs) {
                Wasinformedby wib = (Wasinformedby) wib2;
                if (valid == false) {
                    if (act.getIdProcessInstance() == wib.getActivityidActivityInformed().getIdProcessInstance()) {
                        data += wib.getActivityidActivityInformant().getIdProcessInstance();
                        valid = true;
                    }
                }
            }
            if (valid == false) {
                data += "?";
                valid = true;
            }
            data += ",";
            data += act.getWasinformedbyList().isEmpty() ? "0" : "1";

            data += "\n";
            i++;
        }

        System.out.println(data);

        try {
            // Gravando no arquivo  
            File arquivo;

            arquivo = new File("/C:/Users/Humberto/Desktop/data.arff");
            FileOutputStream fos = new FileOutputStream(arquivo);
            String texto = data;
            fos.write(texto.getBytes());
            fos.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }

//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Status", "Weka file generated."));
    }
}
