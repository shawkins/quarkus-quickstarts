package org.acme.getting.started;

import org.jboss.resteasy.reactive.server.core.CurrentRequestManager;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @Path("/before")
    @GET
    public Response before() {
        var context = CurrentRequestManager.get();
        return Response.ok("Host: %s Scheme %s".formatted(context.getHttpHeaders().getRequestHeader("Host"),
                context.getUriInfo().getBaseUri().getScheme())).build();
    }

    @Path("/after")
    @GET
    public Response after() {
        var context = CurrentRequestManager.get();
        return Response.ok("Scheme %s Host: %s".formatted(context.getUriInfo().getBaseUri().getScheme(),
                context.getHttpHeaders().getRequestHeader("Host"))).build();
    }
}