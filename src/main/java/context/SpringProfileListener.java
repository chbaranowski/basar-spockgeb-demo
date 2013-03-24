package context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringProfileListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String value = System.getProperty("spring.profiles.active");
        if(value == null) {
            System.setProperty("spring.profiles.active", "production");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
