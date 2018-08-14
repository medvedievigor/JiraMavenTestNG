package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
    private String userName;
    private String password;
    private String url;

    public String getUserName () {
        return userName;
    }
    public String getPassword () {
        return password;
    }
    public String getsUrl () {
        return url;
    }

    public String   getProjectProperty() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("jira.properties");
            // load a property file
            prop.load(input);
            // get the property value
           userName= prop.getProperty("jiraLogin");
           password=prop.getProperty("jiraPassword");
           url=prop.getProperty("jiraURL");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return url;
    }
}
