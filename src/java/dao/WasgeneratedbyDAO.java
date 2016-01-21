package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasgeneratedby;
import util.PersistenceUtil;

public class WasgeneratedbyDAO {

    public static WasgeneratedbyDAO wasgeneratedbyDAO;

    public static WasgeneratedbyDAO getInstance() {
        if (wasgeneratedbyDAO == null) {
            wasgeneratedbyDAO = new WasgeneratedbyDAO();
        }
        return wasgeneratedbyDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasgeneratedby buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasgeneratedby As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Wasgeneratedby> wasgeneratedbys = query.getResultList();
        if (wasgeneratedbys != null && wasgeneratedbys.size() > 0) {
            return wasgeneratedbys.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasgeneratedby> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasgeneratedby As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasgeneratedby
     *
     * @param wasgeneratedby
     */
    public void remover(Wasgeneratedby wasgeneratedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasgeneratedby);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasgeneratedby 
     *
     * @param wasgeneratedby 
     * @return
     */
    public Wasgeneratedby persistir(Wasgeneratedby wasgeneratedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasgeneratedby = em.merge(wasgeneratedby);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasgeneratedby;
    }

}
