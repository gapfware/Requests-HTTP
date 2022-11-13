package org.example.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import okhttp3.Request;

import java.io.IOException;

public class ServerPOSTRequest extends ServerResponse {
    public ServerPOSTRequest(String headers, String body, String statusCode) {
        super(headers, body, statusCode);
    }

    public ServerPOSTRequest() {
    }

    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) throws IOException {

        ServerPOSTRequest obj = new ServerPOSTRequest();
        obj.sendPOSTRequest("");
    }

    public void sendPOSTRequest(String urlparameter) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(urlparameter)
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();

        setStatusCodeResponse(String.valueOf(response.code()));
        System.out.println("Status Code: " + getStatusCodeResponse()); //Obtengo e imprimo el status code.

        Headers responseHeaders = response.headers();
        setHeadersResponse(String.valueOf(responseHeaders));
        System.out.println(getHeadersResponse());


        System.out.println("-------------Body Response---------------------");
        String responseBody = response.body().string();
        gson.toJson(responseBody);
        setBodyResponse(responseBody);

        System.out.println(getBodyResponse());

    }
}
