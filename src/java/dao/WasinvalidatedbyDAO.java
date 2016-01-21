package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasinvalidatedby;
import util.PersistenceUtil;

public class WasinvalidatedbyDAO {

    public static WasinvalidatedbyDAO wasinvalidatedbyDAO;

    public static WasinvalidatedbyDAO getInstance() {
        if (wasinvalidatedbyDAO == null) {
            wasinvalidatedbyDAO = new WasinvalidatedbyDAO();
        }
        return wasinvalidatedbyDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasinvalidatedby buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasinvalidatedby As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Wasinvalidatedby> wasinvalidatedbys = query.getResultList();
        if (wasinvalidatedbys != null && wasinvalidatedbys.size() > 0) {
            return wasinvalidatedbys.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasinvalidatedby> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasinvalidatedby As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasinvalidatedby
     *
     * @param wasinvalidatedby
     */
    public void remover(Wasinvalidatedby wasinvalidatedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasinvalidatedby);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasinvalidatedby 
     *
     * @param wasinvalidatedby 
     * @return
     */
    public Wasinvalidatedby persistir(Wasinvalidatedby wasinvalidatedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasinvalidatedby = em.merge(wasinvalidatedby);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasinvalidatedby;
    }

}
