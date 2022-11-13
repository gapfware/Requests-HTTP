package org.example.entities;

public abstract  class ServerResponse {
    private String headersResponse;
    private String bodyResponse;
    private String statusCodeResponse;

    public ServerResponse(String headers, String body, String statusCode) {
        this.headersResponse = headers;
        this.bodyResponse = body;
        this.statusCodeResponse = statusCode;
    }

    public ServerResponse() {

    }

    public String getHeadersResponse() {
        return headersResponse;
    }

    public void setHeadersResponse(String headersResponse) {
        this.headersResponse = headersResponse;
    }

    public String getBodyResponse() {
        return bodyResponse;
    }

    public void setBodyResponse(String bodyResponse) {
        this.bodyResponse = bodyResponse;
    }

    public String getStatusCodeResponse() {
        return statusCodeResponse;
    }

    public void setStatusCodeResponse(String statusCodeResponse) {
        this.statusCodeResponse = statusCodeResponse;
    }
}
