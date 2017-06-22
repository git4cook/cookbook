package com.groupd.cookbook.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.groupd.cookbook.R;

/**
 * Created by siyu on 2017/6/22.
 */

public class UpdateRecipe extends AppCompatActivity {
    private String title ;
    private String tags;
    private String des;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        Bundle b = getIntent().getExtras();
        title = b.getString("recipeName");
        tags = b.getString("tags");
        des = b.getString("des");


    }




}
