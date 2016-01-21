package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasinfluencedby;
import util.PersistenceUtil;

public class WasinfluencedbyDAO {

    public static WasinfluencedbyDAO wasinfluencedbyDAO;

    public static WasinfluencedbyDAO getInstance() {
        if (wasinfluencedbyDAO == null) {
            wasinfluencedbyDAO = new WasinfluencedbyDAO();
        }
        return wasinfluencedbyDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasinfluencedby buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasinfluencedby As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Wasinfluencedby> wasinfluencedbys = query.getResultList();
        if (wasinfluencedbys != null && wasinfluencedbys.size() > 0) {
            return wasinfluencedbys.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasinfluencedby> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasinfluencedby As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasinfluencedby
     *
     * @param wasinfluencedby
     */
    public void remover(Wasinfluencedby wasinfluencedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasinfluencedby);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasinfluencedby 
     *
     * @param wasinfluencedby 
     * @return
     */
    public Wasinfluencedby persistir(Wasinfluencedby wasinfluencedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasinfluencedby = em.merge(wasinfluencedby);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasinfluencedby;
    }

}
