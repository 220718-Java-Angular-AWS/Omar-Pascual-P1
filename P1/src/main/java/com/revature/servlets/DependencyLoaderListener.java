package com.revature.servlets;

import com.revature.services.DataSourceService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing servlet context...");
        //This will establish the connection early on
        DataSourceService.getConnection();
        //Do things for servlets context initialization...
        System.out.println("Servlets context init...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}