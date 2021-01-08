package com.example.erp.controller;

import com.example.erp.bean.Bill;
import com.example.erp.bean.LoginDetails;
import com.example.erp.service.operationService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;


@Path("operation")
public class OperationController {
    operationService operationService = new operationService();


    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createBill(@FormDataParam("description") String description,
                               @FormDataParam("amount") Integer amount,
                               @FormDataParam("date") String date,
                               @FormDataParam("status") String status) throws URISyntaxException {
        Bill bill = new Bill(description, amount, date, status);
        System.out.println(bill.getDescription());
        System.out.println(bill.getAmount());
        operationService operationServ = new operationService();
        if (!operationServ.createBill(bill)) {
            System.out.println("No data received by create bill controller");
            return Response.status(203).build();
        }
        return Response.ok().build();
    }


    @POST
    @Path("/update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBill(@FormDataParam("bill_id") Integer bill_id,
                               @FormDataParam("amount") Integer amount,
                               @FormDataParam("status") String status) throws URISyntaxException {
        System.out.println("INSIDE UPDATE CONTROLLER");
        Bill bill = new Bill(null, amount, null, status);
        if (!operationService.updateBill(bill_id, bill)) {
            System.out.println("No data received by update bill controller");
            return Response.status(203).build();
        }
        return Response.ok().build();

    }

    @POST
    @Path("/read")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response readBill(@FormDataParam("bill_id") int bill_id) throws URISyntaxException {
        System.out.println("READ Controller" + " bill_id=" + bill_id);
        Bill bill = operationService.readBill(bill_id);
        System.out.println("READ controller reponse Amount: "+bill.getAmount());
        return Response.ok().entity(bill).build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBill(@FormDataParam("bill_id") Integer bill_id) throws URISyntaxException {
        System.out.println("INSIDE DELETE CONTROLLER");
        if (!operationService.deleteBill(bill_id)) {
            System.out.println("No data received by delete bill controller");
            return Response.status(203).build();
        }
        return Response.ok().build();
    }
}