package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
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
            JSONArray nameArray = sandwichDetailJSON.getJSONArray("name");
            JSONObject nameObject = nameArray.getJSONObject(0);
            String mainName = nameObject.getString("mainName");
            List<String> aka = nameObject.getString("alsoKnownAs");

            String placeOfOrigin = sandwichDetailJSON.getString("placeOfOrigin");
            String description = sandwichDetailJSON.getString("description");
            String image = sandwichDetailJSON.getString("image");
            List<String> ingredients = sandwichDetailJSON.getString("ingredients");

            return Sandwich sandwich = new Sandwich(mainName, aka, placeOfOrigin, description, image, ingredients);
        }catch (JSONException e) {
            return null;
        }
    }
}
