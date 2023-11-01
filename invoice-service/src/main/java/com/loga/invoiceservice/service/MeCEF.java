package com.loga.invoiceservice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loga.invoiceservice.app.factory.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeCEF implements IMeCEF {

    private final Gson GSON = new Gson().newBuilder()
            .setPrettyPrinting()
            .create();

    /*
     * PRODUCTION https://sygmef.impots.bj/emcf/api/info
     * TEST https://developper.impots.bj/sygmef-emcf/api/info
     * */
    private final String INFO_URL = "https://developper.impots.bj/sygmef-emcf/api/info";

    /*
    * PRODUCTION https://sygmef.impots.bj/emcf/api/invoice
    * TEST https://developper.impots.bj/sygmef-emcf/api/invoice
    * */
    private final String INVOICE_URL = "https://developper.impots.bj/sygmef-emcf/api/invoice";

    final String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjEyMDE3MDA1Nzg3MDd8VFMwMTAwMzg4NCIsInJvbGUiOiJUYXhwYXllciIsIm5iZiI6MTY5Njk1NzYwMiwiZXhwIjoxNzM1NTk5NjAwLCJpYXQiOjE2OTY5NTc2MDIsImlzcyI6ImltcG90cy5iaiIsImF1ZCI6ImltcG90cy5iaiJ9.yY4iMs-402Sacc2CGpWt0ko_x6QKt1gKm-EVwhn1ER0";

    @Override
    public InfoResponseDto info() throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INFO_URL+"/status"))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("GET", HttpRequest.BodyPublishers.ofString(GSON.toJson(null), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), InfoResponseDto.class);
    }

    @Override
    public TaxGroupDto taxes() throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INFO_URL+"/taxGroups"))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("GET", HttpRequest.BodyPublishers.ofString(GSON.toJson(null), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), TaxGroupDto.class);
    }

    @Override
    public List<InvoiceTypeDto> invoices() throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INFO_URL+"/invoiceTypes"))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("GET", HttpRequest.BodyPublishers.ofString(GSON.toJson(null), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), new TypeToken<ArrayList<InvoiceTypeDto>>(){}.getType());
    }

    @Override
    public List<PaymentTypeDto> payments() throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INFO_URL+"/paymentTypes"))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("GET", HttpRequest.BodyPublishers.ofString(GSON.toJson(null), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), new TypeToken<ArrayList<PaymentTypeDto>>(){}.getType());
    }

    @Override
    public StatusResponseDto status() throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INVOICE_URL+"/"))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("GET", HttpRequest.BodyPublishers.ofString(GSON.toJson(null), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), StatusResponseDto.class);
    }

    @Override
    public InvoiceResponseDataDto invoicing(InvoiceRequestDataDto data) throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INVOICE_URL+"/"))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("POST", HttpRequest.BodyPublishers.ofString(GSON.toJson(data), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), InvoiceResponseDataDto.class);
    }

    @Override
    public SecurityElementsDto confirmation(String uid, String action) throws IOException, InterruptedException {
        /*
         * action = {confirm | cancel}
         * */

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INVOICE_URL+"/"+uid+"/"+action))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("PUT", HttpRequest.BodyPublishers.ofString(GSON.toJson(null), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), SecurityElementsDto.class);
    }

    @Override
    public InvoiceDetailsDto details(String uid) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(INVOICE_URL+"/"+uid))
                .setHeader("Content-Type","application/json; charset=UTF-8")
                .setHeader("Accept","application/json")
                .setHeader("Authorization",token)
                .method("GET", HttpRequest.BodyPublishers.ofString(GSON.toJson(null), StandardCharsets.UTF_8))
                .timeout(Duration.ofMillis(5000))
                .build();

        HttpResponse<?> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(httpResponse.body().toString(), InvoiceDetailsDto.class);
    }
}
