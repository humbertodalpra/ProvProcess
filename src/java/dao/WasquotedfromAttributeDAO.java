package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.WasquotedfromAttribute;
import util.PersistenceUtil;

public class WasquotedfromAttributeDAO {

    public static WasquotedfromAttributeDAO wasquotedfromAttributeDAO;

    public static WasquotedfromAttributeDAO getInstance() {
        if (wasquotedfromAttributeDAO == null) {
            wasquotedfromAttributeDAO = new WasquotedfromAttributeDAO();
        }
        return wasquotedfromAttributeDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public WasquotedfromAttribute buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from WasquotedfromAttribute As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<WasquotedfromAttribute> wasquotedfromAttributes = query.getResultList();
        if (wasquotedfromAttributes != null && wasquotedfromAttributes.size() > 0) {
            return wasquotedfromAttributes.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<WasquotedfromAttribute> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasquotedfromAttribute As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasquotedfromAttribute
     *
     * @param wasquotedfromAttribute
     */
    public void remover(WasquotedfromAttribute wasquotedfromAttribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasquotedfromAttribute);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasquotedfromAttribute 
     *
     * @param wasquotedfromAttribute 
     * @return
     */
    public WasquotedfromAttribute persistir(WasquotedfromAttribute wasquotedfromAttribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasquotedfromAttribute = em.merge(wasquotedfromAttribute);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasquotedfromAttribute;
    }

}
