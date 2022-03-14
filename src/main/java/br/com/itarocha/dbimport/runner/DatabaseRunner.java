package br.com.itarocha.dbimport.runner;

import br.com.itarocha.dbimport.connection.DBConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRunner {

    static Connection connection = null;
    static DatabaseMetaData metadata = null;

    // Static block for initialization
    static {
        try {
            connection = DBConnection.getConnection("firebird");
        } catch (SQLException e) {
            System.err.println("There was an error getting the connection: " + e.getMessage());
        }

        try {
            metadata = connection.getMetaData();
        } catch (SQLException e) {
            System.err.println("There was an error getting the metadata: " + e.getMessage());
        }
    }

    public void go(){
        System.out.println("DEU CERTO!!!");
        try {
            List<String> tabelas = getTablesMetadata();
            for (String tabela : tabelas) {
                System.out.println(tabela);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getTablesMetadata() throws SQLException {
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
