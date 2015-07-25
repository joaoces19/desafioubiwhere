/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubiwhere.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ubiwhere.business_logic.PersonBean;
import com.ubiwhere.business_logic.responses.PersonResponse;
import com.ubiwhere.entities.Person;
import com.ubiwhere.entities.Phonenumber;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author joaoces
 */
@Path("person")
public class PersonServices {
    
    @EJB
    private PersonBean pb;
    
    private final Gson gson;
    
    public PersonServices() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("add")
    public Response addPerson(String content) {
        Person p;
        
        try{            
            p = gson.fromJson(content, Person.class);
        } catch(JsonSyntaxException jse){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(gson.toJson("JSON DESERIALIZING ERROR"))
                    .build();
        }
        
        PersonResponse pr = pb.addPerson(p);
        if(pr.equals(PersonResponse.OK)){
            return Response.status(Response.Status.OK).entity(PersonResponse.OK).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(pr).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}/edit")
    public Response editPerson(@PathParam("id") int id, String content) {
        Person p;
        
        try{
            p = gson.fromJson(content, Person.class);
        } catch(JsonSyntaxException jse){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(gson.toJson("JSON DESERIALIZING ERROR"))
                    .build();
        }
        
        p.setIdPerson(id);
        
        PersonResponse pr = pb.editPerson(p);
        if(pr.equals(PersonResponse.OK)){
            return Response.status(Response.Status.OK).entity(PersonResponse.OK).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(pr).build();
    }
    
    @DELETE
    @Produces("application/json")
    @Path("{id}/delete")
    public Response deletePerson(@PathParam("id") int id) {
        PersonResponse pr = pb.removePerson(id);
        
        if(pr.equals(PersonResponse.OK)){
            return Response.status(Response.Status.OK).entity(PersonResponse.OK).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(pr).build();
    }
    
    @PUT
    @Produces("application/json")
    @Path("{id}/number/add")
    public Response addPersonNumber(@PathParam("id") int id, String content) {
        Phonenumber pn;
        
        try{            
            pn = gson.fromJson(content, Phonenumber.class);
        } catch(JsonSyntaxException jse){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(gson.toJson("JSON DESERIALIZING ERROR"))
                    .build();
        }
        
        PersonResponse pr = pb.numberAdd(id, pn);
        
        if(pr.equals(PersonResponse.OK)){
            return Response.status(Response.Status.OK).entity(PersonResponse.OK).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(pr).build();
    }
    
    @DELETE
    @Produces("application/json")
    @Path("{idPerson}/number/{idPhonenumber}/delete")
    public Response deletePersonNumber(@PathParam("idPerson") int idPerson,
            @PathParam("idPhonenumber") int idPhonenumber) {
        PersonResponse pr = pb.numberRemove(idPerson, idPhonenumber);
        
        if(pr.equals(PersonResponse.OK)){
            return Response.status(Response.Status.OK).entity(PersonResponse.OK).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(pr).build();
    }
    
    @PUT
    @Produces("application/json")
    @Path("{idPerson}/company/{idCompany}/link")
    public Response personCompanyLink(@PathParam("idPerson") int idPerson,
            @PathParam("idCompany") int idCompany) {
        PersonResponse pr = pb.personLinkCompany(idPerson, idCompany);
        
        if(pr.equals(PersonResponse.OK)){
            return Response.status(Response.Status.OK).entity(PersonResponse.OK).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(pr).build();
    }
    
    @PUT
    @Produces("application/json")
    @Path("{idPerson}/company/{idCompany}/unlink")
    public Response personCompanyUnlink(@PathParam("idPerson") int idPerson,
            @PathParam("idCompany") int idCompany) {        
        PersonResponse pr = pb.personUnlinkCompany(idPerson, idCompany);
        
        if(pr.equals(PersonResponse.OK)){
            return Response.status(Response.Status.OK).entity(PersonResponse.OK).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(pr).build();        
    }
    
    @GET
    @Produces("application/json")
    @Path("{id}/details")
    public Response personDetails(@PathParam("id") int id) {        
        Person p;
        
        try{            
            p = pb.personDetails(id);
        } catch(JsonSyntaxException jse){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(gson.toJson("JSON DESERIALIZING ERROR"))
                    .build();
        }
        
        if(p!=null){
            return Response.status(Response.Status.OK).entity(gson.toJson(p)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(PersonResponse.INVALID_PERSON_ID).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("details")
    public Response personsDetails() {        
        return Response.status(Response.Status.OK).entity(gson.toJson(pb.personsDetails())).build();
    }
}
