package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ilimturan on 17/11/14.
 */
@Entity
public class Post extends Model {

    @Id
    public Long id;
    public String title;
    public String text;
    public Boolean isActive;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date createdAt = new Date();

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date modifiedAt = new Date();

    @ManyToOne
    public User user;

    public static Finder<Long,Post> find = new Finder<Long,Post>(
            Long.class, Post.class
    );
}
