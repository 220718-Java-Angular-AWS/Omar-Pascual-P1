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
        String param = req.getParameter("reimburse-id");
        if(param == null) {
            //Return all
            List<Reimburse> reimburseList = service.getAllReimburse();
            String json = mapper.writeValueAsString(reimburseList);
            resp.getWriter().println(json);
        } else {
            //return the one the request wants
            Integer reimburseId = Integer.parseInt(req.getParameter("reimburse-id"));

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

        while(buffer.ready()){
            builder.append(buffer.readLine());
        }
        String json = builder.toString();
        Reimburse reimburse = mapper.readValue(json, Reimburse.class);

        service.saveReimburse(reimburse);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
