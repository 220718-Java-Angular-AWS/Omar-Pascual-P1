package com.revature.daos;
import java.util.List;

public interface DataSourceCrud<T> {
    //The interface which we will implement in each of the actual DAOs
    //CRUD CREATE READ UPDATE DELETE


    //void because we aren't returning anything
    void create(T t);
    //For read, we want to return some type of information
    //This will be the read only one thing and find that thing though index
    T read(int id);
    //List all the users
    List<T> readAll();
    void update(T t);
    void delete(int id);
}
