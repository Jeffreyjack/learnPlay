package controllers;

import models.User;
import play.*;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import models.Bank;
import static play.libs.Json.toJson;
import java.util.*;
import views.html.*;
import play.data.Form;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * Created by Jeffrey on 2/15/2016.
 */
public class UserController extends Controller{

    public Result createUser() {
        DynamicForm form = Form.form().bindFromRequest();
        User user = new User(form.get("emailAddress"),form.get("password"));
        user.save();
        return ok();
    }
}
