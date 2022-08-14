package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.ReimburseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ReimburseServlet extends HttpServlet {
    //private ReimburseService service;
    ReimburseService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Reimburse servlet initializing...");
        this.service = new ReimburseService();
        //this.mapper = new ObjectMapper();
        System.out.println("Reimburse servlet initialized");
    }

    @Override
    public void destroy() {
    }
}
