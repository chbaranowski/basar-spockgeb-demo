package runner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Webapp {

    boolean isRunning = false;

    Server server;

    int port = 8881;

    WebApplicationContext webApplicationContext;

    public Webapp(){
    }

    public Webapp(int port){
        this.port = port;
    }

    public void start() throws Exception {
        if (!isRunning) {
            server = new Server(port);
            WebAppContext context = new WebAppContext();
            context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
            context.setResourceBase("src/main/webapp/");
            context.setContextPath("/");
            context.setParentLoaderPriority(true);
            server.setHandler(context);
            server.start();
            webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context.getServletContext());
            isRunning = true;
        }
    }

    public void stop() throws Exception {
        if (isRunning) {
            webApplicationContext = null;
            server.stop();
            server = null;
            isRunning = false;
        }
    }

    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public int getPort() {
        return port;
    }
}

