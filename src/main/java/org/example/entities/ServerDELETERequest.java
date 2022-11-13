package org.example.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import okhttp3.Request;

import java.io.IOException;

public class ServerDELETERequest extends ServerResponse{
    public ServerDELETERequest(String headers, String body, String statusCode) {
        super(headers, body, statusCode);
    }

    public ServerDELETERequest() {
    }
    private final OkHttpClient httpClient = new OkHttpClient();


    public static void main(String[] args) throws IOException {

        ServerDELETERequest obj = new ServerDELETERequest();
        obj.sendDELETERequest("");
    }

    public void sendDELETERequest(String urlparameter) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);
        okhttp3.Request request = new Request.Builder()
                .url(urlparameter)
                .method("DELETE", body)
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
