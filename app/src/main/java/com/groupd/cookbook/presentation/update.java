package com.groupd.cookbook.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;



public class update extends AppCompatActivity {
    private String title ;
    private String tags;
    private String des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        Bundle b = getIntent().getExtras();

        title = b.getString("recipeName");
        tags = b.getString("tags");
        des = b.getString("des");
        TextView editName =(TextView) findViewById(R.id.viewName);
        EditText editTags = (EditText)findViewById(R.id.editTags) ;
        EditText editDec  = (EditText)findViewById(R.id.editDec) ;
        editName.setText(title);
        editTags.setText(tags);
        editDec.setText(des);

    }
    public void buttonConfirmOnClick (View v) {
        AccessRecipe AR = new AccessRecipe();

        TextView editName =(TextView) findViewById(R.id.viewName);
        EditText editTags = (EditText)findViewById(R.id.editTags);
        EditText editDec  = (EditText)findViewById(R.id.editDec);

        Recipe newRecipe = new Recipe (editName.getText().toString(),editDec.getText().toString(),editTags.getText().toString());
        String result = validateRecipeData(newRecipe);

       if(result == null) {
            result = AR.updateRecipe(newRecipe);
            if (result == null) {
                Intent c;
                c = new Intent(this, MainActivity.class);
                update.this.startActivity(c);
            } else {
                Messages.fatalError(this, result);
            }
       } else{
          Messages.warning(this,result);
        }

    }

private String validateRecipeData(Recipe recipe){

    if(recipe.getName().length()==0){
        return "RecipeName requierd";
    }
    if(recipe.getTags().length()==0){
        return "Tags requierd";
    }

    return null;
}


}
