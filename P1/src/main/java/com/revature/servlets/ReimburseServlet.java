package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Reimburse;
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
        String param = req.getParameter("reimburseId");

        if(param == null) {
            List<Reimburse> userList = service.getAllReimburse();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else {
            Integer reimburseId = Integer.parseInt(req.getParameter("reimburseId"));
            Reimburse reimburse = service.getReimburse(reimburseId);
            String json = mapper.writeValueAsString(reimburse);
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

        Reimburse newReimbursement = mapper.readValue(json, Reimburse.class);
        service.saveReimburse(newReimbursement);

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()){
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        Reimburse newReimburse = mapper.readValue(json, Reimburse.class);
        service.updateReimburse(newReimburse);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
