package be.usgprofessionals.controller;

import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import org.springframework.stereotype.Component;

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
@Component
public class DigigramDBController extends DBController {

    private String DBUSER, DBPASS, DBURL;
    private Connection connection;

    public DigigramDBController() {
        super("DBURL2");
    }




}
