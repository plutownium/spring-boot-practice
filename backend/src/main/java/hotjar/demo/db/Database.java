package hotjar.demo.db;

import org.apache.catalina.User;
import org.hibernate.bytecode.internal.bytebuddy.PassThroughInterceptor;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.Driver;
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

    private String mode;

    public Database(String mode) {
        this.mode = mode;
    }

    // init database constants
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/student";
    private static final String TEST_DB_DRIVER = DATABASE_DRIVER;
    private static final String TEST_DB_URL = "jdbc:postgresql://localhost:5432/studentstest";
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

    private Properties getTestDbProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", "postgres");
            
            properties.setProperty("password", "postgres");
            
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
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
                System.out.println(ANSI_GREEN + mode + ANSI_RESET);
                boolean testingMode = new String("testing").equals(mode);
                if (testingMode) {
                    Class.forName(TEST_DB_DRIVER);
                    connection = (Connection) DriverManager.getConnection(TEST_DB_URL, getTestDbProperties());
                    System.out.println(ANSI_GREEN + "Testing db connection established" + ANSI_RESET);
                } else {
                    Class.forName(DATABASE_DRIVER);
                    System.out.println(ANSI_GREEN + "Dev db connection established" + ANSI_RESET);
                    connection = (Connection) DriverManager.getConnection(DATABASE_URL, getProperties());
                }
            }
            catch (ClassNotFoundException | SQLException e)
            {
                System.out.println(ANSI_RED + "PROBLEM\n#########\n#######" + ANSI_RESET);
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println(ANSI_RED + "########\n#######\n"+ANSI_RESET);
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
        // ##
        // heavy lifter
        // ##
        statement = connection.createStatement();
        System.out.println(query);
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
        statement = connection.createStatement();

        String sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
        "(ID                    SERIAL PRIMARY KEY," +
        " NAME                  TEXT    NOT NULL, " +
        " EMAIL                 TEXT    NOT NULL, " +
        " DOB                   DATE    NOT NULL, " +
        " AGE                   INT     NOT NULL, " +
        " ADDRESS               CHAR(50), " +
        " GPA                   REAL)", tableName);
        ResultSet resultSet = statement.executeQuery(sql);
        
        return resultSet;   
    }
    
} 
