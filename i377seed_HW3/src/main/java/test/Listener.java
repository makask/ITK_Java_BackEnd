package test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String contents = Util.readFileFromClasspath("schema.sql");

        System.out.println(contents);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}