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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        Users users = mapper.readValue(json, Users.class);
        Users authUser = service.authenticate(users.getUsername(), users.getPassword());

        if(authUser != null){
            resp.setStatus(200);
            resp.getWriter().write(mapper.writeValueAsString(authUser));
            resp.setHeader("JWT", String.valueOf(users.getUserId()));
        }else{
            resp.setStatus(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Users authUser = service.authenticate(username, password);

        String json = mapper.writeValueAsString(authUser);
        resp.getWriter().println(json);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");

    }
}
