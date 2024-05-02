package com.konectape.diviswap.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.konectape.diviswap.constants.Codes;
import com.konectape.diviswap.models.CoinExchange;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.konectape.diviswap.constants.Codes.USD;

public interface Utils {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    List<String> history = new ArrayList<>();

    static CoinExchange request() {
        String uri = "https://v6.exchangerate-api.com/v6/e0009cc8e9e57ba6ae81ae28/latest/USD";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            client.close();
            return gson.fromJson(json, CoinExchange.class);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    static void filterCoins(Object coin, Codes base, Codes target) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el valor que desea convertir:");
        double amount = sc.nextDouble();
        double currency = 0.0;
        try {
            JsonObject obj = gson.toJsonTree(coin).getAsJsonObject();
            if (base == USD) {
                currency = obj.get(String.valueOf(target)).getAsDouble() * amount;
            } else {
                currency = amount / obj.get(String.valueOf(base)).getAsDouble();
            }

            String exchange = "El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]".formatted(amount, base, currency, target);
            history.add(exchange);
            System.out.println(exchange);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    static void historyExchange() {
        System.out.printf("Ultimas %d conversiones \n", history.size());
        for (String s : history) {
            System.out.println(s);
        }
    }
}
