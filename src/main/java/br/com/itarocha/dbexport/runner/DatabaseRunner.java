package br.com.itarocha.dbexport.runner;

import br.com.itarocha.dbexport.connection.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseRunner {

    @Autowired
    private DBConnection dbcon;

    public void go(){
        Connection con = null;
        DatabaseMetaData metadata = null;
        try {
            con = dbcon.getConnection("firebird");
        } catch (SQLException e) {
            System.err.println("There was an error getting the connection: " + e.getMessage());
        }

        try {
            metadata = con.getMetaData();
        } catch (SQLException e) {
            System.err.println("There was an error getting the metadata: " + e.getMessage());
        }

        System.out.println("DEU CERTO!!!");
        try {
            List<String> tabelas = getTablesMetadata(metadata);
            for (String tabela : tabelas) {
                System.out.println(tabela);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTablesMetadata(DatabaseMetaData metadata) throws SQLException {
        String table[] = { "TABLE" };
        ResultSet rs = null;
        // receive the Type of the object in a String array.
        rs = metadata.getTables(null, null, null, table);
        List<String> tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

}
