package br.com.SegundoProjeto.DataBaseClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {
    private Connection connection;
    private String urlConnection;
    private String userDataBase = "postgres";
    private String passwordDataBase = "Afton3444$";

    public Connection startConnection(String dataBaseName, int port, String tableName){
        urlConnection = "jdbc:"+dataBaseName + "://localhost:"+port +"/"+tableName;
        try {
            connection = DriverManager.getConnection(urlConnection, userDataBase, passwordDataBase);
            if(connection != null){
                System.out.println(dataBaseName + " connected in "+ tableName);
                return connection;
            }
        }catch (SQLException e){
            System.out.println("an Exception has occurred:"+e.getMessage());
            System.out.println("Occurred in:"+e.getStackTrace());
        }
        return connection;
    }
}
