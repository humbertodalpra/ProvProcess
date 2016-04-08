package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Instance;
import util.PersistenceUtil;

public class InstanceDAO {

    public static InstanceDAO instanceDAO;

    public static InstanceDAO getInstance() {
        if (instanceDAO == null) {
            instanceDAO = new InstanceDAO();
        }
        return instanceDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Instance buscar(int nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Instance As a where a.idProcessInstance = :nome");
        query.setParameter("nome", nome);

        List<Instance> instances = query.getResultList();
        if (instances != null && instances.size() > 0) {
            return instances.get(0);
        }

        return null;
    }

    public List<Instance> buscarInstance(int nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Instance As a where a.idProcessInstance = :nome");
        query.setParameter("nome", nome);

        List<Instance> instances = query.getResultList();
        if (instances != null && instances.size() > 0) {
            return instances;
        }

        return null;
    }

    /**
     * Busca todas as activities
     *
     * @return
     */
    public List<Instance> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Instance As a");
        return query.getResultList();
    }

    public List<Instance> buscarInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        //Query query = em.createQuery("select distinct act from Instance act group by act.idProcessInstance");
        //Query query = em.createQuery("select a.idInstance, a.name, a.startTime, b.endTime, a.idProcessInstance, a.typeInstance, a.priority from Instance as a, instance as b WHERE a.starttime IS NOT NULL and b.endTime IS NOT NULL group by a.idProcessInstance");
        Query query = em.createQuery("select a from Instance a, Instance b WHERE a.startTime IS NOT NULL and b.endTime IS NOT NULL group by a.idProcessInstance");
        return query.getResultList();
    }

    public List<Instance> buscarNomeInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Instance act group by act.name");
        return query.getResultList();
    }

    public List<Instance> buscarTipoInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Instance act group by act.typeInstance");
        return query.getResultList();
    }

    public List<Instance> buscarPrioridadeInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Instance act group by act.priority");
        return query.getResultList();
    }

    public List<Instance> buscarInicioInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Instance act group by act.startTime");
        return query.getResultList();
    }
    
    public List<Instance> buscarFimInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Instance act group by act.endTime");
        return query.getResultList();
    }
    
    /**
     * Remove uma instance
     *
     * @param instance
     */
    public void remover(Instance instance) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(instance);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Instance
     *
     * @param instance
     * @return
     */
    public Instance persistir(Instance instance) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            instance = em.merge(instance);
            em.getTransaction().commit();
            System.out.println("Registro Instance gravado com sucesso");
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return instance;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Instance ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
