package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;
    private Properties prop;
    public static final String FILENAME = "resources/config.properties";

    public static Configurations getInstance() {
        if (Configurations.instance == null) {
            Configurations.instance = new Configurations();
        }

        return Configurations.instance;
    }

    private Configurations() {
       prop = new Properties();
        try (InputStream input = new FileInputStream(FILENAME)) {
            prop.load(input);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    //getters
    public int getThreadPoolSize(){
        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }

    public String getMazeGeneratingAlgorithm(){
        return prop.getProperty("mazeGeneratingAlgorithm");
    }

    public String getMazeSearchingAlgorithm(){
        return prop.getProperty("mazeSearchingAlgorithm");
    }

    //setters
    public void setThreadPoolSize(String val){
        try (OutputStream output = new FileOutputStream(FILENAME)) {
            prop.setProperty("threadPoolSize", val);
            prop.store(output, null);

        }catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void setMazeGeneratingAlgorithm(String val){
        try (OutputStream output = new FileOutputStream(FILENAME)) {
            prop.setProperty("mazeGeneratingAlgorithm",val);
            prop.store(output, null);

        }catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void setMazeSearchingAlgorithm(String val){
        try (OutputStream output = new FileOutputStream(FILENAME)) {
            prop.setProperty("mazeSearchingAlgorithm",val);
            prop.store(output, null);

        }catch (IOException io) {
            io.printStackTrace();
        }
    }



}
