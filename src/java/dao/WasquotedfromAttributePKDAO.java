package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.WasquotedfromAttributePK;
import util.PersistenceUtil;

public class WasquotedfromAttributePKDAO {

    public static WasquotedfromAttributePKDAO wasquotedfromAttributePKDAO;

    public static WasquotedfromAttributePKDAO getInstance() {
        if (wasquotedfromAttributePKDAO == null) {
            wasquotedfromAttributePKDAO = new WasquotedfromAttributePKDAO();
        }
        return wasquotedfromAttributePKDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public WasquotedfromAttributePK buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from WasquotedfromAttributePK As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<WasquotedfromAttributePK> wasquotedfromAttributePKs = query.getResultList();
        if (wasquotedfromAttributePKs != null && wasquotedfromAttributePKs.size() > 0) {
            return wasquotedfromAttributePKs.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<WasquotedfromAttributePK> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasquotedfromAttributePK As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasquotedfromAttributePK
     *
     * @param wasquotedfromAttributePK
     */
    public void remover(WasquotedfromAttributePK wasquotedfromAttributePK) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasquotedfromAttributePK);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasquotedfromAttributePK 
     *
     * @param wasquotedfromAttributePK 
     * @return
     */
    public WasquotedfromAttributePK persistir(WasquotedfromAttributePK wasquotedfromAttributePK) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasquotedfromAttributePK = em.merge(wasquotedfromAttributePK);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasquotedfromAttributePK;
    }

}
