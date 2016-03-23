package be.usgprofessionals.controller;

import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Thomas Straetmans on 02/03/16.
 * <p>
 * Digidatabase for USG Professionals
 */
public abstract class DBController {

    private String DBUSER, DBPASS, DBURL;
    protected Connection connection;

    public DBController(String urltag) {
        Properties prop = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(is);
            DBUSER = prop.get("DBUSER").toString();
            DBPASS = prop.get("DBPASS").toString();
            DBURL = prop.get(urltag).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected SQLQuery createConnectionQuery() {
        createConnection();
        SQLTemplates dialect = new MySQLTemplates();
        return new SQLQuery(connection, dialect);
    }

    protected SQLInsertClause createInsertClause(RelationalPathBase RPB) {
        createConnection();
        SQLTemplates dialect = new MySQLTemplates();
        return new SQLInsertClause(connection, dialect, RPB);
    }

    protected SQLDeleteClause createDeleteClause(RelationalPathBase RPB) {
        createConnection();
        SQLTemplates dialect = new MySQLTemplates();
        return new SQLDeleteClause(connection, dialect, RPB);
    }

    private void createConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
