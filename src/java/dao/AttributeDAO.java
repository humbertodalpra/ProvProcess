/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Attribute;
import util.PersistenceUtil;

/**
 *
 * @author Humberto
 */
public class AttributeDAO {
     public static AttributeDAO attributeDAO;

    public static AttributeDAO getInstance() {
        if (attributeDAO == null) {
            attributeDAO = new AttributeDAO();
        }
        return attributeDAO;
    }

  
    /**
     * Busca todas as Cidades
     *
     * @return
     */
    public List<Attribute> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Attribute As a");
        return query.getResultList();
    }

    /**
     * Remove uma attribute
     *
     * @param Attribute
     */
    public void remover(Attribute attribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(attribute);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Attribute 
     *
     * @param attribute 
     * @return
     */
    public Attribute persistir(Attribute attribute) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            attribute = em.merge(attribute);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attribute;
    }
}
