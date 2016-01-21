package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Hadmember;
import util.PersistenceUtil;

public class HadmemberDAO {

    public static HadmemberDAO hadmemberDAO;

    public static HadmemberDAO getInstance() {
        if (hadmemberDAO == null) {
            hadmemberDAO = new HadmemberDAO();
        }
        return hadmemberDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Hadmember buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Hadmember As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Hadmember> hadmembers = query.getResultList();
        if (hadmembers != null && hadmembers.size() > 0) {
            return hadmembers.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Hadmember> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Hadmember As a");
        return query.getResultList();
    }

    /**
     * Remove uma hadmember
     *
     * @param hadmember
     */
    public void remover(Hadmember hadmember) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(hadmember);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Hadmember 
     *
     * @param hadmember 
     * @return
     */
    public Hadmember persistir(Hadmember hadmember) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            hadmember = em.merge(hadmember);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hadmember;
    }

}
