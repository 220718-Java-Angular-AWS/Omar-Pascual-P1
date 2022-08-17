package com.revature.daos;

import com.revature.pojos.Users;
import com.revature.services.DataSourceService;
import jdk.internal.org.objectweb.asm.commons.RemappingSignatureAdapter;

import java.nio.file.AccessDeniedException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAOS implements DataSourceCrud<Users>{

    Connection connection;

    public UserDAOS() {
        this.connection = DataSourceService.getConnection();
    }


    @Override
    public void create(Users user) {
        try {
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                Integer key = keys.getInt("user_id");
                user.setUserId(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Users read(int id) {
        Users user = new Users();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setUsername(results.getString("username"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<Users> readAll() {
        List<Users> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();



            while(results.next()) {
                Users user = new Users();
                user.setUserId(results.getInt("user_id"));
                user.setUsername(results.getString("username"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void update(Users user) {

        try {
            String sql = "UPDATE users SET username = ?, email = ?, password = ?, WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getUserId());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Users authenticate(String username, String password){
        Users users = new Users();

        try{
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet results = pstmt.executeQuery();

            if(results.next()){
                users.setUserId(results.getInt("user_id"));
                users.setUsername(results.getString("username"));
                users.setEmail(results.getString("email"));
                users.setPassword(results.getString("password"));
                users.setAdmin(results.getBoolean("admin"));

            }else{
                throw new AccessDeniedException("Access Denied!");
            }

        }catch(SQLException | AccessDeniedException e){
            throw new RuntimeException(e);
        }
        return users;
    }
}