package org.qf.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * JDBC操作数据库的工具类
 */
public class BaseDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static { initial();}
    public static void initial(){
        Properties properties = new Properties();
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try{
            properties.load(inputStream);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public boolean getConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void closeAll(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    //增删改
    public int executeUpdate(String sql,Object[] params){
        int count = 0;
        if(this.getConnection()){
            try{
                preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i+1,params[i]);
                }
                count = preparedStatement.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return count;
    }
    //查询
    public ResultSet executeQuery(String sql,Object[] params){
        if(this.getConnection()){
            try {
                preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i+1,params[i]);
                }
                resultSet = preparedStatement.executeQuery();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return  resultSet;
    }
}
