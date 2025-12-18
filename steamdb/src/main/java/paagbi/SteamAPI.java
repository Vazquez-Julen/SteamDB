package paagbi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonObject appData = root.getAsJsonObject(String.valueOf(appId));

            if (!appData.get("success").getAsBoolean()) {
                System.out.println("Errorea: Steam Store API ez du arrakastarik itzuli.");
                return null;
            }

            JsonObject data = appData.getAsJsonObject("data");

            String name = data.get("name").getAsString();
            String image = data.get("header_image").getAsString();

            String genre = "Besteak";
            if (data.has("genres")) {
                JsonArray genres = data.getAsJsonArray("genres");
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
}
