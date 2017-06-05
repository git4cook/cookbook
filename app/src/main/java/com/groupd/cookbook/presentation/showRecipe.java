package com.groupd.cookbook.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.groupd.cookbook.R;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.business.AccessRecipe;


public class showRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_show);
    }
}
