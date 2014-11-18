package controllers;

import models.*;
import play.mvc.*;
import views.html.*;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        List<Post> posts = Post.find.all();
        return ok(index.render(posts));
    }

}
