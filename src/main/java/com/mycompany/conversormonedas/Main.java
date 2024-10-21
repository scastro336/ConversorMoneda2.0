package com.mycompany.conversormonedas;

// *
// * @author Sixto Castro
// *

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class Main {

    // Lista para guardar el historial de conversiones
    private static List<String> historialConversiones = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            // Mostrar el menú principal
            System.out.println("\nBienvenido al Conversor de Divisas");
            System.out.println("Elige una opcion:");
            System.out.println("1. Conversor de Monedas");
            System.out.println("2. Historial de Conversiones");
            System.out.println("3. Salir");

            System.out.print("\nOpcion a elegir: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer del scanner

            switch (opcion) {
                case 1:
                    // Opción 1: Conversor de monedas
                    mostrarDivisasPopulares(scanner);
                    break;
                case 2:
                    // Opción 2: Mostrar historial de conversiones
                    mostrarHistorial();
                    break;
                case 3:
                    // Opción 3: Salir
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no valida. Intentalo de nuevo.");
            }
        }

        scanner.close();
    }

    // Metodo para mostrar las divisas populares y proceder con la conversión
    private static void mostrarDivisasPopulares(Scanner scanner) {
        System.out.println("\nLas divisas mas populares son las siguientes:");
        System.out.println("1. Dolar (Estadounidense): USD");
        System.out.println("2. Euro: EUR");
        System.out.println("3. Peso Mexicano: MXN");
        System.out.println("4. Dolar Canadiense: CAD");
        System.out.println("5. Peso Argentino: ARS");
        System.out.println("6. Real Brasileno: BRL");
        System.out.println("7. Nuevo Sol Peruano: PEN");
        System.out.println("8. Peso Venezolano: VES");
        System.out.println("9. Si no has encontrado tu moneda, la puedes ver en la siguiente lista completa:");

        System.out.print("\nOpcion a elegir: ");
        int opcionMoneda = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        // Aquí definimos las divisas populares
        String monedaOrigen = "";
        switch (opcionMoneda) {
            case 1:
                monedaOrigen = "USD";
                break;
            case 2:
                monedaOrigen = "EUR";
                break;
            case 3:
                monedaOrigen = "MXN";
                break;
            case 4:
                monedaOrigen = "CAD";
                break;
            case 5:
                monedaOrigen = "ARS";
                break;
            case 6:
                monedaOrigen = "BRL";
                break;
            case 7:
                monedaOrigen = "PEN";
                break;
            case 8:
                monedaOrigen = "VES";
                break;
            case 9:
                mostrarListaCompleta(scanner);
                return;
            default:
                System.out.println("Opción no valida.");
                return;
        }

        realizarConversion(scanner, monedaOrigen);
    }

    // Metodo para mostrar la lista completa de monedas
    private static void mostrarListaCompleta(Scanner scanner) {
        String apiURL = "https://v6.exchangerate-api.com/v6/dc347d77e4552b0784e97b51/latest/USD";

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convertir respuesta JSON a objeto
            String jsonResponse = response.body();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            // Mostrar la lista de monedas disponibles
            System.out.println("\nLista completa de Monedas Disponibles:");
            for (Map.Entry<String, ?> entry : rates.entrySet()) {
                String monedaCodigo = entry.getKey();
                System.out.println(monedaCodigo);
            }

            // Pedir al usuario que seleccione una moneda de origen
            System.out.println("\nINGRESE TIPO DE MONEDA DE ORIGEN: ");
            String monedaOrigen = scanner.nextLine().toUpperCase();

            realizarConversion(scanner, monedaOrigen);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar la lista completa de monedas
    private static void mostrarListaCompletaDestino(Scanner scanner) {
        String apiURL = "https://v6.exchangerate-api.com/v6/dc347d77e4552b0784e97b51/latest/USD";

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convertir respuesta JSON a objeto
            String jsonResponse = response.body();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            // Mostrar la lista de monedas disponibles
            System.out.println("\nLista completa de Monedas Disponibles:");
            for (Map.Entry<String, ?> entry : rates.entrySet()) {
                String monedaCodigo = entry.getKey();
                System.out.println(monedaCodigo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para realizar la conversión de monedas
    // Método para realizar la conversión de monedas
    private static void realizarConversion(Scanner scanner, String monedaOrigen) {
        String apiURL = "https://v6.exchangerate-api.com/v6/dc347d77e4552b0784e97b51/latest/" + monedaOrigen;

        try {
            // Solicitar monto a convertir
            System.out.println("INGRESE MONTO DE ORIGEN: ");
            double montoOrigen = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer del scanner

            System.out.println("INGRESE TIPO DE MONEDA DESTINO: ");

            // Mostrar las opciones de moneda de destino
            String monedaDestino = mostrarDivisasPopularesDestino(scanner);

            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convertir respuesta JSON a objeto
            String jsonResponse = response.body();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            // Verificar si la API devolvió un error
            if (jsonObject.has("error-type")) {
                System.out.println("Error: " + jsonObject.get("error-type").getAsString());
                return;
            }

            // Acceder a las tasas de cambio desde el JSON
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            // Verificar si la moneda de destino existe
            if (rates.has(monedaDestino)) {
                double tasaCambio = rates.get(monedaDestino).getAsDouble();
                double montoConvertido = montoOrigen * tasaCambio;

                // Mostrar el resultado con dos decimales
                System.out.println(String.format("%.2f %s EQUIVALEN A %.2f %s", montoOrigen, monedaOrigen, montoConvertido, monedaDestino));

                // Guardar la conversión en el historial formateada con dos decimales
                historialConversiones.add(String.format("%.2f %s = %.2f %s", montoOrigen, monedaOrigen, montoConvertido, monedaDestino));
            } else {
                System.out.println("Moneda de destino no encontrada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para mostrar las divisas populares para la moneda de destino y devolver la opción elegida
    private static String mostrarDivisasPopularesDestino(Scanner scanner) {
        System.out.println("Las divisas más populares son las siguientes:");
        System.out.println("1. Dolar (Estadounidense): USD");
        System.out.println("2. Euro: EUR");
        System.out.println("3. Peso Mexicano: MXN");
        System.out.println("4. Dolar Canadiense: CAD");
        System.out.println("5. Peso Argentino: ARS");
        System.out.println("6. Real Brasileno: BRL");
        System.out.println("7. Nuevo Sol Peruano: PEN");
        System.out.println("8. Peso Venezolano: VES");
        System.out.println("9. Si no has encontrado tu moneda, la puedes ver en la siguiente lista completa:");

        System.out.print("\nOpción a elegir: ");
        int opcionMoneda = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        String monedaDestino = "";
        switch (opcionMoneda) {
            case 1:
                monedaDestino = "USD";
                break;
            case 2:
                monedaDestino = "EUR";
                break;
            case 3:
                monedaDestino = "MXN";
                break;
            case 4:
                monedaDestino = "CAD";
                break;
            case 5:
                monedaDestino = "ARS";
                break;
            case 6:
                monedaDestino = "BRL";
                break;
            case 7:
                monedaDestino = "PEN";
                break;
            case 8:
                monedaDestino = "VES";
                break;
            case 9:
                // Mostrar la lista completa de monedas y permitir al usuario ingresar la moneda de destino
                mostrarListaCompletaDestino(scanner);
                System.out.println("INGRESE TIPO DE MONEDA DE DESTINO: ");
                monedaDestino = scanner.nextLine().toUpperCase();
                return monedaDestino;
            default:
                System.out.println("Opción no válida.");
                return null; // Retornamos null en caso de una opción no válida
        }

        return monedaDestino; // Retornamos la moneda seleccionada
    }

    // Metodo para mostrar el historial de conversiones
    private static void mostrarHistorial() {
        if (historialConversiones.isEmpty()) {
            System.out.println("No hay conversiones realizadas aun.");
        } else {
            System.out.println("\nHistorial de Conversiones:");
            for (String conversion : historialConversiones) {
                System.out.println(conversion);
            }
        }
    }
}