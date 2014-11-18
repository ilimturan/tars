package controllers;

import models.*;
import play.Logger;
import play.data.Form;
import play.mvc.*;

public class Admin extends Controller {

    private static final Form<User> adminNewForm = Form.form(User.class);
    private static final Form<AdminSignInForm> adminSignInForm = Form.form(AdminSignInForm.class);

    public static Result checkAdminLogin(){
        //Logger.info(session("connected"));
        if( session("connected").equals("ok") ) {
            return redirect(routes.Admin.adminpage());
        }
        return null;
    }

    public static Result newform() {
        //checkAdminLogin();
        return ok(views.html.formadminnew.render(adminNewForm));
    }
    public static Result newadmin() {
        //checkAdminLogin();

        Form<User> boundForm = adminNewForm.bindFromRequest();
        if(boundForm.hasErrors()){
            flash("error", "Please correct the form bellow");
            return badRequest(views.html.formadminnew.render(boundForm));
        }
        else{
            User newAdmin = boundForm.get();
            //newAdmin.save();
            Boolean result = newAdmin.create();
            if (!result) {
                flash("error", "New admin can't create");
                return badRequest(views.html.formadminnew.render(boundForm));
            } else {
                flash("success", "Successfully added new admin");
                return redirect(routes.Admin.loginform());
            }

        }
    }

    public static Result loginform() {
        //checkAdminLogin();
        return ok(views.html.formadminlogin.render(adminSignInForm));
    }

    public static Result login() {

        Form<AdminSignInForm> boundForm = adminSignInForm.bindFromRequest();

        if(boundForm.hasErrors()) {
            flash("error", "Email or pass error");
            return badRequest(views.html.formadminlogin.render(adminSignInForm));
        } else {
            AdminSignInForm data = boundForm.get();
            User user = new User();
            Boolean result = user.login(data.emailAddress, data.passWord);

            if(result){
                flash("success", "You are loggin");
                session().clear();
                session("connected", "ok");
                session("user", data.emailAddress);
                return redirect(routes.Admin.adminpage());
            }else{
                flash("error", "Email or password not correct");
                return badRequest(views.html.formadminlogin.render(boundForm));
            }

        }

    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.Admin.loginform()
        );
    }

    public static Result adminpage() {
        String lg = session().get("connected");

        if((lg != null) && lg.equals("ok")){
            return ok(views.html.adminmainpage.render());
        }else{
            flash("error", "Please login");
            return redirect(routes.Admin.loginform());
        }

    }
}
