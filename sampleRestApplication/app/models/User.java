package models;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by Jeffrey on 2/15/2016.
 */
@Entity
public class User extends Model{
    @Id
    public Long id;
    private String authToken;
    private String emailAddress;
    private byte[] password;

    public User(String emailAddress, String password) {
        setEmailAddress(emailAddress);
        setPassword(password);
     }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = getSha512(password);
    }

    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String createToken() {
        authToken = UUID.randomUUID().toString();
        save();
        return authToken;
    }

    public void deleteToken() {
        authToken = null;
        System.out.println("authToken removd :: "+authToken);
        save();
    }

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public static User findByAuthToken(String authToken) {
        if (authToken == null) {
            return null;
        }
        try  {
            return find.where().eq("authToken", authToken).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static User findByEmailAddressAndPassword(String emailAddress, String password) {
        return find.where().eq("emailAddress", emailAddress.toLowerCase()).eq("password", getSha512(password)).findUnique();
    }
}
