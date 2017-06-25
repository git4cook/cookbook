package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.groupd.cookbook.R;

// By Glenn

public class addNewRecipe extends AppCompatActivity implements View.OnClickListener {

    private String[] inputArray = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipie);
        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        EditText titleQuery = (EditText)findViewById(R.id.addTitleID);
        EditText tagsQuery = (EditText)findViewById(R.id.addTagsID);
        EditText stepsQuery = (EditText)findViewById(R.id.addStepsID);

        String titleInput = titleQuery.getText().toString();
        String tagsInput = tagsQuery.getText().toString();
        String stepsInput = stepsQuery.getText().toString();

        // Note decided to pass a array instead of making a new record
        // Reason: Keep GUI and Logic separate, GUI is independent of Recipe class - Glenn


        System.out.println("The title is: " + titleInput);
        Log.i("info", "title = " + titleInput);
        Log.i("info", "tags = " + tagsInput);
        Log.i("info", "steps = " + stepsInput);

        createInputArray(titleInput,tagsInput,stepsInput);

        return;
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
