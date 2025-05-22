package productionCode;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> env = EnvLoader.loadEnv("key.env");

        String apiKey = env.get("API_KEY");
        String dbUrl = env.get("DB_URL");

        System.out.println("API Key: " + apiKey);
        System.out.println("DB URL: " + dbUrl);
    }
}
