package tn.esprit.applicationgui.utils;


import com.squareup.okhttp.*;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
public class SendSMS {
    public void Send(String num,String messageValue){

        String to="+216"+num;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        JSONArray destinations = new JSONArray();
        JSONObject destination = new JSONObject();
        destination.put("to", to);
        destinations.put(destination);
        message.put("destinations", destinations);
        message.put("from", "KoolArt");
        message.put("text", messageValue);
        messages.put(message);
        requestBody.put("messages", messages);

// Create the RequestBody using the constructed JSON object
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
         Request request = new Request.Builder()
                .url("https://2v3k3w.api.infobip.com/sms/2/text/advanced")
                .method("POST", body)
                .addHeader("Authorization", "App a32a7f55d4ee97151dc48aad7c73a727-9d872f55-d8d0-4ead-a18d-ced22d91a092")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("PROBLEM");
        }
    }
}
