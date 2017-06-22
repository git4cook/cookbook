package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;


public class showRecipe extends AppCompatActivity {

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

        Intent update;
        update = new Intent(this, UpdateRecipe.class);
        b.putString("recipeName",title);
        b.putString("tags",vRcy.getTags());
        b.putString("des",vRcy.getDirection());
        update.putExtras(b);
        showRecipe.this.startActivity(update);
    }

     public void buttonDeleteOnClick (View v) {
         AccessRecipe RAC = new AccessRecipe();
         Recipe vRcy = RAC.getR(title);
         String result = RAC.deleteRecipe(vRcy);
         if(result == null) {
             Intent delete;
             delete = new Intent(this, MainActivity.class);
             showRecipe.this.startActivity(delete);
         }
         else {
             Messages.warning(this, result);
         }
         }

    public void buttonUpdateOnClick(View v){
        Intent update;
        update = new Intent(this, UpdateRecipe.class);
        showRecipe.this.startActivity(update);

    }

     }


