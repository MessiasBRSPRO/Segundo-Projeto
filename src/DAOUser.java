import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser {

    private ConnectDataBase connectDataBase;
    private Connection connection;
    private String sqlCommand;

    public DAOUser(){
        this.connectDataBase = new ConnectDataBase();
        this.connection = connectDataBase.startConnection("postgresql", 5432, "usuarios");
    }
    public void insertIntoDataBase(User user){
        sqlCommand = "INSERT INTO usuarios (username, mail, password) VALUES(?, ?, ?)";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            operationsSQLExecutor.setString(1, user.getUsername());
            operationsSQLExecutor.setString(2, user.getMail());
            operationsSQLExecutor.setString(3, user.getPassword());
            System.out.println(user.getUsername() + " has registered in dataBase");
            operationsSQLExecutor.execute();
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }

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

    public void seeAllRows(){
        sqlCommand = "SELECT * FROM usuarios";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            ResultSet rows = operationsSQLExecutor.executeQuery();
            int totalRows = 0;
            while(rows.next()){
                totalRows++;
                System.out.println("Username:"+rows.getString(1) + " | Mail:"+rows.getString(2) + " | password:"+rows.getString(3));
            }
            System.out.println("Total rows:"+totalRows);
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }

    public void specifiedRowSearch(String username){
        sqlCommand = "SELECT * FROM usuarios WHERE username=?";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            operationsSQLExecutor.setString(1, username);
            ResultSet rows = operationsSQLExecutor.executeQuery();
            int results = 0;
            while(rows.next()){
                results++;
                System.out.println("Username:"+rows.getString(1) + " | Mail:"+rows.getString(2) + " | password:"+rows.getString(3));
            }
            System.out.println("Results:"+results);
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }
}
