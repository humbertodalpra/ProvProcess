package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasderivedfrom;
import util.PersistenceUtil;

public class WasderivedfromDAO {

    public static WasderivedfromDAO wasderivedfromDAO;

    public static WasderivedfromDAO getInstance() {
        if (wasderivedfromDAO == null) {
            wasderivedfromDAO = new WasderivedfromDAO();
        }
        return wasderivedfromDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasderivedfrom buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasderivedfrom As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Wasderivedfrom> wasderivedfroms = query.getResultList();
        if (wasderivedfroms != null && wasderivedfroms.size() > 0) {
            return wasderivedfroms.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasderivedfrom> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasderivedfrom As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasderivedfrom
     *
     * @param wasderivedfrom
     */
    public void remover(Wasderivedfrom wasderivedfrom) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasderivedfrom);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasderivedfrom 
     *
     * @param wasderivedfrom 
     * @return
     */
    public Wasderivedfrom persistir(Wasderivedfrom wasderivedfrom) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasderivedfrom = em.merge(wasderivedfrom);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasderivedfrom;
    }

}
