package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Activity;
import util.PersistenceUtil;

public class ActivityDAO {

    public static ActivityDAO activityDAO;

    public static ActivityDAO getInstance() {
        if (activityDAO == null) {
            activityDAO = new ActivityDAO();
        }
        return activityDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Activity buscar(int nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Activity As a where a.idProcessInstance = :nome");
        query.setParameter("nome", nome);

        List<Activity> activitys = query.getResultList();
        if (activitys != null && activitys.size() > 0) {
            return activitys.get(0);
        }

        return null;
    }

    public List<Activity> buscarInstance(int nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Activity As a where a.idProcessInstance = :nome");
        query.setParameter("nome", nome);

        List<Activity> activitys = query.getResultList();
        if (activitys != null && activitys.size() > 0) {
            return activitys;
        }

        return null;
    }

    /**
     * Busca todas as activities
     *
     * @return
     */
    public List<Activity> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Activity As a");
        return query.getResultList();
    }

    public List<Activity> buscarInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Activity act group by act.idProcessInstance");
        //Query query = em.createQuery("select a.idactivity, a.name, a.starttime, b.endtime, a.idprocessinstance, a.type_activity, a.priority from Activity as a, activity as b WHERE a.starttime IS NOT NULL and b.endTime IS NOT NULL group by a.idProcessInstance");
        return query.getResultList();
    }

    public List<Activity> buscarNomeInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Activity act group by act.name");
        return query.getResultList();
    }

    public List<Activity> buscarTipoInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Activity act group by act.typeActivity");
        return query.getResultList();
    }

    public List<Activity> buscarPrioridadeInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Activity act group by act.priority");
        return query.getResultList();
    }

    public List<Activity> buscarInicioInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Activity act group by act.startTime");
        return query.getResultList();
    }
    
    public List<Activity> buscarFimInstancias() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct act from Activity act group by act.endTime");
        return query.getResultList();
    }
    
    /**
     * Remove uma activity
     *
     * @param activity
     */
    public void remover(Activity activity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(activity);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Activity
     *
     * @param activity
     * @return
     */
    public Activity persistir(Activity activity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            activity = em.merge(activity);
            em.getTransaction().commit();
            System.out.println("Registro Activity gravado com sucesso");
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return activity;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Activity ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
