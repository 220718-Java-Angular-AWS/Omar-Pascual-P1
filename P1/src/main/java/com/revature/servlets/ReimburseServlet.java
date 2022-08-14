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
        Integer userId = Integer.parseInt(req.getParameter("user-id"));

        List<Reimburse> reimburseList = service.getReimburseForUser(userId);
        String json = mapper.writeValueAsString(reimburseList);

        resp.getWriter().print(json);
        resp.setStatus(200);
        resp.setContentType("Application/Json");
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
