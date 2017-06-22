package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import java.util.ArrayList;

public class showRecipe extends AppCompatActivity {
    private AccessRecipe AR;
    private ArrayList<Recipe> recipeList;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_show);
        Bundle b = getIntent().getExtras();
        String rcyTitle = b.getString("recipeName");
        AccessRecipe RAC = new AccessRecipe();
        Recipe vRcy = RAC.getR(rcyTitle);
        title = vRcy.getName();
        TextView rti = (TextView) findViewById(R.id.vTitle);
        TextView rta = (TextView) findViewById(R.id.vTags);
        TextView rct = (TextView) findViewById(R.id.vDes);
        rti.setText(/*"Title: \n" + */vRcy.getName());
        rta.setText("Tags: \n" + vRcy.getTags());
        rct.setText("How to cook: \n" + vRcy.getDirection());

    }

     public void buttonDeleteOnClick (View v) {
         AccessRecipe RAC = new AccessRecipe();
         Recipe vRcy = RAC.getR(title);
         String result = RAC.deleteRecipe(vRcy);
         if(result == null) {
             Intent i;
             i = new Intent(this, MainActivity.class);
             showRecipe.this.startActivity(i);
         }
         else {
             Messages.warning(this, result);
         }
         }
     }


