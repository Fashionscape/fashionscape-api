package dev.salmonllama.fsapi.pgsql;

import java.sql.*;
import java.util.Properties;

public class PGProvider {
    private final String url = DBConstants.CONN_STRING_FASHIONSCAPE;
    private Connection c;

    public PGProvider() {
        // TODO: Initialization things here?
    }

    private Connection createConnection() {
        try {
            Properties props = new Properties();
            props.setProperty("user", "fashionscape");
            props.setProperty("password", "INSERT PASSWORD HERE"); // TODO: Connect to Azure secrets for password management
            props.setProperty("ssl", "true");
            c = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public Connection getConnection() {
        if (c == null) {
            return createConnection();
        }
        return c;
    }

    private void resolveParameters(PreparedStatement stmt, Object... params) {
        // TODO: Implement parameter resolution, inject SQL types from java POJOs.
        // Something to consider, is this even necessary? Maybe, if there isn't a Spring PGSQL lib with object annotations
    }

    public int query(String sql) throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }

    public int query(String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            resolveParameters(stmt, params);
            return stmt.executeUpdate();
        }
    }

    public int insert(String sql, Object... params) throws SQLException {
        try (
                PreparedStatement query = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = query.getGeneratedKeys()
        ) {
            resolveParameters(query, params);
            query.executeUpdate();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return -1;
        }
    }

    public ResultSet select(String sql, Object... params) throws SQLException {
        PreparedStatement stmt;
        stmt = getConnection().prepareStatement(sql);
        resolveParameters(stmt, params);
        return stmt.executeQuery();
    }
}
