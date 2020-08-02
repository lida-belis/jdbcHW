package services;

import org.apache.log4j.Logger;

import java.sql.*;

public class JDBCService {
    static Logger logger = Logger.getLogger(JDBCService.class);

    Connection connection;
    Statement statement;

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String username = "postgres";
    static final String password = "bellida88";

    public JDBCService() {
        logger.info("Setup connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, username, password);
        } catch (ClassNotFoundException e) {
            logger.error("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            logger.error(e.getMessage());
            return;
        }catch (SQLException e) {
            logger.error("Connection Failed");
            logger.error(e.getMessage());
            return;
        }

        if (connection != null) {
            logger.info("You successfully connected to database now");
        } else {
            logger.error("Failed to make connection to database");
        }
    }

    public Connection getDBConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            logger.error("Close connection Failed");
            logger.error(throwables.getMessage());
        }
    }

    public Statement getStatement() {
        try {
            if (statement == null) {
                statement = getDBConnection().createStatement();
            }

            logger.info("Statement is ready.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return statement;
    }

    public void closeStatement() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void executeSQL(String sql) {
        try {
            getStatement().execute(sql);
            logger.info("SQL: " + sql + " has been executed.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
            logger.info("SQL: " + sql + " has been executed.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultSet;
    }

    public void dropAllTables() {
        String createTableSQL = "DROP SCHEMA public CASCADE; " +
                "CREATE SCHEMA public;";

        executeSQL(createTableSQL);
    }
}
