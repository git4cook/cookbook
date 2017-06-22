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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_show);
        Bundle b = getIntent().getExtras();
        String rcyTitle = b.getString("recipeName");
        AccessRecipe RAC = new AccessRecipe();
        Recipe vRcy = RAC.getR(rcyTitle);
        TextView rti = (TextView) findViewById(R.id.vTitle);
        TextView rta = (TextView) findViewById(R.id.vTags);
        TextView rct = (TextView) findViewById(R.id.vDes);
        rti.setText(/*"Title: \n" + */vRcy.getName());
        rta.setText("Tags: \n" + vRcy.getTags());
        rct.setText("How to cook: \n" + vRcy.getDirection());

    }

     public void buttonDeleteOnClick (View v) {
         TextView rti = (TextView) findViewById(R.id.vTitle);
         String reName = rti.getText().toString().trim();
         Recipe delete = AR.getR(reName);
         String result = AR.deleteRecipe(delete);
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


