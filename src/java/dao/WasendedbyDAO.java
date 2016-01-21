package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasendedby;
import util.PersistenceUtil;

public class WasendedbyDAO {

    public static WasendedbyDAO wasendedbyDAO;

    public static WasendedbyDAO getInstance() {
        if (wasendedbyDAO == null) {
            wasendedbyDAO = new WasendedbyDAO();
        }
        return wasendedbyDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasendedby buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasendedby As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Wasendedby> wasendedbys = query.getResultList();
        if (wasendedbys != null && wasendedbys.size() > 0) {
            return wasendedbys.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasendedby> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasendedby As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasendedby
     *
     * @param wasendedby
     */
    public void remover(Wasendedby wasendedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasendedby);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasendedby 
     *
     * @param wasendedby 
     * @return
     */
    public Wasendedby persistir(Wasendedby wasendedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasendedby = em.merge(wasendedby);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasendedby;
    }

}
