package controllers;

import play.*;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import models.User;
import static play.libs.Json.toJson;
import java.util.*;
import views.html.*;
import play.data.Form;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Application extends Controller {

    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";

    public Result index() {
        return ok(index.render("Hi Jeffrey"));
    }

/*    public static Result login() {
        DynamicForm form = Form.form().bindFromRequest();
        String email = form.get("email");
        String password = form.get("password");
        if(email != null){
            String authToken = createToken();
            ObjectNode authTokenJson = Json.newObject();
            authTokenJson.put(AUTH_TOKEN, authToken);
            response().setCookie(AUTH_TOKEN, authToken);
            return ok(authTokenJson);
        }
        else
            return badRequest();
    }*/

    public Result getUsers() {
        return ok(toJson(models.User.getUser()));
    }

    public Result createUser() {

        DynamicForm form = Form.form().bindFromRequest();
        //Form<models.User> userForm = Form.form(models.User.class);
        User user = new User(form.get("name"));
        System.out.println("user name :: "+user.getName());
        user.save();
        return redirect("/users");
    }

}
