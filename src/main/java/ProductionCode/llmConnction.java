package ProductionCode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class llmConnction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter dietary restriction: ");
        String restriction = scanner.nextLine();

        llmConnction app = new llmConnction();
        String meal = app.sugestmeal(restriction);
        System.out.println("Suggested meal: " + meal);
    }

    // Builds the prompt with correct formatting
    String promrtMaker(String restriction) {
        return "Given the following information:\n\n" +
                "Dietary restriction: " + restriction + "\n\n" +
                "Give me the name of just one meal. No ingredients or how to make it. Just give me the name. no other information no greadding just the name of the name no intoddaction\n" +
                "if it was not possible to find a suitable meal, just say 'no suitable meal found'"+
                "if you find one just add " + restriction + " to it";
    }
    String promrtBestTime(int time) {
        return "Given the following information:\n\n" +
                "Time: " + time + "\n\n" +
                "Give me the name of just one meal. No ingredients or how to make it. Just give me the name. no other information no greadding just the name of the name no intoddaction\n" +
                "if it was not possible to find a suitable meal, just say 'no suitable meal found'";

    }

    // Sends prompt and returns the response
    public String sugestmeal(String restriction) {
        String reply = null;
        String prompt = promrtMaker(restriction);

        try {
            URL url = new URL("https://openrouter.ai/api/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer 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"); // Keep this secure!
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "deepseek/deepseek-prover-v2:free");

            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.put(userMessage);

            requestBody.put("messages", messages);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Error: " + statusCode);
                try (BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.out.println("Error Response: " + errorResponse);
                }
            } else {
                StringBuilder response = new StringBuilder();
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line.trim());
                    }
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                reply = jsonResponse
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");

                return reply.trim();
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return reply;
    }
}
