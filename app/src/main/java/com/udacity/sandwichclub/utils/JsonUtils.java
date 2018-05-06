package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich thisSandwich = null;
        try {
             /*
        <item>{\"name\":{\"mainName\":\"Ham and cheese sandwich\",\"alsoKnownAs\":[]},\"placeOfOrigin\":\"\",\"description\":\"A ham and cheese
        sandwich is a common type of sandwich. It is made by putting cheese and sliced ham
        between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables
        like lettuce, tomato, onion or pickle slices can also be included. Various kinds of
        mustard and mayonnaise are also
        common.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",\"ingredients\":[\"Sliced
        bread\",\"Cheese\",\"Ham\"]}
        */

            JSONObject sandwichDetailJSON = new JSONObject(json);
            JSONObject nameObject = sandwichDetailJSON.getJSONObject("name");
            String mainName = nameObject.getString("mainName");
            JSONArray alsoKnownAs = nameObject.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAsList = new ArrayList<String>();

            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            String placeOfOrigin = sandwichDetailJSON.getString("placeOfOrigin");
            String description = sandwichDetailJSON.getString("description");
            String image = sandwichDetailJSON.getString("image");
            JSONArray ingredientsArray = sandwichDetailJSON.getJSONArray("ingredients");
            ArrayList<String> ingredientsList = new ArrayList<String>();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }
            thisSandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
            return thisSandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thisSandwich;
    }
}
