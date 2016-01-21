package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.WasrevisionofAttributePK;
import util.PersistenceUtil;

public class WasrevisionofAttributePKDAO {

    public static WasrevisionofAttributePKDAO wasrevisionofAttributePKDAO;

    public static WasrevisionofAttributePKDAO getInstance() {
        if (wasrevisionofAttributePKDAO == null) {
            wasrevisionofAttributePKDAO = new WasrevisionofAttributePKDAO();
        }
        return wasrevisionofAttributePKDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public WasrevisionofAttributePK buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from WasrevisionofAttributePK As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<WasrevisionofAttributePK> wasrevisionofAttributePKs = query.getResultList();
        if (wasrevisionofAttributePKs != null && wasrevisionofAttributePKs.size() > 0) {
            return wasrevisionofAttributePKs.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<WasrevisionofAttributePK> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasrevisionofAttributePK As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasrevisionofAttributePK
     *
     * @param wasrevisionofAttributePK
     */
    public void remover(WasrevisionofAttributePK wasrevisionofAttributePK) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasrevisionofAttributePK);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasrevisionofAttributePK 
     *
     * @param wasrevisionofAttributePK 
     * @return
     */
    public WasrevisionofAttributePK persistir(WasrevisionofAttributePK wasrevisionofAttributePK) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasrevisionofAttributePK = em.merge(wasrevisionofAttributePK);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasrevisionofAttributePK;
    }

}
