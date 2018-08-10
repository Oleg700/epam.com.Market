package com.epam.market.database;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final int MAX_SIZE= Integer.parseInt(DBManager.getProperty("db.ConPollMaxSize"));
    private static final int MIN_SIZE= Integer.parseInt(DBManager.getProperty("db.ConPollMinSize"));
    private static BlockingQueue<Connection> blockingQueue;
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    public static  synchronized  ConnectionPool getInstance() {
        if(instance == null){
            instance = new ConnectionPool();
            blockingQueue =new ArrayBlockingQueue<>(MAX_SIZE);
        }
        return instance;
    }

    public  Connection getConnectionFromPool()
    {
        if(blockingQueue.size() ==MIN_SIZE){
            this.fillConnectionPool();
        }
        Connection connection= null;
        try {
            connection = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            LOGGER.error("thread interrupted by getting connection from connection pool",e);
        }
        return (connection);
    }


    public  Connection makeConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Error Class.forName(com.mysql.jdbc.Driver) is not found",e);
            e.printStackTrace();
        }
        String url = DBManager.getProperty("db.url");
        String login = DBManager.getProperty("db.login");
        String password = DBManager.getProperty("db.password");
        connection = DriverManager.getConnection(url, login, password);
        return connection;
    }

    public void fillConnectionPool(){
        for(int i=0;i<MAX_SIZE;i++)
        {
            try {
                blockingQueue.put(makeConnection());
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error("Error putting connection to blocking queue",e);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                LOGGER.error("thread interrupted, when it fills connection pool",e);
            }
        }

    }

}
