package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasattributedto;
import util.PersistenceUtil;

public class WasattributedtoDAO {

    public static WasattributedtoDAO wasattributedtoDAO;

    public static WasattributedtoDAO getInstance() {
        if (wasattributedtoDAO == null) {
            wasattributedtoDAO = new WasattributedtoDAO();
        }
        return wasattributedtoDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Wasattributedto buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Wasattributedto As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Wasattributedto> wasattributedtos = query.getResultList();
        if (wasattributedtos != null && wasattributedtos.size() > 0) {
            return wasattributedtos.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasattributedto> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasattributedto As a");
        return query.getResultList();
    }

    public List<Wasattributedto> buscarInstance(int nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct E from Entity E, Activity a, Used U \n" +
"    where U.entityidEntity = E and U.activityidActivity = a  AND a.idProcessInstance = :nome");
        query.setParameter("nome", nome);

        List<Wasattributedto> wasattributedtos = query.getResultList();
        if (wasattributedtos != null && wasattributedtos.size() > 0) {
            return wasattributedtos;
        }

        return null;
    }
    
    
    /**
     * Remove uma wasattributedto
     *
     * @param wasattributedto
     */
    public void remover(Wasattributedto wasattributedto) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasattributedto);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasattributedto 
     *
     * @param wasattributedto 
     * @return
     */
    public Wasattributedto persistir(Wasattributedto wasattributedto) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasattributedto = em.merge(wasattributedto);
            em.getTransaction().commit();
            System.out.println("Registro wasattributedto gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasattributedto;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Wasattributedto ");
       query.executeUpdate();
       em.getTransaction().commit();
    
  }

}
