package com.groupd.cookbook.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/{

    //
    private AccessRecipe AR;
    private ArrayList<Recipe> Rlist;
    private ArrayAdapter<Recipe> RADP;
    private int selectedCoursePosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list /*list_and_search_menu*/);

        /*Button addButton = (Button)findViewById(R.id.addButtonID);
        addButton.setOnClickListener(this);*/
        //Button addButton = (Button)findViewById(R.

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, addNewRecipe.class);
        startActivity(i);
    }

}
