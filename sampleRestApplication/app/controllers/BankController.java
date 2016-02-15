package controllers;

import play.*;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.*;
import models.Bank;
import static play.libs.Json.toJson;
import java.util.*;
import views.html.*;
import play.data.Form;

@Security.Authenticated(Secured.class)
public class BankController extends Controller {

    public Result index() {
        return ok(index.render("Hi Jeffrey"));
    }

    public Result getBanks() {
        return ok(toJson(models.Bank.getBanks()));
    }

    public Result addBank() {
        DynamicForm form = Form.form().bindFromRequest();
        Bank bank = new Bank(form.get("name"));
        System.out.println("user name :: "+bank.getName());
        bank.save();
        return ok();
    }

}
