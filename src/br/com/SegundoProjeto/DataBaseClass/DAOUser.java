package br.com.SegundoProjeto.DataBaseClass;

import br.com.SegundoProjeto.Services.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser {

    private ConnectDataBase connectDataBase;
    private Connection connection;
    private String sqlCommand;
    private static int totalRows = 0;

    public DAOUser(){
        this.connectDataBase = new ConnectDataBase();
        this.connection = connectDataBase.startConnection("postgresql", 5432, "usuarios");
    }

    //This method insert an user in dataBase
    public void insertIntoDataBase(User user){
        sqlCommand = "INSERT INTO usuarios (username, mail, password) VALUES(?, ?, ?)";
        try{
            if(user.getMail() != null && user.getPassword() != null){
                //Only be added in DB if mail and password is valid.
                PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
                operationsSQLExecutor.setString(1, user.getUsername());
                operationsSQLExecutor.setString(2, user.getMail());
                operationsSQLExecutor.setString(3, user.getPassword());
                System.out.println(user.getUsername() + " has registered in dataBase");
                operationsSQLExecutor.execute();
            }else{
                System.out.println("Please have an error in your information in register, please check!");
            }
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }

    //this method delete an row from the mail of an user
    public void deleteFromDataBase(User user){
        sqlCommand = "DELETE FROM usuarios WHERE mail=?";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            operationsSQLExecutor.setString(1, user.getMail());
            System.out.println(user.getUsername() + " has removed from Database");
            operationsSQLExecutor.execute();
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }

    //This method displays all rows in Database usuarios
    public void seeAllRows(){
        sqlCommand = "SELECT * FROM usuarios";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            ResultSet rows = operationsSQLExecutor.executeQuery();
            while(rows.next()){
                totalRows++;
                System.out.println("Username:"+rows.getString(1) + " | Mail:"+rows.getString(2) + " | password:"+rows.getString(3));
            }
            System.out.println("Total rows:"+totalRows);
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }

    //this method is for if is necessary an specified user search
    public String specifiedRowSearch(User user){
        sqlCommand = "SELECT * FROM usuarios WHERE mail=? and password=?";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            operationsSQLExecutor.setString(1, user.getMail());
            operationsSQLExecutor.setString(2, user.getPassword());
            ResultSet rows = operationsSQLExecutor.executeQuery();
            while(rows.next()){
                totalRows++;
                return "Username:"+rows.getString(1) + " | Mail:"+rows.getString(2) + " | password:"+rows.getString(3);
            }
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
        return String.valueOf(totalRows);
    }

    /*for a future use....
     public void updateMail(String newMail, br.com.SegundoProjeto.Services.User user){
        sqlCommand = "UPDATE usuarios SET mail=? WHERE mail=?";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            operationsSQLExecutor.setString(1, newMail);
            operationsSQLExecutor.setString(2, user.getMail());
            operationsSQLExecutor.executeUpdate();
            System.out.println("Your mail has updated!");
        }catch (SQLException e){
            System.out.println("an Exception has occurred:" +e.getMessage());
        }
    }
    * */

    public void updatePassword(String newPassword, User user){
        sqlCommand = "UPDATE usuarios SET password=? WHERE mail=?";
        try{
            PreparedStatement operationsSQLExceutor = connection.prepareStatement(sqlCommand);
            operationsSQLExceutor.setString(1, newPassword);
            operationsSQLExceutor.setString(2, user.getMail());
            operationsSQLExceutor.executeUpdate();
            System.out.println("your password has been updated");
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }

    public static int getTotalRows() {
        return totalRows;
    }
}
