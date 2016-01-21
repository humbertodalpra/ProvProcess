package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Used;
import util.PersistenceUtil;

public class UsedDAO {

    public static UsedDAO usedDAO;

    public static UsedDAO getInstance() {
        if (usedDAO == null) {
            usedDAO = new UsedDAO();
        }
        return usedDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Used buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Used As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Used> useds = query.getResultList();
        if (useds != null && useds.size() > 0) {
            return useds.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Used> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Used As a");
        return query.getResultList();
    }

    /**
     * Remove uma used
     *
     * @param used
     */
    public void remover(Used used) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(used);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Used 
     *
     * @param used 
     * @return
     */
    public Used persistir(Used used) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            used = em.merge(used);
            em.getTransaction().commit();
            System.out.println("Registro Used gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return used;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Used ");
       query.executeUpdate();
       em.getTransaction().commit();
    
    }

}
