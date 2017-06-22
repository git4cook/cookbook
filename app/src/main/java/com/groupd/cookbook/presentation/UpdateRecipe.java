package com.groupd.cookbook.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.groupd.cookbook.R;
import com.groupd.cookbook.objects.Recipe;

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
        EditText editName =(EditText)findViewById(R.id.editName);
        EditText editTags = (EditText)findViewById(R.id.editTags) ;
        EditText editDec  = (EditText)findViewById(R.id.editDec) ;
        editName.setText(title);
        editTags.setText(tags);
        editDec.setText(des);

        editName = (EditText)findViewById(R.id.editName);
        editTags = (EditText)findViewById(R.id.editTags);
        editDec  = (EditText)findViewById(R.id.editDec);

        Recipe newRecipe = new Recipe (editName.getText().toString(),editTags.getText().toString(),editDec.getText().toString());

    }




}
