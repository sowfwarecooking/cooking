package ProductionCode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class llmConnction {


    /**
     * Generates a prompt string based on a dietary restriction.
     *
     * @author Mohammed Saeed Enab
     * @date 2025-08-10
     * @param restriction The dietary restriction to consider (e.g., vegetarian, gluten-free).
     * @return A prompt string requesting a single meal name based on the given restriction.
     */
    String promrtMaker(String restriction) {
        return "Given the following information:\n\n" +
                "Dietary restriction: " + restriction + "\n\n" +
                "Give me the name of just one meal. No ingredients or how to make it. Just give me the name. no other information no greadding just the name of the name no intoddaction\n" +
                "if it was not possible to find a suitable meal, just say 'no suitable meal found'"+
                "if you find one just add " + restriction + " to it";
    }

    /***
     *
     * @author Mohammed Saeed Enab
     * @date 2025-08-10
     * @param time
     * @return A prompt string requesting a single meal name based on the given time
     */
    String promrtBestTime(int time) {
        return "Given the following information:\n\n" +
                "Time: " + time + "\n\n" +
                "Give me the name of just one meal. No ingredients or how to make it. Just give me the name. no other information no greadding just the name of the name no intoddaction\n" +
                "if it was not possible to find a suitable meal, just say 'no suitable meal found'"+
                "if you find one just add " + time + " to it";

    }

    /***
     *
     * @param time
     * @param restriction
     * @param ingredient
     * @return A prompt string requesting a single meal name based on the given restriction , available ingredient and time
     */
    String promrtAll(int time , String restriction , String ingredient) {
        return "Given the following information:\n\n" +
                "Time: " + time + "\n\n" +
                "Dietary restriction: " + restriction + "\n\n" +
                "Ingredient: " + ingredient + "\n\n" +
                "Give me the name of just one meal. No ingredients or how to make it. Just give me the name. no other information no greadding just the name of the name no intoddaction\n" +
                "if it was not possible to find a suitable meal, just say 'no suitable meal found'";

    }

    public String timeSugest(int time) {
        String reply = null;
        String prompt = promrtBestTime(time);
        reply = sugestmeal(prompt);

        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return reply;

    }

    /***
     * Sends a dietary restriction prompt to the OpenRouter API and retrieves a suggested meal name.
     * <p>
     * The response will contain the name of a meal that fits the specified dietary restriction,
     * or a message indicating that no suitable meal was found.
     * @auther Mohammed Saeed Enab
     * @date 2025-08-10
     *
     * @param restriction The user's dietary restriction (e.g., "vegan", "gluten-free").
     * @return A string containing either the name of a suitable meal or a fallback message.

     */

    public String sugestmeal(String restriction ) {
        String reply = null;

            String prompt = promrtMaker(restriction);

        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return reply;
    }

    /***
     **
     * Sends a prompt to the OpenRouter API to suggest a meal based on preparation time,
     * dietary restriction, and a key ingredient.
     * <p>
     * The API is expected to return a detailed meal suggestion suitable for the input parameters.
     *
     * @param time        The maximum cooking time allowed (in minutes).
     * @param restriction The dietary restriction to follow.
     * @param ingredient  A key ingredient the user wants in the meal.
     * @return A string containing the suggested meal description, or a fallback message.
     */

    public String sugestmealBasedONIngredients(int time , String restriction , String ingredient) {
        String reply = null;

        String prompt = promrtAll(time , restriction , ingredient);

        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return reply;

    }
}
