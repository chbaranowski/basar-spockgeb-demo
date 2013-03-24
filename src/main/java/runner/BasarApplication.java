package runner;

public class BasarApplication {

    public static void main(String[] args) throws Exception {
        Webapp webapp = new Webapp(8081);
        webapp.start();
    }

}
