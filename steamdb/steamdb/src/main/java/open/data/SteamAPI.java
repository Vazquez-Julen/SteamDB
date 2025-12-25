package open.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import open.model.GameInfo;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SteamAPI {

    private static final String PLAYER_COUNT_URL =
            "https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v1/?appid=";
    private static final String STORE_URL =
            "https://store.steampowered.com/api/appdetails?appids=";

    public static int getCurrentPlayers(int appId) {
        try {
            URL url = new URL(PLAYER_COUNT_URL + appId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject json = JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject("response");

            return json.get("player_count").getAsInt();

        } catch (Exception e) {
            System.out.println("Errorea Steam API deitzen: " + e.getMessage());
            return -1;
        }
    }

    public static GameInfo getGameInfo(int appId) {
        try {
            URL url = new URL(STORE_URL + appId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject json = JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject(String.valueOf(appId))
                    .getAsJsonObject("data");

            String name = json.get("name").getAsString();
            String image = json.get("header_image").getAsString();

            String genre = "Besteak"; // default
            if (json.has("genres")) {
                JsonArray genres = json.getAsJsonArray("genres");
                if (genres.size() > 0) {
                    String original = genres.get(0).getAsJsonObject().get("description").getAsString();
                    switch (original) {
                        case "Action": genre = "Ekintza"; break;
                        case "Strategy": genre = "Estrategia"; break;
                        case "RPG": genre = "RPG"; break;
                        default: genre = "Besteak";
                    }
                }
            }

            return new GameInfo(name, image, genre);

        } catch (Exception e) {
            System.out.println("Errorea Steam Store API: " + e.getMessage());
            return null;
        }
    }

    public static String getGamePrice(int appId) {
        try {
            // Agregar parámetro cc=ES para forzar precios en euros
            URL url = new URL(STORE_URL + appId + "&cc=ES");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject json = JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject(String.valueOf(appId));

            if (!json.get("success").getAsBoolean()) {
                return "Ez dago eskuragarri";
            }

            JsonObject data = json.getAsJsonObject("data");
            
            // Verificar si es gratis
            if (data.has("is_free") && data.get("is_free").getAsBoolean()) {
                return "Doan";
            }

            // Obtener precio
            if (data.has("price_overview")) {
                JsonObject priceOverview = data.getAsJsonObject("price_overview");
                int finalPrice = priceOverview.get("final").getAsInt();
                
                // Convertir centavos a euros
                double price = finalPrice / 100.0;
                
                // Siempre mostrar en euros
                return String.format("%.2f €", price);
            }

            return "Ez dago eskuragarri";

        } catch (Exception e) {
            System.out.println("Errorea prezioak lortzean: " + e.getMessage());
            return "Ez dago eskuragarri";
        }
    }
}
