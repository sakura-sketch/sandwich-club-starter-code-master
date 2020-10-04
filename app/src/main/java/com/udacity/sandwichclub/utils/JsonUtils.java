package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichDetails = new JSONObject(json);
            Sandwich sandwichData = new Sandwich();
            List<String> alsoKnownAsList = new ArrayList<String>();
            List<String> ingredientsList = new ArrayList<String>();
           /* {"name":{"mainName":"Ham and cheese
                sandwich","alsoKnownAs":[]},"placeOfOrigin":"","description":"A ham and cheese
                sandwich is a common type of sandwich. It is made by putting cheese and sliced ham
                between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables
                like lettuce, tomato, onion or pickle slices can also be included. Various kinds of
                mustard and mayonnaise are also
                common.","image":"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG","ingredients":["Sliced
                bread","Cheese","Ham"]}*/
           JSONObject nameData = sandwichDetails.getJSONObject("name");
           sandwichData.setMainName(nameData.getString("mainName"));
           JSONArray alsoKnownAs = nameData.getJSONArray("alsoKnownAs");
            for(int i=0;i<alsoKnownAs.length();i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            JSONArray ingredientsArray = sandwichDetails.getJSONArray("ingredients");
            for(int i=0;i<ingredientsArray.length();i++){
                ingredientsList.add(ingredientsArray.getString(i));
            }
           sandwichData.setAlsoKnownAs(alsoKnownAsList);
           sandwichData.setImage(sandwichDetails.getString("image"));
           sandwichData.setPlaceOfOrigin(sandwichDetails.getString("placeOfOrigin"));
           sandwichData.setIngredients(ingredientsList);
           sandwichData.setDescription(sandwichDetails.getString("description"));
           return sandwichData;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
