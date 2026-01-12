package org.abik.steam_inventory_parcer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class SteamInventoryInfo {
    private static final int APP_ID = 730; // cs2

    public static JSONObject getInventoryItems(String steamId, int contextId) {
        String urlString = "https://steamcommunity.com/inventory/" + steamId + "/" + APP_ID + "/" + contextId;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            if (jsonResponse.has("descriptions")) {
                return jsonResponse;
            } else {
                System.out.println("Error while retrieving items: " +
                        jsonResponse.optString("message", "Unknown error"));
                return null;
            }
        } catch (Exception e) {
            System.out.println("HTTP request error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter SteamID64 of the Steam profile: ");
        String steamId = scanner.nextLine();
        scanner.close();

        JSONObject inventory = getInventoryItems(steamId, 2);
        if (inventory != null) {
            JSONArray assets = inventory.getJSONArray("assets");
            JSONArray descriptions = inventory.getJSONArray("descriptions");
            System.out.println("Inventory items:");
            System.out.println("---");

            for (int i = 0; i < assets.length(); i++) {
                JSONObject asset = assets.getJSONObject(i);
                String classid = asset.getString("classid");

                for (int j = 0; j < descriptions.length(); j++) {
                    JSONObject description = descriptions.getJSONObject(j);
                    if (description.getString("classid").equals(classid)) {
                        System.out.println("Name: " + description.getString("name"));
                        System.out.println("Type: " + description.getString("type"));
                        System.out.println("Marketable: " +
                                (description.getInt("marketable") == 1 ? "Yes" : "No"));
                        System.out.println("---");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Failed to retrieve inventory items.");
        }
    }
}
