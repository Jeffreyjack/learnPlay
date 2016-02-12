package controllers;

import controllers.routes;
import controllers.Application;
import models.User;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by Jeffrey on 2/12/2016.
 */
public class Secured extends Security.Authenticator {

/*
    @Override
    public String getUsername(Context ctx) {
        User user = null;
        return null;
    }
*/

    @Override
    public Result onUnauthorized(Context ctx) {
        return badRequest();
    }
}
