package models;

/**
 * Created by Jeffrey on 2/12/2016.
 */

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Bank extends Model {
    @Id
    private long id;
    private String name;

    public Bank(String name) {
        this.name=name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Finder<Long, Bank> finder = new Finder<>(Long.class, Bank.class);

    public static List<Bank> getBanks() {
        return finder.all();
    }

}
