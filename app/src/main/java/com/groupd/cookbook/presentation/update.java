package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.tag;

import java.util.ArrayList;


public class update extends AppCompatActivity {
    private String title;
    private String tags;
    private String des;
    //private String tagString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        Bundle b = getIntent().getExtras();

        title = b.getString("recipeName");
        tags = b.getString("tags");
        des = b.getString("des");
        TextView editName = (TextView) findViewById(R.id.viewName);
        TextView tag = (TextView) findViewById(R.id.tagudt);
        EditText editDec = (EditText) findViewById(R.id.editDec);
        editName.setText(title);
        tag.setText(tags);
        editDec.setText(des);
        /*Button tag1 = (Button)findViewById(R.id.b1);
        Button tag2 = (Button)findViewById(R.id.b2);
        Button tag3 = (Button)findViewById(R.id.b3);
        Button tag4 = (Button)findViewById(R.id.b4);
        Button tag5 = (Button)findViewById(R.id.b5);*/
    }

    public void buttonConfirmOnClick(View v) throws myException {
        AccessRecipe AR = new AccessRecipe();

        TextView editName = (TextView) findViewById(R.id.viewName);
        //TextView edit = (TextView) findViewById(R.id.viewName);

        EditText editDec = (EditText) findViewById(R.id.editDec);
        String temp[] = tags.split(",");
        ArrayList<tag> tagsInObj = new ArrayList<>();
        for(int i = 0;i<temp.length;i++){
            tagsInObj.add(new tag(temp[i]));
        }
        Recipe newRecipe = new Recipe(editName.getText().toString(), editDec.getText().toString(), tagsInObj);
        String result = validateRecipeData(newRecipe);
        Recipe rlt;
        if (result == null) {
            try {
                AR.updateRecipe(newRecipe);
                Intent c;
                c = new Intent(this, MainActivity.class);
                update.this.startActivity(c);
            }catch (myException e) {
                Messages.warning(this, e.getMessage());
            }
      }

    }

   private String validateRecipeData(Recipe recipe) {

        if (recipe.getName().length() == 0) {
            return "RecipeName requierd";
        }
       // if (recipe.getRecipeTags().length() == 0) {
         //   return "Tags requierd";
        //}

        return null;
    }


    public void onClick11(View v) {
        TextView tag = (TextView) findViewById(R.id.tagudt);
        if (tags.equalsIgnoreCase("")) {
            tags += "vegi";
            tag.setText(tags);
        } else {
            if(!deleteDucplicate("vegi")) {
                tags += ",vegi";
            }
            tag.setText(tags);
        }
    }

    public void onClick22(View v) {
        TextView tag = (TextView) findViewById(R.id.tagudt);
        if (tags.equalsIgnoreCase("")) {
            tags += "meat";
            tag.setText(tags);
        } else {
            if(!deleteDucplicate("meat")) {
                tags += ",meat";
            }
            tag.setText(tags);
        }
    }

    public void onClick33(View v) {
        TextView tag = (TextView) findViewById(R.id.tagudt);
        if (tags.equalsIgnoreCase("")) {
            tags += "breakfast";
            tag.setText(tags);
        } else {
            if(!deleteDucplicate("breakfast")) {
                tags += ",breakfast";
            }
            tag.setText(tags);
        }
    }

    public void onClick44(View v) {
        TextView tag = (TextView) findViewById(R.id.tagudt);
        if (tags.equalsIgnoreCase("")) {
            tags += "lunch";
            tag.setText(tags);
        } else {
            if(!deleteDucplicate("lunch")) {
                tags += ",lunch";
            }
            tag.setText(tags);
        }
    }

    public void onClick55(View v) {
        TextView tag = (TextView) findViewById(R.id.tagudt);
        if (tags.equalsIgnoreCase("")) {
            tags += "dinner";
            tag.setText(tags);
        } else {
            if(!deleteDucplicate("dinner")) {
                tags += ",dinner";
            }
            tag.setText(tags);
        }
    }

    public boolean deleteDucplicate(String delete) {
        String delete1 = "," + delete;
        String delete2 = delete + ",";
        String result;
        if ((tags.indexOf(delete1)) >= 0){
            result = tags.replace(delete1, "");
            tags = result;
            return true;
        }
        if ((tags.indexOf(delete2)) >= 0) {
            result = tags.replace(delete2, "");
            tags=  result;
            return true;
        }
        if ((tags.indexOf(delete)) >= 0) {
            result = tags.replace(delete, "");
            tags=  result;
            return true;
        }

        return false;
    }


}
