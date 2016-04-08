/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import dao.ActivityDAO;
import dao.AgentDAO;
import dao.EntityDAO;
import dao.UsedDAO;
import dao.WasassociatedwithDAO;
import dao.WasattributedtoDAO;
import dao.WasinformedbyDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Activity;
import model.Agent;
import model.Entity;
import model.Used;
import model.Wasassociatedwith;
import model.Wasattributedto;
import model.Wasinformedby;

/**
 *
 * @author Humberto
 */
public class OntologyDAO implements Runnable {

    public List<String> buscartodos() {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-load.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        List ontologys = new ArrayList();
        OntClass equipe = model.getOntClass(baseURI + "Activity");
        OntProperty nome = model.getOntProperty(baseURI + "used");
        String temp1;
        String temp2;
        for (ExtendedIterator<? extends OntResource> instances = equipe.listInstances(); instances.hasNext();) {
            OntResource equipeInstance = instances.next();
            //ontologys.add(equipeInstance.getProperty(nome).toString().replace(baseURI, ""));
            //System.out.println("Equipe instance: " + equipeInstance.getProperty(nome).toString().replace("http://www.w3.org/ns/prov#", ""));

            // find out the resources that link to the instance
            for (StmtIterator stmts = model.listStatements(null, null, equipeInstance); stmts.hasNext();) {
                Individual ind = stmts.next().getSubject().as(Individual.class);
                // show the properties of this individual
                //System.out.println("  " + ind.getURI().toString().replace("http://www.w3.org/ns/prov#", ""));
                ontologys.add(ind.getURI().toString().replace(baseURI, ""));

                for (StmtIterator j = ind.listProperties(); j.hasNext();) {
                    Statement s = j.next();
                    //System.out.print("    " + s.getPredicate().getLocalName().toString().replace("http://www.w3.org/ns/prov#", "") + " -> ");
                    temp1 = (s.getPredicate().getLocalName().toString().replace(baseURI, "") + " -> ");
                    if (s.getObject().isLiteral()) {
                        //System.out.println(s.getLiteral().getLexicalForm().replace("http://www.w3.org/ns/prov#", ""));
                        ontologys.add(s.getLiteral().getLexicalForm().replace(baseURI, ""));
                    } else {
                        //System.out.println(s.getObject().toString().replace("http://www.w3.org/ns/prov#", ""));
                        temp2 = s.getObject().toString().replace(baseURI, "");
                        ontologys.add(temp1 + temp2);
                    }
                }
            }
        }
        return ontologys;
    }

