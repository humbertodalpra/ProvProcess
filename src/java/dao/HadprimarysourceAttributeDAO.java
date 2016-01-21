/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.HadprimarysourceAttribute;
import util.PersistenceUtil;

/**
 *
 * @author Humberto
 */
public class HadprimarysourceAttributeDAO {
     public static HadprimarysourceAttributeDAO hadprimarysourceAttributeDAO;

    public static HadprimarysourceAttributeDAO getInstance() {
        if (hadprimarysourceAttributeDAO == null) {
            hadprimarysourceAttributeDAO = new HadprimarysourceAttributeDAO();
        }
        return hadprimarysourceAttributeDAO;
    }

  
    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<HadprimarysourceAttribute> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from HadprimarysourceAttribute As a");
        return query.getResultList();
    }

    /**
     * Remove uma hadprimarysourceAttribute
     *
     * @param HadprimarysourceAttribute
     */
    public void remover(HadprimarysourceAttribute hadprimarysourceAttribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(hadprimarysourceAttribute);
        em.getTransaction().commit();
    }

    /**
     * Persite uma HadprimarysourceAttribute 
     *
     * @param hadprimarysourceAttribute 
     * @return
     */
    public HadprimarysourceAttribute persistir(HadprimarysourceAttribute hadprimarysourceAttribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            hadprimarysourceAttribute = em.merge(hadprimarysourceAttribute);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hadprimarysourceAttribute;
    }
}
