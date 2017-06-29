package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.tag;

import java.util.List;


public class showRecipe extends AppCompatActivity {

    private String title;
    private Recipe vRcy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_show);
        Bundle b = getIntent().getExtras();
        String rcyTitle = b.getString("recipeName");
        AccessRecipe RAC = new AccessRecipe();
        try {
            vRcy = RAC.getRecipe(rcyTitle);
        } catch (myException e) {
            e.printStackTrace();
        }
        title = vRcy.getName();

        TextView rti = (TextView) findViewById(R.id.vTitle);
        TextView rta = (TextView) findViewById(R.id.vTags);
        TextView rct = (TextView) findViewById(R.id.vDes);
        rti.setText(/*"Title: \n" + */title);
        String tags =vRcy.getRecipeTags().get(0).getTagsName();
        for(int i = 1;i<vRcy.getRecipeTags().size();i++){
            tags =tags+","+vRcy.getRecipeTags().get(i).getTagsName();
        }
        rta.setText("Tags: \n" + tags);
        rct.setText("How to cook: \n" +  vRcy.getDirection());

    }

     public void buttonDeleteOnClick (View v) throws myException {
         AccessRecipe RAC = new AccessRecipe();
         RAC.deleteRecipe(title);
             Intent delete;
             delete = new Intent(this, MainActivity.class);
             showRecipe.this.startActivity(delete);

     }

    public void buttonUpdateOnClick(View v){

        Intent update = new Intent(this, update.class);
        Bundle a =  new Bundle();
        a.putString("recipeName",vRcy.getName());
        a.putString("tags",tagsInString(vRcy.getRecipeTags()));
        a.putString("des",vRcy.getDirection());
        update.putExtras(a);
        showRecipe.this.startActivity(update);


    }
    private String tagsInString(List<tag> tags){
        String result = tags.get(0).getTagsName();
        for(int i = 1;i<tags.size();i++){
            result = result+","+tags.get(i).getTagsName();
        }
        return result;
    }
     }


