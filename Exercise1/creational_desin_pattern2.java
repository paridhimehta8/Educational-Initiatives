public class ConfigurationManager {
    private static ConfigurationManager instance;
    private String theme = "Light";
    private String language = "English";

    private ConfigurationManager() {}

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}

public class App {
    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        System.out.println("Theme: " + config1.getTheme()); 

        config1.setTheme("Dark");

        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("Theme: " + config2.getTheme()); 
    }
}