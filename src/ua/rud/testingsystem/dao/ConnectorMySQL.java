package ua.rud.testingsystem.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class creates connection with database
 */
public class ConnectorMySQL {
    /**
     * Method creates connection with database
     *
     * @return connection with database
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        DataSource ds = null;
        try {
            Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
            ds = (DataSource) envCtx.lookup("jdbc/testingsystem");
        } catch (NamingException e) {
            e.printStackTrace();
            // TODO: 23.07.2016 insert log here
        }
        return ds.getConnection();
    }
}
