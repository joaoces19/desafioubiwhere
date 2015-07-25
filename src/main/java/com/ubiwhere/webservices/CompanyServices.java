/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubiwhere.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ubiwhere.business_logic.CompanyBean;
import com.ubiwhere.business_logic.responses.CompanyResponse;
import com.ubiwhere.entities.Company;
import javax.ejb.EJB;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author joaoces
 */
@Path("company")
public class CompanyServices {
    
    @EJB
    private CompanyBean cb;

    private final Gson gson;
    
    public CompanyServices() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("add")
    public Response addCompany(String content) {
        Company c;
        
        try{
            c = gson.fromJson(content, Company.class);
        } catch(JsonSyntaxException jse){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(gson.toJson("JSON DESERIALIZING ERROR"))
                    .build();
        }
        
        CompanyResponse cr = cb.addCompany(c);
        if(cr.equals(CompanyResponse.OK)){
            return Response.status(Response.Status.OK).entity(CompanyResponse.OK).build();
        }
        
        return Response.status(Response.Status.FORBIDDEN).entity(cr).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}/edit")
    public Response editCompany(@PathParam("id") int id, String content) {
        Company c;
        
        try{
            c = gson.fromJson(content, Company.class);
        } catch(JsonSyntaxException jse){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(gson.toJson("JSON DESERIALIZING ERROR"))
                    .build();
        }
        
        c.setIdCompany(id);
        
        CompanyResponse cr = cb.editCompany(c);
        if(cr.equals(CompanyResponse.OK)){
            return Response.status(Response.Status.OK).entity(CompanyResponse.OK).build();
        }
        
        return Response.status(Response.Status.FORBIDDEN).entity(cr).build();
    }
    
    @DELETE
    @Produces("application/json")
    @Path("{id}/delete")
    public Response deleteCompany(@PathParam("id") int id) {
        CompanyResponse cr = cb.removeCompany(id);
        
        if(cr.equals(CompanyResponse.OK)){
            return Response.status(Response.Status.OK).entity(CompanyResponse.OK).build();
        }
        
        return Response.status(Response.Status.FORBIDDEN).entity(cr).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("{id}/details")
    public Response companyDetails(@PathParam("id") int id) {
        Company c = cb.companyDetails(id);
        if(c!=null){
            return Response.status(Response.Status.OK).entity(gson.toJson(c)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(CompanyResponse.INVALID_COMPANY_ID).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("details")
    public Response companiesDetails() {        
        return Response.status(Response.Status.OK).entity(gson.toJson(cb.companiesDetails())).build();
    }
}
