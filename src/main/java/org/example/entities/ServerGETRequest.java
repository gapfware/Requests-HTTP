package org.example.entities;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import okhttp3.Request;

public class ServerGETRequest extends ServerResponse{

    private final OkHttpClient httpClient = new OkHttpClient();

    public ServerGETRequest(String headers, String body, String statusCode) {
        super(headers, body, statusCode);
    }
    public ServerGETRequest() {
    }

    public static void main(String[] args) throws IOException {

        ServerGETRequest obj = new ServerGETRequest();
        obj.sendGETRequest("");
    }

    public void sendGETRequest(String urlparameter) throws IOException {


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        okhttp3.Request request = new Request.Builder()
                .url(urlparameter)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            //Obtener El codigo de estado de la response
            setStatusCodeResponse(String.valueOf(response.code()));
            System.out.println("Status Code: " + getStatusCodeResponse());

            // Obtener los headers de la response
            Headers responseHeaders = response.headers();
            setHeadersResponse(String.valueOf(responseHeaders));

            System.out.println("--------------Response headers------------------");
            System.out.println(getHeadersResponse());

            // Obtener el body de la response
            System.out.println("-------------Body Response---------------------");
            String bodyResponse = response.body().string();
            gson.toJson(bodyResponse);
            setBodyResponse(bodyResponse);
            System.out.println(getBodyResponse());

        } catch (Exception e) {
            setBodyResponse(null);
            System.out.println(e);
        }


    }
}
