package ua.vn.talkos.controllers;

import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by oleg.sukhov
 */
@Path("test")
@RequestScoped
public class AuthenticateController {

    @Path("/hello")
    @GET
    @Produces("text/plain")
    public String test() {
        return "hello";
    }
}
