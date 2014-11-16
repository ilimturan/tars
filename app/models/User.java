package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

/**
 * Created by ilimturan on 17/11/14.
 */
@Entity
public class User extends Model {
    @Id
    public Long id;

    public String userName;
    public String fullName;
    public String emailAddress;
    public Boolean isActive;

    @OneToMany(mappedBy = "user")
    public List<Post> posts;

}
