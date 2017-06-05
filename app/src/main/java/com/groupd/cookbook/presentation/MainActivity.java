package com.groupd.cookbook.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.groupd.cookbook.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list /*list_and_search_menu*/);

        Button addButton = (Button)findViewById(R.id.addButtonID);
        addButton.setOnClickListener(this);
        //Button addButton = (Button)findViewById(R.

    }//dont delete this activity


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, addNewRecipe.class);
        startActivity(i);
    }

}
