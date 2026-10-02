package com.rradjabli.econanalysisengine.httptest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpTest {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting...");

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://fred.stlouisfed.org/graph/fredgraph.csv?id=MKTGDPAZA646NWDB"
                ))
                .GET()
                .build();

        System.out.println("Sending request...");

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response received!");
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}