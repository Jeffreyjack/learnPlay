package models;

/**
 * Created by Jeffrey on 2/12/2016.
 */

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
public class User extends Model {
    @Id
    private long id;
    private String name;
    private String authToken;

    public User(String name) {
        this.name=name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

/*    public String createToken() {
        authToken = UUID.randomUUID().toString();
        save();
        return authToken;
    }*/

    public static Finder<Long, User> finder = new Finder<>(Long.class, User.class);

    public static List<User> getUser() {
        return finder.all();
    }

/*    public static User findUser(String name){
        return find.where().eq();
    }*/
}
