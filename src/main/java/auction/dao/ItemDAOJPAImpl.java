/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Yannick
 */
public class ItemDAOJPAImpl implements ItemDAO {
    
    private final EntityManager em;
    
    public ItemDAOJPAImpl(EntityManager em)
    {
        this.em = em;
    }
    
    @Override
    public int count() {
        Query q = em.createNamedQuery("Item.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item) {
         em.persist(item);
    }

    @Override
    public void edit(Item item) {
        em.merge(item);
    }


    @Override
    public List<Item> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Item.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Item find(Long id) {
        Query q = em.createNamedQuery("Item.findById", Item.class);
        q.setParameter("id", id);
        return (Item) q.getSingleResult();
    }
    
    /**
     *
     * @param description 
     * @return list of item instances having specified description
     */
    public List<Item> findByDescription(String description)
    {
        Query q = em.createNamedQuery("Item.findByDescription", Item.class);
        q.setParameter("description", description);
        return (List<Item>) q.getResultList();
    }

    @Override
    public void remove(Item item) {
        em.remove(em.merge(item));
    } 
    
    @Override
    public boolean addBid(Item item, Bid bid) {
        em.persist(bid);
        return true;
    }
}
