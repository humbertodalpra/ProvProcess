package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Actedonbehalfof;
import util.PersistenceUtil;

public class ActedonbehalfofDAO {

    public static ActedonbehalfofDAO actedonbehalfofDAO;

    public static ActedonbehalfofDAO getInstance() {
        if (actedonbehalfofDAO == null) {
            actedonbehalfofDAO = new ActedonbehalfofDAO();
        }
        return actedonbehalfofDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Actedonbehalfof buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Actedonbehalfof As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Actedonbehalfof> actedonbehalfofs = query.getResultList();
        if (actedonbehalfofs != null && actedonbehalfofs.size() > 0) {
            return actedonbehalfofs.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Actedonbehalfof> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Actedonbehalfof As a");
        return query.getResultList();
    }

    /**
     * Remove uma actedonbehalfof
     *
     * @param actedonbehalfof
     */
    public void remover(Actedonbehalfof actedonbehalfof) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(actedonbehalfof);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Actedonbehalfof 
     *
     * @param actedonbehalfof 
     * @return
     */
    public Actedonbehalfof persistir(Actedonbehalfof actedonbehalfof) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            actedonbehalfof = em.merge(actedonbehalfof);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actedonbehalfof;
    }

}