    public List<String> buscarInfluenced(String nome, String id) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-load.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "SELECT ?subject\n"
                + " 	WHERE { \n"
                + "?subject prov:influenced <http://www.w3.org/ns/prov#" + nome.replaceAll(" ", "_") + "_" + id + ">.\n"
                + "}";
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, ontModel);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }

    public List<String> buscarwasInfluencedBy(String nome, String id) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-load.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "SELECT ?subject\n"
                + " 	WHERE { \n"
                + "?subject prov:wasInfluencedBy <http://www.w3.org/ns/prov#" + nome.replaceAll(" ", "_") + "_" + id + ">.\n"
                + "}";
        Query query2 = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query2, model);
        ResultSet results = qe.execSelect();
        List resultslist2 = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next2 = results.next();
            String result2 = null;
            result2 = next2.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist2.add(result2.replace("> )", ""));
        }

        return resultslist2;
    }

    public List<String> buscarwasAssociatedWith(String nome, String id) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-loadnotinference.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "SELECT distinct ?p ?o\n" +
                "WHERE\n" +
                "{	<http://www.w3.org/ns/prov#" + nome.replaceAll(" ", "_") + "_" + id + "> ?p ?o\n"
                + "	. ?p a owl:ObjectProperty \n"
                + "\n"
                + "}";
        Query query2 = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query2, model);
        ResultSet results = qe.execSelect();
        List resultslist2 = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next2 = results.next();
            String result2 = null;
            result2 = next2.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist2.add(result2.replace("> )", ""));
        }

        return resultslist2;
    }

    public List<String> buscarwasInformedBy(String nome, String id) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-loadnotinference.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "SELECT ?subject\n"
                + " 	WHERE { \n"
                + "?subject prov:wasInformedBy <http://www.w3.org/ns/prov#" + nome.replaceAll(" ", "_") + "_" + id + ">.\n"
                + "}";
        Query query2 = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query2, model);
        ResultSet results = qe.execSelect();
        List resultslist2 = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next2 = results.next();
            String result2 = null;
            result2 = next2.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist2.add(result2.replace("> )", ""));
        }

        return resultslist2;
    }

    public List<String> buscarused(String nome, String id) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-loadnotinference.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "SELECT ?subject\n"
                + " 	WHERE { \n"
                + "?subject prov:used <http://www.w3.org/ns/prov#" + nome.replaceAll(" ", "_") + "_" + id + ">.\n"
                + "}";
        Query query2 = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query2, model);
        ResultSet results = qe.execSelect();
        List resultslist2 = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next2 = results.next();
            String result2 = null;
            result2 = next2.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist2.add(result2.replace("> )", ""));
        }

        return resultslist2;
    }

    public List<String> buscarwasAttributedTo(String nome, String id) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-loadnotinference.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "SELECT ?subject\n"
                + " 	WHERE { \n"
                + "?subject prov:wasAttributedTo <http://www.w3.org/ns/prov#" + nome.replaceAll(" ", "_") + "_" + id + ">.\n"
                + "}";
        Query query2 = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query2, model);
        ResultSet results = qe.execSelect();
        List resultslist2 = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next2 = results.next();
            String result2 = null;
            result2 = next2.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist2.add(result2.replace("> )", ""));
        }

        return resultslist2;
    }

    @Override
    public void run() {

        System.out.println("Start");
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = "file:///C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-Empty.owl";
        //caminho fisico da nova ontologia
        String newontology = "/C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-load.owl";
        String newontology2 = "/C:/Users/humbe/OneDrive/Mestrado/Dissertação/OWL/OntologyProvProcess-loadnotinference.owl";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM_TRANS_INF;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Loading data from database", "OK"));
        System.out.println("Loading data from database");

        //Carrega os Activitys na ontologia apartir do banco de dados
        Resource resourceact = model.getResource(baseURI + "Activity");
        //Prepara os dataProperties
        DatatypeProperty dppii = ontModel.createDatatypeProperty(baseURI + "processInstanceId");
        DatatypeProperty dpta = ontModel.createDatatypeProperty(baseURI + "typeActivity");
        DatatypeProperty dpp = ontModel.createDatatypeProperty(baseURI + "priority");
        DatatypeProperty dpsat = ontModel.createDatatypeProperty(baseURI + "startedAtTime");
        DatatypeProperty dpeat = ontModel.createDatatypeProperty(baseURI + "endedAtTime");
        Activity activity = new Activity();
        List activitys = new ArrayList();
        activitys = new ActivityDAO().buscarTodas();
        for (Object activity1 : activitys) {
            activity = (Activity) activity1;
            if (!activity.getName().equals("")) {
                Individual act = model.createIndividual(baseURI + activity.getName().replace(" ", "_") + "_" + activity.getIdActivity(), resourceact);
                int ipi = activity.getIdProcessInstance();
                String convertipi = String.valueOf(ipi);
                act.addProperty(dppii, convertipi);

                if (!activity.getTypeActivity().equals("")) {
                    act.addProperty(dpta, activity.getTypeActivity());
                }
                if (!activity.getPriority().equals("")) {
                    act.addProperty(dpp, activity.getPriority());
                }
                if (activity.getStartTime() != null) {
                    act.addProperty(dpsat, activity.getStartTime().toString().replace(" ", "_"));
                }
                if (activity.getEndTime() != null) {
                    act.addProperty(dpeat, activity.getEndTime().toString().replace(" ", "_"));
                }
            }
        }

        //Carrega os Agents na ontologia apartir do banco de dados
        Agent agent = new Agent();
        List agents = new ArrayList();
        agents = new AgentDAO().buscarTodas();
        for (Object agent1 : agents) {
            agent = (Agent) agent1;
            if (!agent.getName().isEmpty()) {
                if (agent.getTypeAgent().equals("Person")) {
                    Resource resourceperson = model.getResource(baseURI + "Person");
                    model.createIndividual(baseURI + agent.getName().replace(" ", "_") + "_" + agent.getIdAgent(), resourceperson);
                } else if (agent.getTypeAgent().equals("Organization")) {
                    Resource resourceperson = model.getResource(baseURI + "Organization");
                    model.createIndividual(baseURI + agent.getName().replace(" ", "_") + "_" + agent.getIdAgent(), resourceperson);
                } else {
                    Resource resourceperson = model.getResource(baseURI + "SoftwareAgent");
                    model.createIndividual(baseURI + agent.getName().replace(" ", "_") + "_" + agent.getIdAgent(), resourceperson);
                }
            }
        }

        //Prepara os objectsProperties
        ObjectProperty pwaw = ontModel.createObjectProperty(baseURI + "wasAssociatedWith");
        Wasassociatedwith waw = new Wasassociatedwith();
        List waws = new ArrayList();
        waws = new WasassociatedwithDAO().buscarTodas();
        for (Object waw1 : waws) {
            waw = (Wasassociatedwith) waw1;
            if (!waw.getAgentidAgent().getName().isEmpty() && !waw.getActivityidActivity().getName().isEmpty()) {
                Individual a = model.getIndividual(baseURI + waw.getActivityidActivity().getName().replace(" ", "_") + "_" + waw.getActivityidActivity().getIdActivity());
                Individual ag = model.getIndividual(baseURI + waw.getAgentidAgent().getName().replace(" ", "_") + "_" + waw.getAgentidAgent().getIdAgent());
                a.addProperty(pwaw, ag);
            }
        }

        //Carrega os Entitys na ontologia apartir do banco de dados
        Resource resourceorg = model.getResource(baseURI + "Entity");
        //Prepara os dataProperties
        DatatypeProperty dpte = ontModel.createDatatypeProperty(baseURI + "typeEntity");
        Entity entity = new Entity();
        List entitys = new ArrayList();
        entitys = new EntityDAO().buscarTodas();
        for (Object entity1 : entitys) {
            entity = (Entity) entity1;
            if (!entity.getName().isEmpty() && !entity.getIdEntity().toString().isEmpty()) {
                Individual ent = model.createIndividual(baseURI + entity.getName().replace(" ", "_") + "_" + entity.getIdEntity(), resourceorg);
                ent.addProperty(dpte, entity.getTypeEntity());
            }
        }

        //Prepara os objectsProperties
        ObjectProperty opused = ontModel.createObjectProperty(baseURI + "used");
        Used used = new Used();
        List useds = new ArrayList();
        useds = new UsedDAO().buscarTodas();
        for (Object used1 : useds) {
            used = (Used) used1;
            if (!used.getActivityidActivity().getName().isEmpty() && !used.getEntityidEntity().getName().isEmpty()) {
                Individual a2 = model.getIndividual(baseURI + used.getActivityidActivity().getName().replace(" ", "_") + "_" + used.getActivityidActivity().getIdActivity());
                Individual e = model.getIndividual(baseURI + used.getEntityidEntity().getName().replace(" ", "_") + "_" + used.getEntityidEntity().getIdEntity());
                a2.addProperty(opused, e);
            }
        }

        //Prepara os objectsProperties
        ObjectProperty opwat = ontModel.createObjectProperty(baseURI + "wasAttributedTo");
        Wasattributedto wat = new Wasattributedto();
        List wats = new ArrayList();
        wats = new WasattributedtoDAO().buscarTodas();
        for (Object wat1 : wats) {
            wat = (Wasattributedto) wat1;
            if (!wat.getAgentidAgent().getName().isEmpty() && !wat.getEntityidEntity().getName().isEmpty()) {
                Individual ag2 = model.getIndividual(baseURI + wat.getAgentidAgent().getName().replace(" ", "_") + "_" + wat.getAgentidAgent().getIdAgent());
                Individual e2 = model.getIndividual(baseURI + wat.getEntityidEntity().getName().replace(" ", "_") + "_" + wat.getEntityidEntity().getIdEntity());
                e2.addProperty(opwat, ag2);
            }
        }

//Prepara os objectsProperties
        ObjectProperty opwib = ontModel.createObjectProperty(baseURI + "wasInformedBy");
        Wasinformedby wib = new Wasinformedby();
        List wibs = new ArrayList();
        wibs = new WasinformedbyDAO().buscarTodas();
        for (Object wib1 : wibs) {
            wib = (Wasinformedby) wib1;
            if (!wib.getActivityidActivityInformant().getName().isEmpty() && !wib.getActivityidActivityInformed().getName().isEmpty()) {
                Individual a3 = model.getIndividual(baseURI + wib.getActivityidActivityInformant().getName().replace(" ", "_") + "_" + wib.getActivityidActivityInformant().getIdActivity());
                Individual a4 = model.getIndividual(baseURI + wib.getActivityidActivityInformed().getName().replace(" ", "_") + "_" + wib.getActivityidActivityInformed().getIdActivity());
                a3.addProperty(opwib, a4);
            }
        }

        //validar a nova ontologia a ser criada
        System.out.println("Validating the ontology");
        ///FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validating the ontology", "Ok"));
        InfModel modelInf = ModelFactory.createInfModel(reasoner, model);
        ValidityReport vrp1 = modelInf.validate();
        if (vrp1.isValid()) {
            System.out.println("Valid OWL");
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Valid OWL", "Yes"));
        } else {
            System.out.println("Not valid OWL");
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not valid OWL", "Sorry, a failure occurred"));
            for (Iterator i = vrp1.getReports(); i.hasNext();) {
                System.out.println(" - " + i.next());
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(" - " + i.next()));
            }
        }

        //Gerar o novo arquivo com os dados do banco na nova ontologia
        FileWriter arquivo = null;
        FileWriter arquivo2 = null;
        try {
            //caminho para o novo arquivo de ontologia
            arquivo = new FileWriter(newontology);
            arquivo2 = new FileWriter(newontology2);
            //se não existir arquivo, o mesmo será criado, se não, será reescrito
        } catch (IOException ex) {
            ex.printStackTrace(); //verificando problemas
        }
        //determinando que o fluxo de saida vai para o arquivo e não para a tela
        BufferedWriter out = new BufferedWriter(arquivo);
        BufferedWriter out2 = new BufferedWriter(arquivo2);
        model.write(out2, "RDF/XML-ABBREV");
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, model);
        //utilizar RDF/XML-ABBREV, so RDF/XML da erro no protege!
        model.write(out, "RDF/XML-ABBREV");
        System.out.println("Ontology Load");
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Status", "Ontology successfully loaded"));

    }
}
