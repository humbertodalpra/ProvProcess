/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Wasassociatedwith;
import util.PersistenceUtil;

/**
 *
 * @author Humberto
 */
public class WasassociatedwithDAO {

    public static WasassociatedwithDAO wasAssociatedWithDAO;

    public static WasassociatedwithDAO getInstance() {
        if (wasAssociatedWithDAO == null) {
            wasAssociatedWithDAO = new WasassociatedwithDAO();
        }
        return wasAssociatedWithDAO;
    }

    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Wasassociatedwith> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Wasassociatedwith As a");
        return query.getResultList();
    }
   
    public List<Wasassociatedwith> buscarInstance(int nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct ag from Agent ag, Activity a, Wasassociatedwith w \n" +
"    where a.idProcessInstance = :nome and w.agentidAgent = ag");
        query.setParameter("nome", nome);

        List<Wasassociatedwith> wasassociatedwiths = query.getResultList();
        if (wasassociatedwiths != null && wasassociatedwiths.size() > 0) {
            return wasassociatedwiths;
        }

        return null;
    }

    /**
     * Remove uma wasAssociatedWith
     *
     * @param Wasassociatedwith
     */
    public void remover(Wasassociatedwith wasAssociatedWith) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(wasAssociatedWith);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Wasassociatedwith
     *
     * @param wasAssociatedWith
     * @return
     */
    public Wasassociatedwith persistir(Wasassociatedwith wasAssociatedWith) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasAssociatedWith = em.merge(wasAssociatedWith);
            em.getTransaction().commit();
            System.out.println("Registro Wasassociatedwith gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasAssociatedWith;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Wasassociatedwith ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
