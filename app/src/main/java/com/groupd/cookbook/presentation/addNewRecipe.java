package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;

// By Glenn, Tao Wu

public class addNewRecipe extends AppCompatActivity implements View.OnClickListener {

    private String[] inputArray = new String[3];
    private String tagString="";
    //TextView tag = (TextView) findViewById(R.id.tagsS);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipie);
        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        /*Button tag1 = (Button)findViewById(R.id.tag1);
        //tag1.setOnClickListener(this);
        Button tag2 = (Button)findViewById(R.id.tag2);
        //tag2.setOnClickListener(this);
        Button tag3 = (Button)findViewById(R.id.tag3);
        //tag3.setOnClickListener(this);
        Button tag4 = (Button)findViewById(R.id.tag4);
        //tag4.setOnClickListener(this);
        Button tag5 = (Button)findViewById(R.id.tag5);
        //tag5.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View v) {

        EditText titleQuery = (EditText)findViewById(R.id.addTitleID);
        //EditText tagsQuery = (EditText)findViewById(R.id.addTagsID);
        EditText stepsQuery = (EditText)findViewById(R.id.addStepsID);

        String titleInput = titleQuery.getText().toString();
        String tagsInput = tagString;//.getText().toString();
        String stepsInput = stepsQuery.getText().toString();

        // Note decided to pass a array instead of making a new record
        // Reason: Keep GUI and Logic separate, GUI is independent of Recipe class - Glenn






        Recipe newRecipe = new Recipe(titleInput,stepsInput,tagsInput);
        String result = validateRecipeData(newRecipe);

        if (result == null) {
            System.out.println("The title is: " + titleInput);
            Log.i("info", "title = " + titleInput);
            Log.i("info", "tags = " + tagsInput);
            Log.i("info", "steps = " + stepsInput);

            createInputArray(titleInput,tagsInput,stepsInput);
        } else {
            Messages.warning(this, result);
        }
    }

    public void onClick1(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "vegi";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("vegi")) {
                tagString += ",vegi";

            }
            tag.setText(tagString);
        }
    }

    public void onClick2(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "meat";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("meat")) {
                tagString += ",meat";

            }
            tag.setText(tagString);
        }
    }

    public void onClick3(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "breakfast";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("breakfast")) {
                tagString += ",breakfast";

            }
            tag.setText(tagString);
        }
    }

    public void onClick4(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "lunch";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("lunch")) {
                tagString += ",lunch";

            }
            tag.setText(tagString);
        }
    }

    public void onClick5(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "dinner";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("dinner")) {
                tagString += ",dinner";

            }
            tag.setText(tagString);
        }
    }

    public boolean deleteDucplicate(String delete) {
        String delete1 = "," + delete;
        String delete2 = delete + ",";
        String result;
        if ((tagString.indexOf(delete1)) >= 0){
            result = tagString.replace(delete1, "");
            tagString = result;
            return true;
        }
        if ((tagString.indexOf(delete2)) >= 0) {
            result = tagString.replace(delete2, "");
            tagString=  result;
            return true;
        }
        if ((tagString.indexOf(delete)) >= 0) {
            result = tagString.replace(delete, "");
            tagString=  result;
            return true;
        }

        return false;
    }

    private String validateRecipeData(Recipe recipe) {

        if (recipe.getName().length() == 0) {
            return "RecipeName requierd";
        }
        if (recipe.getTags().length() == 0) {
            return "Tags requierd";
        }

        return null;
    }



    /*
    PURPOSE: Create array of input parameters and intent to pass array to main menu,
                then close activity
    AUTHOR: Glenn
     */
    public void createInputArray(String titleInput, String tagsInput, String stepsInput)
    {
        inputArray[0] = titleInput;
        inputArray[1] = tagsInput;
        inputArray[2] = stepsInput;

        Intent createdIntent = new Intent();
        createdIntent.putExtra("RECIPE_DATA",inputArray);
        setResult(RESULT_OK, createdIntent);
        finish();
    }

}
