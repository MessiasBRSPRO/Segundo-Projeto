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
        sqlCommand = "INSERT INTO usuarios (mail, password) VALUES(?, ?)";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            operationsSQLExecutor.setString(1, user.getMail());
            operationsSQLExecutor.setString(2, user.getPassword());
            System.out.println(user.getMail() + " has registered in dataBase");
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
                System.out.println("User Mail:"+rows.getString(1) + " | password:"+rows.getString(2));
            }
            System.out.println("Total rows:"+totalRows);
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }
}
