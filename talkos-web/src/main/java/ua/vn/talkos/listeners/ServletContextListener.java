package ua.vn.talkos.listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import ua.vn.talkos.controllers.AuthenticateController;

/**
 * @author oleg.sukhov
 */
public class ServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {

            @Override
            protected void configureServlets() {
                bind(AuthenticateController.class);
                serve("/*").with(GuiceContainer.class);
            }
        });
    }
}
