package exweb;

import util.DataSourceProvider;
import util.DbUtil;
import util.FileUtil;
import util.PropertyLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String url = PropertyLoader.getProperty("jdbc.url");
        String schema = FileUtil.readFileFromClasspath("schema.sql");
        DataSourceProvider.setDbUrl(url);

        try(Connection c = DataSourceProvider.getDataSource().getConnection()){

            DbUtil.insertFromString(c,schema);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("db loaded");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
