package com.niit.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection
{
    private static Connection connection;

    public static Connection getConnection()
    {
        String URL="jdbc:mysql://localhost:3306/juckbox";
        String userName="root";
        String password="Haravi@19";

        try
        {
            connection= DriverManager.getConnection(URL,userName,password);
            return connection;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
