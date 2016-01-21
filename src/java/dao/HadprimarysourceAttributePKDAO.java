/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.HadprimarysourceAttributePK;
import util.PersistenceUtil;

/**
 *
 * @author Humberto
 */
public class HadprimarysourceAttributePKDAO {
     public static HadprimarysourceAttributePKDAO hadprimarysourceAttributePKDAO;

    public static HadprimarysourceAttributePKDAO getInstance() {
        if (hadprimarysourceAttributePKDAO == null) {
            hadprimarysourceAttributePKDAO = new HadprimarysourceAttributePKDAO();
        }
        return hadprimarysourceAttributePKDAO;
    }

  
    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<HadprimarysourceAttributePK> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from HadprimarysourceAttributePK As a");
        return query.getResultList();
    }

    /**
     * Remove uma hadprimarysourceAttributePK
     *
     * @param HadprimarysourceAttributePK
     */
    public void remover(HadprimarysourceAttributePK hadprimarysourceAttributePK) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(hadprimarysourceAttributePK);
        em.getTransaction().commit();
    }

    /**
     * Persite uma HadprimarysourceAttributePK 
     *
     * @param hadprimarysourceAttributePK 
     * @return
     */
    public HadprimarysourceAttributePK persistir(HadprimarysourceAttributePK hadprimarysourceAttributePK) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            hadprimarysourceAttributePK = em.merge(hadprimarysourceAttributePK);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hadprimarysourceAttributePK;
    }
}
