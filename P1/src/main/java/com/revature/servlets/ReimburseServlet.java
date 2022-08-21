package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Reimburse;
import com.revature.pojos.Users;
import com.revature.services.ReimburseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ReimburseServlet extends HttpServlet {
    //private ReimburseService service;
    ReimburseService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Reimburse servlet initializing...");
        this.service = new ReimburseService();
        this.mapper = new ObjectMapper();
        System.out.println("Reimburse servlet initialized");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("user-id");
        if(param == null) {
            //Return all if no input for request_id detected (unsafe)
            List<Reimburse> userList = service.getAllReimburse();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else {
            //specified request_id input detected (favored)
            Integer reqId = Integer.parseInt(req.getParameter("user-id"));
            Reimburse request = service.getReimburse(reqId);
            String json = mapper.writeValueAsString(request);
            resp.getWriter().println(json);
        }
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        Reimburse reimbursement = mapper.readValue(json, Reimburse.class);
        service.saveReimburse(reimbursement);

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();
        Reimburse updateReimburse = mapper.readValue(json, Reimburse.class);
        service.updateReimburse(updateReimburse);

        //resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("reimburseid");
        Integer reimburseId = Integer.parseInt(param);
        service.deleteReimburse(reimburseId);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
