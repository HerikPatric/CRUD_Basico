package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBase {
    private static Connection conn = null;

    public static Properties loadProperties(){
       try(FileInputStream fileInputStream = new FileInputStream("db.properties")){
           Properties properties = new Properties();
           properties.load(fileInputStream);
           return properties;
       } catch (IOException e) {
           throw new RuntimeException("Erro ao tentar abrir o arquivo! \n" + e.getMessage());
       }
    }

    public static Connection getConnection(){
        if (conn == null){
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                conn = DriverManager.getConnection(url, properties);
            }catch (SQLException e){
                throw new DataBaseException("Erro ao conectar com o banco de dados! \n" + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if (conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                throw new DataBaseException("Erro ao tentar encerrar a conexao com o banco de dados! \n" + e.getMessage());
            }
        }
    }
    public static void closeStatemente(Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataBaseException("Erro ao encerrar o Statement! " + e.getMessage());
            }
        }
    }
}
