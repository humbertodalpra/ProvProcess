package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Agent;
import util.PersistenceUtil;

public class AgentDAO {

    public static AgentDAO agentDAO;

    public static AgentDAO getInstance() {
        if (agentDAO == null) {
            agentDAO = new AgentDAO();
        }
        return agentDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Agent buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Agent As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Agent> agents = query.getResultList();
        if (agents != null && agents.size() > 0) {
            return agents.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Agent> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Agent As a");
        return query.getResultList();
    }

    public List<Agent> buscarAgenteInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from Agent a group by a.name");
        return query.getResultList();
    }

        public List<Agent> buscarAgenteTypeInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from Agent a group by a.typeAgent");
        return query.getResultList();
    }
    
    /**
     * Remove uma agent
     *
     * @param agent
     */
    public void remover(Agent agent) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(agent);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Agent
     *
     * @param agent
     * @return
     */
    public Agent persistir(Agent agent) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            agent = em.merge(agent);
            em.getTransaction().commit();
            System.out.println("Registro Agent gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agent;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Agent ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
