package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasinformedby;
import util.PersistenceUtil;

public class WasinformedbyDAO {

    public static WasinformedbyDAO wasinformedbyDAO;

    public static WasinformedbyDAO getInstance() {
        if (wasinformedbyDAO == null) {
            wasinformedbyDAO = new WasinformedbyDAO();
        }
        return wasinformedbyDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasinformedby buscar(int nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasinformedby As a where a.idWasInformedBy =:nome ");
        query.setParameter("nome", nome);

        List<Wasinformedby> wasinformedbys = query.getResultList();
        if (wasinformedbys != null && wasinformedbys.size() > 0) {
            return wasinformedbys.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasinformedby> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasinformedby As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasinformedby
     *
     * @param wasinformedby
     */
    public void remover(Wasinformedby wasinformedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasinformedby);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasinformedby 
     *
     * @param wasinformedby 
     * @return
     */
    public Wasinformedby persistir(Wasinformedby wasinformedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasinformedby = em.merge(wasinformedby);
            em.getTransaction().commit();
            System.out.println("Registro Wasinformedby gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasinformedby;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Wasinformedby ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
