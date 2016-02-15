package controllers;

import controllers.routes;
import controllers.BankController;
import controllers.AuthenticationController;
import models.Bank;
import models.User;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by Jeffrey on 2/12/2016.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        User user = null;
        String[] authTokenHeaderValues = ctx.request().headers().get(AuthenticationController.AUTH_TOKEN_HEADER);
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            user = models.User.findByAuthToken(authTokenHeaderValues[0]);
            if (user != null) {
                ctx.args.put("user", user);
                return user.getEmailAddress();
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return badRequest();
    }
}
