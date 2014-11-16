package controllers;

import play.mvc.*;
import views.html.*;


public class Admin extends Controller {

    public static Result loginform() {
        return ok("login form");
    }

    public static Result login() {
        return ok("login");
    }
    public static Result newform() {
        return ok("new admin form");
    }
    public static Result newa() {
        return ok("new admin");
    }
}
