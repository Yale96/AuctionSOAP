package auction.service;

import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SellerMgr {

    private final EntityManagerFactory emf;
    private EntityManager em;

    public SellerMgr() {
        emf = Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     * en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
        // TODO
        Item toReturn = null;
        try {
            toReturn = new Item(seller, cat, description);
            em.getTransaction().begin();
            em.persist(toReturn);
            em.getTransaction().commit();
        } catch(Exception ex)  {
            
        }

        return toReturn;
    }

    /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word
     * verwijderd. false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        // TODO 
        if (item.getHighestBid() == null) {
            return true;
        } else {
            return false;
        }
    }
}
