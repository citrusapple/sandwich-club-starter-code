package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {


        TextView mAKA = findViewById(R.id.also_known_tv);
        TextView mOrigin = findViewById(R.id.origin_tv);
        TextView mDescription = findViewById(R.id.description_tv);
        TextView mIngredients = findViewById(R.id.ingredients_tv);

        // here I am using a try catch for the also known as part of the JSON.  I call the list and change it to string
        //normally I would do .join with java 8 but it seems to break things here due to the rest of the code being older
        // so I did a bit of hard coding and just did a toString with replace all.
        try{
            List alsoKnownAs = sandwich.getAlsoKnownAs();
            String AKAString = alsoKnownAs.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t");
            mAKA.setText(AKAString);
        } catch(Exception e){
            String empty = new String();
            mAKA.setText(empty);
        }

        try {
            String placeOfOrigin = sandwich.getPlaceOfOrigin();
            mOrigin.setText(placeOfOrigin);
        }catch (Exception e){
            String empty = new String();
            mOrigin.setText(empty);
        }

        try {
            String description = sandwich.getDescription();
            mDescription.setText(description);
        } catch (Exception e){
            String empty = new String();
            mDescription.setText(empty);
        }


        try {
            List ingredients = sandwich.getIngredients();
            String ingredientsString = ingredients.toString().replaceAll("[", "").replaceAll("]", "");
            mIngredients.setText(ingredientsString);
        }catch (Exception e){
            String empty = new String();
            mIngredients.setText(empty);
        }
    }
}
