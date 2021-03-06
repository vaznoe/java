package com.company.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/status")
@Produces(MediaType.TEXT_PLAIN)
public class StatusResource {
    @GET
    public Response healthcheck(){
        return Response.ok("Up and Running").build();
    }
}
