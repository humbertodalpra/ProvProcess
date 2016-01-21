package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Archive;
import util.PersistenceUtil;

public class ArchiveDAO {

    public static ArchiveDAO archiveDAO;

    public static ArchiveDAO getInstance() {
        if (archiveDAO == null) {
            archiveDAO = new ArchiveDAO();
        }
        return archiveDAO;
    }

    /**
     * Busca uma Cidade especifica
     *
     * @param nome
     * @return
     */
    public Archive buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Archive As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Archive> archives = query.getResultList();
        if (archives != null && archives.size() > 0) {
            return archives.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Archive> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Archive As a");
        return query.getResultList();
    }

    /**
     * Remove uma archive
     *
     * @param archive
     */
    public void remover(Archive archive) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(archive);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Archive 
     *
     * @param archive 
     * @return
     */
    public Archive persistir(Archive archive) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            archive = em.merge(archive);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return archive;
    }

}
