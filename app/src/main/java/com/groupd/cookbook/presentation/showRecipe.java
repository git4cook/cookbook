package com.groupd.cookbook.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;

public class showRecipe extends AppCompatActivity {

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



}
