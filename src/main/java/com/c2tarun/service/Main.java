package com.c2tarun.service;

import com.c2tarun.service.modules.PinConfigurationModule;
import com.google.inject.Guice;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:9898/myapp/";

    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.c2tarun.service package
        final ResourceConfig rc = new ResourceConfig().packages("com.c2tarun.service.controller");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        Guice.createInjector(new PinConfigurationModule());

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

