package com.revature.daos;

import com.revature.pojos.Reimburse;
import com.revature.services.DataSourceService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReimburseDAOS implements DataSourceCrud<Reimburse> {
    Connection connection;

    public ReimburseDAOS() {
        //This will let me get the connection from somewhere else AKA DatasourceService
        //connection = DataSourceService.getConnection();
        this.connection = DataSourceService.getConnection();
    }

    //@Override
    public void create(Reimburse reimburse) {
        try {
            //Create the String
            //parameterize the statement
            //execute the statement
            //marshal the result set

            //They are ? because we will parameterize later
            String sql = "INSERT INTO reimburse (user_id, ticket, reason, amount, pending) VALUES (?,?,?,?,false)";
            //PreparedStatement is the type that we're going to store the reference into
            //Not user "new" keyword because it's an interface, not a class. Getting back some inherited concrete
            //class from what were going to do with the connection
            //sql will be what the statement will be built out of
            PreparedStatement pstmt = connection.prepareStatement(sql);

            //Step2: Parameterize
            //You get the data from the task class by the calling code. The parameter list. ( task)
            pstmt.setInt(1, reimburse.getUserId());
            pstmt.setInt(2, reimburse.getTicket());
            pstmt.setString(3, reimburse.getReasons());
            pstmt.setDouble(4, reimburse.getAmount());
            pstmt.setBoolean(5, reimburse.getPending());
            //Step3: Execute the statement
            //We use execute update because we are creating something in the database
            //And only 1 row will be effected.
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public Reimburse read(int id) {
        Reimburse reimburse = new Reimburse();

        try {
            String sql = "SELECT * FROM reimburse WHERE reimburse_id = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);

            //This time the execute will be Query because we want the result set this time
            ResultSet results = pstmt.executeQuery();
            //The Resultset will be task. So make task into an object
            //We have en empty constructor because we want to be able to create empty objects to input
            //the results into an empty object

            //This will allow us to see the row
            if (results.next()) {
                reimburse.setReimburseId(results.getInt("reimburse_id"));
                reimburse.setUserId(results.getInt("user_id"));
                reimburse.setTicket(results.getInt("ticket"));
                reimburse.setReasons(results.getString("reasons"));
                reimburse.setAmount(results.getDouble("amount"));
                reimburse.setPending(results.getBoolean("pending"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimburse;
    }


    //@Override
    public List<Reimburse> readAll() {
        List<Reimburse> reimburseList = new LinkedList<>();
        try{
            String sql = "SELECT * FROM reimburse";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet results = pstmt.executeQuery();
            //As long as there is somewhere to advance the cursor to, do function
            while(results.next()){
                //We make the object in here because everytime we iterate through the list in each row, we
                //need a whole new task object. A new one must be created each time
                Reimburse reimburse = new Reimburse();

                reimburse.setReimburseId(results.getInt("reimburse_id"));
                reimburse.setUserId(results.getInt("user_id"));
                reimburse.setTicket(results.getInt("ticket"));
                reimburse.setReasons(results.getString("reasons"));
                reimburse.setAmount(results.getDouble("amount"));
                reimburse.setPending(results.getBoolean("pending"));
                //add the task in the list
                reimburseList.add(reimburse);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimburseList;
    }

    //@Override
    //Think of parameter list as how you get stuff into method
    //return type is how you get stuff out of method.
    public void update(Reimburse reimburse) {
        try{
            //We are not updating the id here because it's serialized. It's the same everytime
            //Don't need to change ID because we don't need to create a new entry in the database or advance the entry
            //to another point in the database.
            String sql = "UPDATE reimburse SET user_id = ?, ticket = ?, reasons = ?, amount = ?, pending = ?,  WHERE reimburse_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            //Previously querey the database and gotten the task object out of it. Return the path object
            //pass it to update after making changes.
            pstmt.setInt(1, reimburse.getUserId());
            pstmt.setInt(2, reimburse.getTicket());
            pstmt.setString(3, reimburse.getReasons());
            pstmt.setDouble(4, reimburse.getAmount());
            pstmt.setBoolean(5, reimburse.getPending());
            pstmt.setInt(6, reimburse.getReimburseId());

            //We use update because we don't really care about the row count. Row count would be 1.
            //It would be 0 if there is no matching apss update.
            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //@Override
    public void delete(int id) {
        try{
            String sql = "DELETE FROM reimburse WHERE reimburse_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}

