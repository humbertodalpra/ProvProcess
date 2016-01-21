package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Entity;
import util.PersistenceUtil;

public class EntityDAO {

    public static EntityDAO entityDAO;

    public static EntityDAO getInstance() {
        if (entityDAO == null) {
            entityDAO = new EntityDAO();
        }
        return entityDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Entity buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Entity As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Entity> entitys = query.getResultList();
        if (entitys != null && entitys.size() > 0) {
            return entitys.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Entity> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Entity As a");
        return query.getResultList();
    }

    public List<Entity> buscartipoentity() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from Entity a group by a.typeEntity");
        return query.getResultList();
    }
    
    /**
     * Remove uma entity
     *
     * @param entity
     */
    public void remover(Entity entity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Entity 
     *
     * @param entity 
     * @return
     */
    public Entity persistir(Entity entity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            entity = em.merge(entity);
            em.getTransaction().commit();
            System.out.println("Registro Entity gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Entity ");
       query.executeUpdate();
       em.getTransaction().commit();
      
  }

}
