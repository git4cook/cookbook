package com.groupd.cookbook.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.tag;

import java.util.ArrayList;
import java.util.List;

// By Glenn, Tao Wu

public class addNewRecipe extends AppCompatActivity implements View.OnClickListener {
    private AccessRecipe AR;
    private String[] inputArray = new String[3];
    private String tagString="";
    //TextView tag = (TextView) findViewById(R.id.tagsS);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipie);
        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        AR = new AccessRecipe();

        /*Button tag1 = (Button)findViewById(R.id.tag1);
        //tag1.setOnClickListener(this);
        Button tag2 = (Button)findViewById(R.id.tag2);
        //tag2.setOnClickListener(this);
        Button tag3 = (Button)findViewById(R.id.tag3);
        //tag3.setOnClickListener(this);
        Button tag4 = (Button)findViewById(R.id.tag4);
        //tag4.setOnClickListener(this);
        Button tag5 = (Button)findViewById(R.id.tag5);
        //tag5.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View v) {

        EditText titleQuery = (EditText)findViewById(R.id.addTitleID);
        //EditText tagsQuery = (EditText)findViewById(R.id.addTagsID);
        EditText stepsQuery = (EditText)findViewById(R.id.addStepsID);

        String titleInput = titleQuery.getText().toString();
        String tagsInput = tagString;//.getText().toString();
        String stepsInput = stepsQuery.getText().toString();

        // Note decided to pass a array instead of making a new record
        // Reason: Keep GUI and Logic separate, GUI is independent of Recipe class - Glenn

        try {
            if(AR.findRecipe(titleInput)) {
                AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
                alertdialogbuilder.setMessage("This recipe already exist, please use a different name.");

                alertdialogbuilder.setPositiveButton("ok",click1);

                AlertDialog alertdialog1=alertdialogbuilder.create();

                alertdialog1.show();
            }
            //make sure there si no duplicate

            else {


                Recipe newRecipe = new Recipe(titleInput, stepsInput, tagsInObj(tagsInput));
                String result = validateRecipeData(newRecipe);

                if (result == null) {
                    try {
                        AR.insertRecipe(newRecipe);
                        Intent i = new Intent(addNewRecipe.this, MainActivity.class);
                        addNewRecipe.this.startActivity(i);
                    }
                    catch (myException e) {
                        Messages.warning(this, e.getMessage());
                    }
                    //addRecipe is now called here instead of MainActivity
                } else {
                    Messages.warning(this, result);
                }
            }
        } catch (myException e) {
            Messages.warning(this, e.getMessage());
        }
    }

    public void onClick1(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "vegi";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("vegi")) {
                tagString += ",vegi";

            }
            tag.setText(tagString);
        }
    }

    public void onClick2(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "meat";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("meat")) {
                tagString += ",meat";

            }
            tag.setText(tagString);
        }
    }

    public void onClick3(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "breakfast";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("breakfast")) {
                tagString += ",breakfast";

            }
            tag.setText(tagString);
        }
    }

    public void onClick4(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "lunch";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("lunch")) {
                tagString += ",lunch";

            }
            tag.setText(tagString);
        }
    }

    public void onClick5(View v) {
        TextView tag = (TextView) findViewById(R.id.tagsS);
        if (tagString.equalsIgnoreCase("")) {
            tagString += "dinner";
            tag.setText(tagString);
        } else {
            if(!deleteDucplicate("dinner")) {
                tagString += ",dinner";

            }
            tag.setText(tagString);
        }
    }

    public boolean deleteDucplicate(String delete) {
        String delete1 = "," + delete;
        String delete2 = delete + ",";
        String result;
        if ((tagString.indexOf(delete1)) >= 0){
            result = tagString.replace(delete1, "");
            tagString = result;
            return true;
        }
        if ((tagString.indexOf(delete2)) >= 0) {
            result = tagString.replace(delete2, "");
            tagString=  result;
            return true;
        }
        if ((tagString.indexOf(delete)) >= 0) {
            result = tagString.replace(delete, "");
            tagString=  result;
            return true;
        }

        return false;
    }

    private String validateRecipeData(Recipe recipe) {

        if (recipe.getName().length() == 0) {
            return "RecipeName requierd";
        }
        if (recipe.getRecipeTags().size() == 0) {
            return "Tags requierd";
        }

        return null;
    }



    /*
    PURPOSE: Create array of input parameters and intent to pass array to main menu,
                then close activity
    AUTHOR: Glenn
     */
 private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface arg0,int arg1)
        {
            arg0.cancel();
        }
    };


    private List<tag >tagsInObj(String tags){
        String temp[] = tags.split(",");
        ArrayList<tag> tagsInObj = new ArrayList<tag>();
        for(int i = 0;i<temp.length;i++){
            tagsInObj.add(new tag(temp[i]));
        }
        return tagsInObj;
    }

}