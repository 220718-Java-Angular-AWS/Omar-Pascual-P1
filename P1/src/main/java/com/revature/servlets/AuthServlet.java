package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Users;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    ObjectMapper mapper;
    UserService service;

    @Override
    public void init() throws ServletException {
        System.out.println("Longin intializing...");
        this.mapper = new ObjectMapper();
        this.service = new UserService();
        System.out.println("Login initialized");
    }

    @Override
    public void destroy(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Users authUser = service.authenticate(username, password);

        String json = mapper.writeValueAsString(authUser);
        resp.getWriter().print(json);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");

    }
}
