package com.revature.services;

import com.revature.daos.UserDAOS;
import com.revature.pojos.Users;

import java.util.List;

public class UserService {
    //We will need a dao object to dereference
    //Make it private because nobody needs to worry about the reference. Only calling code within this class
    //needs to worry about that
    private UserDAOS dao;


    public UserService() {
        this.dao = new UserDAOS();
    }

    //This will call the create method
    public void saveUser(Users users){
        dao.create(users);
    }

    public Users getUser(int id){
        return dao.read(id);
    }
    public List<Users> getAllUsers(){
        return dao.readAll();
    }

    public void updateUser(Users users){
        dao.update(users);
    }

    public void deleteUser(int id){
        dao.delete(id);
    }
}
