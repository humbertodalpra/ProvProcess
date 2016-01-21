package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.User;
import util.PersistenceUtil;

public class UserDAO {

    public static UserDAO userDAO;

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public User buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from User As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<User> users = query.getResultList();
        if (users != null && users.size() > 0) {
            return users.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<User> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from User As a");
        return query.getResultList();
    }

    /**
     * Remove uma user
     *
     * @param user
     */
    public void remover(User user) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    /**
     * Persite uma User 
     *
     * @param user 
     * @return
     */
    public User persistir(User user) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            user = em.merge(user);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
