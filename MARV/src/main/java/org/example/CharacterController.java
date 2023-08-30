package org.example;

import org.example.CharacterModel;
import org.example.CharacterView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CharacterController {
    private CharacterView view;

    public CharacterController(CharacterView view) {
        this.view = view;
    }

    public void fetchCharacterInfo() throws IOException {
        String characterId = "1009610"; // ID del personaje (Spider-Man)
        String url = "https://gateway.marvel.com:443/v1/public/characters/" + characterId;
        url += "?ts=1&apikey=1109f02c30d7b6c01af4be6dab4bae7e&hash=698723e83defc2daa590087c373760ae";

        // Realizar la solicitud a la API
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Crear un objeto JSONObject a partir de la respuesta JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Acceder a los datos del personaje
            JSONObject characterData = jsonResponse.getJSONObject("data").getJSONArray("results").getJSONObject(0);
            String characterName = characterData.getString("name");
            String characterDescription = characterData.getString("description");
            String characterThumbnailURL = characterData.getJSONObject("thumbnail").getString("path") + "." + characterData.getJSONObject("thumbnail").getString("extension");
            JSONArray comicsArray = characterData.getJSONObject("comics").getJSONArray("items");

            List<String> comicsTitle = new ArrayList<>();
            for (int i = 0; i < comicsArray.length(); i++) {
                JSONObject comicData = comicsArray.getJSONObject(i);
                comicsTitle.add(comicData.getString("name"));
            }

            CharacterModel characterModel = new CharacterModel();
            characterModel.setName(characterName);
            characterModel.setDescription(characterDescription);
            characterModel.setThumbnailURL(characterThumbnailURL);
            characterModel.setComicsTitle(comicsTitle);

            view.displayCharacter(characterModel);
        } else {
            view.displayError(responseCode);
        }

        connection.disconnect();
    }
}