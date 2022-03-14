package br.com.itarocha.dbexport.connection;

import br.com.itarocha.dbexport.config.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class DBConnection {

    @Autowired
    private DbConfig cfg;

    public Connection getConnection(String tipo) throws SQLException {
        Connection connection;
        Properties props = new Properties();
        props.setProperty("user", "SYSDBA");
        props.setProperty("password", "masterkey");
        props.setProperty("encoding", "WIN1252");
        //props.setProperty("encoding", "UNICODE_FSS");
        props.setProperty("sqldialect", "3");
        //props.setProperty("lc_ctype", "WIN1252");
        connection = DriverManager.getConnection(cfg.getUrl() ,props);
        System.err.println("The connection is successfully obtained");
        return connection;
    }
}
