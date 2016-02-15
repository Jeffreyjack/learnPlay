package controllers;

import models.Bank;
import play.*;
import play.data.DynamicForm;
import play.mvc.*;
import models.User;
import static play.libs.Json.toJson;
import java.util.*;
import views.html.*;
import play.data.Form;
import play.libs.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * Created by Jeffrey on 2/15/2016.
 */
public class AuthenticationController extends Controller {

    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";

    public Result login() {
        System.out.println("inside login");
        DynamicForm form = Form.form().bindFromRequest();
        User user = User.findByEmailAddressAndPassword(form.get("emailAddress"), form.get("password"));
        if (user == null) {
            System.out.println("no User Found");
            return unauthorized();
        }
        else {
            String authToken = user.createToken();
            ObjectNode authTokenJson = Json.newObject();
            authTokenJson.put(AUTH_TOKEN, authToken);
            response().setCookie(AUTH_TOKEN, authToken);
            System.out.println("authToken :: "+authToken);
            System.out.println("authTokenJson :: "+authTokenJson);
            return ok(authTokenJson);
        }
    }

    @Security.Authenticated(Secured.class)
    public Result logout() {
        response().discardCookie(AUTH_TOKEN);
        getUser().deleteToken();
        return ok();
    }

    public static User getUser() {
        return (User)Http.Context.current().args.get("user");
    }
}

