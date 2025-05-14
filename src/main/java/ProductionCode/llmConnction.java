package ProductionCode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class llmConnction {

  private static   String apiKey;
  private EnvLoader env ;

    llmConnction() {
        env = new EnvLoader();
        apiKey = env.loadEnv("key.env").get("API_KEY");
    }
    /**
     * Generates a prompt string based on a dietary restriction.
     */
    String promrtMaker(String restriction) {
        return "Given the following information:\n\n" +
                "Dietary restriction: " + restriction + "\n\n" +
                "Give me the name of just one meal. No ingredients or how to make it. Just give me the name. No other information, no greeting, just the name. No introduction.\n" +
                "If it was not possible to find a suitable meal, just say 'no suitable meal found.'\n" +
                "If you find one, just add " + restriction + " to it.";
    }

    /**
     * Generates a prompt string based on the time.
     */
    String promptExactMealTime(int time) {
        return "Given the time constraint of " + time + " minutes, determine the name of a suitable meal that can be prepared exactly within that time. " +
                "If a suitable meal is found, return just the meal name followed by the exact time it takes to prepare in minutes. " +
                "If no meal can be prepared within the exact given time, respond with 'not suitable'.\n\n" +
                "Example Input: \"I have " + time + " minutes to make a meal.\"\n" +
                "Expected Output: \"Spaghetti Aglio e Olio - " + time + " minutes\" (if a suitable meal is found).\n" +
                "Expected Output: \"not suitable\" (if no meal can be prepared in the exact given time).";
    }

    /**
     * Generates a prompt string based on time, restriction, and ingredient.
     */
    String promptAll(int time, String restriction, String ingredient) {
        return   "You are an AI assistant that recommends meal names based on the following input:\n" +
                "- Time available to cook (in minutes)\n" +
                "- Dietary restrictions\n" +
                "- Provided ingredients\n\n" +
                "Rules:\n" +
                "1. Only suggest a meal that fits ALL the given ingredients, time, and dietary restrictions.\n" +
                "2. The meal must be vegan if 'vegan' is specified — no animal products.\n" +
                "3. Use all provided ingredients in the recipe. Do not suggest meals that exclude any of them.\n" +
                "4. If a matching meal is found, reply only with the meal name followed by the total time it takes (e.g., 'Tomato Basil Soup - 40 minutes').\n" +
                "5. If no meal matches, reply with 'not suitable'.\n\n" +
                "Input:\n" +
                "Time: " + time + " minutes\n" +
                "Dietary restriction: " + restriction + "\n" +
                "Ingredients: " + ingredient;
    }


    /**
     * Sends a request to the OpenAI API to get a meal suggestion.
     */
    String suggestMeal(String restriction) {
        String response = "";
        String prompt = promrtMaker(restriction);


        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", this.getApiKey()); // Replace with your actual API key
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Build JSON request
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("model", "gpt-3.5-turbo");

            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.put(userMessage);

            jsonRequest.put("messages", messages);

            // Send request
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(jsonRequest.toString().getBytes("UTF-8"));
                outputStream.flush();

                int responseCode = connection.getResponseCode();
                BufferedReader reader;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }

                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                reader.close();
                response = responseBuilder.toString();

                // Parse JSON
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("choices")) {
                    JSONArray choices = jsonResponse.getJSONArray("choices");
                    String mealSuggestion = choices.getJSONObject(0).getJSONObject("message").getString("content").trim();
                    return mealSuggestion;
                } else {
                    return "No suitable meal found or missing 'choices' in response.";
                }

            } catch (IOException e) {
                return "Error during request: " + e.getMessage();
            }

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
    public String sugestmealBasedONIngredients(int time, String restrictions, String ingredient) {
    String prompt = promptAll(time, restrictions, ingredient);
    String response = "";
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", this.getApiKey()); // Replace with your actual API key
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Build JSON request
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("model", "gpt-3.5-turbo");

            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.put(userMessage);

            jsonRequest.put("messages", messages);

            // Send request
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(jsonRequest.toString().getBytes("UTF-8"));
                outputStream.flush();

                int responseCode = connection.getResponseCode();
                BufferedReader reader;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }

                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                reader.close();
                response = responseBuilder.toString();

                // Parse JSON
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("choices")) {
                    JSONArray choices = jsonResponse.getJSONArray("choices");
                    String mealSuggestion = choices.getJSONObject(0).getJSONObject("message").getString("content").trim();
                    return mealSuggestion;
                } else {
                    return "No suitable meal found or missing 'choices' in response.";
                }

            } catch (IOException e) {
                return "Error during request: " + e.getMessage();
            }

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }

    }

    private String getApiKey() {
        return apiKey;
    }

    String suggetMealTime(int time ){
        String prompt = promptExactMealTime(time);
        String response = "";
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", this.getApiKey()); // Replace with your actual API key
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Build JSON request
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("model", "gpt-3.5-turbo");

            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.put(userMessage);

            jsonRequest.put("messages", messages);

            // Send request
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(jsonRequest.toString().getBytes("UTF-8"));
                outputStream.flush();

                int responseCode = connection.getResponseCode();
                BufferedReader reader;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }

                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                reader.close();
                response = responseBuilder.toString();

                // Parse JSON
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("choices")) {
                    JSONArray choices = jsonResponse.getJSONArray("choices");
                    String mealSuggestion = choices.getJSONObject(0).getJSONObject("message").getString("content").trim();
                    return mealSuggestion;
                } else {
                    return "No suitable meal found or missing 'choices' in response.";
                }

            } catch (IOException e) {
                return "Error during request: " + e.getMessage();
            }

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

}


