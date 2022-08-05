package dao;

import util.DbUtil;
import util.PropertyLoader;
import util.FileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SetupDAO {

    public void createSchema() {
        String statements = FileUtil.readFileFromClasspath("schema.sql");
        String dbUrl = PropertyLoader.getProperty("javax.persistence.jdbc.url");

        try (Connection c = DriverManager.getConnection(dbUrl)) {
            DbUtil.insertFromString(c, statements);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Database loaded...");
    }

}