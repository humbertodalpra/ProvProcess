package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasstartedby;
import util.PersistenceUtil;

public class WasstartedbyDAO {

    public static WasstartedbyDAO wasstartedbyDAO;

    public static WasstartedbyDAO getInstance() {
        if (wasstartedbyDAO == null) {
            wasstartedbyDAO = new WasstartedbyDAO();
        }
        return wasstartedbyDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasstartedby buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasstartedby As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Wasstartedby> wasstartedbys = query.getResultList();
        if (wasstartedbys != null && wasstartedbys.size() > 0) {
            return wasstartedbys.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasstartedby> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasstartedby As a");
        return query.getResultList();
    }

    /**
     * Remove uma wasstartedby
     *
     * @param wasstartedby
     */
    public void remover(Wasstartedby wasstartedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasstartedby);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasstartedby 
     *
     * @param wasstartedby 
     * @return
     */
    public Wasstartedby persistir(Wasstartedby wasstartedby) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasstartedby = em.merge(wasstartedby);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasstartedby;
    }

}
