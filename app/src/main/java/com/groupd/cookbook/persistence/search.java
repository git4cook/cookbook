package com.groupd.cookbook.persistence;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//
import com.groupd.cookbook.R;
import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.presentation.Messages;
import com.groupd.cookbook.presentation.showRecipe;

import java.util.ArrayList;


public class search extends AppCompatActivity {


    private AccessRecipe AR;
    private ArrayList<Recipe> Rlist;
    private ArrayAdapter<Recipe> RADP;
    private int RecyPosy = -1;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search );
        Main.startUp();
        AR = new AccessRecipe();
        Rlist = new ArrayList<Recipe>();
        String rlt = AR.getRecipe(Rlist);
        if (rlt != null) {
            Messages.fatalError(this, rlt);
        }


            final EditText rn = (EditText) findViewById(R.id.recyTitle);
            final Button buttonOpen = (Button) findViewById(R.id.opRcy);
            rn.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    buttonOpen.setEnabled(rn.getText().toString().length() > 0);
                }
            });

        }





    public void buttonOpenOnClick(View v) {
        EditText editName = (EditText)findViewById(R.id.recyTitle);
        String recipeName = editName.getText().toString().trim();
        if(AR.search(recipeName)) {
            Intent reIntent = new Intent(search.this, showRecipe.class);
            Bundle b = new Bundle();
            b.putString("recipeName", recipeName);
            reIntent.putExtras(b);
            search.this.startActivity(reIntent);
        }
        else{
            AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
            alertdialogbuilder.setMessage("We can't find this recipe.");

            alertdialogbuilder.setPositiveButton("ok",click1);

            AlertDialog alertdialog1=alertdialogbuilder.create();

            alertdialog1.show();
        }
    }
    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface arg0,int arg1)
        {
            arg0.cancel();
        }
    };

}
