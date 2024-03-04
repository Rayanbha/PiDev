package Controllers.Forum;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ChatGPT {
    private static final String Mykey = "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"; // Remplacez par votre clé API

    public static String getResponse(String userMessage) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\":\"" + userMessage + "\",\"model\":\"text-davinci-003\",\"max_tokens\":100}");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + Mykey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println("Raw response from OpenAI API: " + responseBody); // Debugging statement
            return extractResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur lors de la communication avec le serveur.";
        }
    }
    private static String extractResponse(String responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            if (jsonObject.has("choices")) {
                JSONArray choicesArray = jsonObject.getJSONArray("choices");
                if (choicesArray.length() > 0) {
                    JSONObject choiceObject = choicesArray.getJSONObject(0);
                    return choiceObject.getString("text");
                } else {
                    return "Aucun choix trouvé dans la réponse.";
                }
            } else {
                return "Le champ 'choices' n'a pas été trouvé dans la réponse.";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "Erreur lors de l'extraction de la réponse JSON: " + e.getMessage();
        }
    }
}

