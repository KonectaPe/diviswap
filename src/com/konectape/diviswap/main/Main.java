package com.konectape.diviswap.main;

import com.konectape.diviswap.api.Api;
import com.konectape.diviswap.models.Coin;
import com.konectape.diviswap.utils.Utils;

import java.util.Scanner;

import static com.konectape.diviswap.constants.Codes.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username = System.getProperty("user.name");
        int option = 0;
        Api api = new Api();
        Coin coin = new Coin(api.getCoinsCode());
        String menu = """
                  \s
                *******************************************************
                Bienvenido %s al conversor de monedas :]
                                 \s
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Historial de conversiones
                8) Salir
                               \s
                Elige una opción válida:
                *******************************************************
                """;

        try {
            while (option != 8) {
                System.out.printf(menu, username);
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        Utils.filterCoins(coin.getConversionRates(), USD, ARS);
                        break;
                    case 2:
                        Utils.filterCoins(coin.getConversionRates(), ARS, USD);
                        break;
                    case 3:
                        Utils.filterCoins(coin.getConversionRates(), USD, BRL);
                        break;
                    case 4:
                        Utils.filterCoins(coin.getConversionRates(), BRL, USD);
                        break;
                    case 5:
                        Utils.filterCoins(coin.getConversionRates(), USD, COP);
                        break;
                    case 6:
                        Utils.filterCoins(coin.getConversionRates(), COP, USD);
                        break;
                    case 7:
                        Utils.historyExchange();
                        break;
                    case 8:
                        System.out.println("Saliendo del programa gracias por utilizar nuestros servicios.");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
