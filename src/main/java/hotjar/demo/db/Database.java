package hotjar.demo.db;

import org.apache.catalina.User;
import org.hibernate.bytecode.internal.bytebuddy.PassThroughInterceptor;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Simple JAVA Database Connection Class
 * 
 * Date: 2018/05/08
 * @author sithira
 */
public class Database
{


    // @Value("${spring.datasource.url}")
    // private String DATABASE_URL;

    // @Value("${spring.datasource.username}")
    // private String USERNAME;

    // @Value("${spring.datasource.password}")
    // private String PASSWORD;


    // init database constants
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/student";
    // private static final String USERNAME = "root";
    // private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    // init connection object
    private Connection connection;
    
    // init properties object
    private Properties properties;

    // init the statement
    private Statement statement;
    
    // create properties
    private Properties getProperties()
    {
        if (properties == null)
        {
            properties = new Properties();

            // System.out.println(USERNAME);
            // System.out.println(PASSWORD);
        
            properties.setProperty("user", "postgres");
            
            properties.setProperty("password", "postgres");
            
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        
        return properties;
    }

    /**
     * Connect to the database  
     * 
     * @return Connection
     */
    public Connection connect()
    {
        if (connection == null)
        {         
            try
            {
                Class.forName(DATABASE_DRIVER);
                
                connection = (Connection) DriverManager.getConnection(DATABASE_URL, getProperties());
            }
            catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Disconnect database
     */
    public void disconnect()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
             
                connection = null;
                
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Return the result set when a correct SQL statement is provided
     * 
     * @param query
     * @return
     * @throws SQLException 
     */
    public ResultSet operate(String query) throws SQLException
    {        
        statement = connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery(query);
        
        return resultSet;        
    }
    
    /**
     * Return the status when a SQL query is provided for INSERT, UPDATE or DELETE
     * 
     * @param query
     * @return
     * @throws SQLException 
     */
    public int createOrUpdateOrDelete(String query) throws SQLException
    {        
            statement = connection.createStatement();
            
            int result = statement.executeUpdate(query);
            
            return result;       
    }

    public ResultSet createTableIfNotExists(String tableName) throws SQLException {
        System.out.println(tableName + " foo foo foo");
        statement = connection.createStatement();

        String sql = String.format("CREATE TABLE %s " +
        "(ID INT PRIMARY KEY     NOT NULL," +
        " NAME           TEXT    NOT NULL, " +
        " EMAIL          TEXT    NOT NULL, " +
        " DOB            DATE    NOT NULL, " +
        " AGE            INT     NOT NULL, " +
        " ADDRESS        CHAR(50), " +
        " GPA            REAL)", tableName);
        ResultSet resultSet = statement.executeQuery(sql);
        
        return resultSet;   
    }
    
} 
