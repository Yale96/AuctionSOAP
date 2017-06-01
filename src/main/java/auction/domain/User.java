package auction.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.getAll", query = "select a from User as a"),
    @NamedQuery(name = "User.count", query = "select count(a) from User as a"),
    @NamedQuery(name = "User.findByEmail", query = "select a from User as a where a.email = :email")
})
public class User {
    
    @Id
    private String email;
    
    public User()
    {
        
    }
    
    public User(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }
}
