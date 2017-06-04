package com.groupd.cookbook.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.groupd.cookbook.R;

public class addNewRecipe extends AppCompatActivity implements View.OnClickListener {

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

        System.out.println("The title is: " + titleInput);
        Log.i("info", "title = " + titleInput);
        Log.i("info", "tags = " + tagsInput);
        Log.i("info", "steps = " + stepsInput);
    }

}
