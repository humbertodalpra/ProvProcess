package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.WasrevisionofAttribute;
import util.PersistenceUtil;

public class WasrevisionofAttributeDAO {

    public static WasrevisionofAttributeDAO wasrevisionofAttributeDAO;

    public static WasrevisionofAttributeDAO getInstance() {
        if (wasrevisionofAttributeDAO == null) {
            wasrevisionofAttributeDAO = new WasrevisionofAttributeDAO();
        }
        return wasrevisionofAttributeDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public WasrevisionofAttribute buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from WasrevisionofAttribute As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<WasrevisionofAttribute> wasrevisionofAttributes = query.getResultList();
        if (wasrevisionofAttributes != null && wasrevisionofAttributes.size() > 0) {
            return wasrevisionofAttributes.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<WasrevisionofAttribute> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasrevisionofAttribute As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasrevisionofAttribute
     *
     * @param wasrevisionofAttribute
     */
    public void remover(WasrevisionofAttribute wasrevisionofAttribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasrevisionofAttribute);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasrevisionofAttribute 
     *
     * @param wasrevisionofAttribute 
     * @return
     */
    public WasrevisionofAttribute persistir(WasrevisionofAttribute wasrevisionofAttribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasrevisionofAttribute = em.merge(wasrevisionofAttribute);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasrevisionofAttribute;
    }

}
