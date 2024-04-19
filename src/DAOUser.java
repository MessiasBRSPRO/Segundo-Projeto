import java.sql.Connection;
import java.sql.PreparedStatement;
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
        sqlCommand = "DELETE FROM usuarios WHERE mail=? AND password=?";
        try{
            PreparedStatement operationsSQLExecutor = connection.prepareStatement(sqlCommand);
            operationsSQLExecutor.setString(1, user.getMail());
            operationsSQLExecutor.setString(2, user.getPassword());
            operationsSQLExecutor.execute();
        }catch (SQLException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }
    }
}
