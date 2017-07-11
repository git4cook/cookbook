package com.groupd.cookbook.business;


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
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.presentation.searchResult;

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
        setContentView(R.layout.search);
        AR = new AccessRecipe();
        try {
            Rlist = (ArrayList<Recipe>) AR.getRecipeList();
        } catch (myException e) {
            e.printStackTrace();
        }
        /*if (rlt != null) {
            Messages.fatalError(this, rlt);
        }*/
        if (Rlist != null) {

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
    }





    public void buttonOpenOnClick(View v) throws myException {
        EditText editName = (EditText)findViewById(R.id.recyTitle);
        String inputStr = editName.getText().toString().trim();
        ArrayList<String> result = AR.search(inputStr);

                if(result.size()>0) {
                    Intent c;
                    c = new Intent(this, searchResult.class);
                    c.putStringArrayListExtra("searchResult", result);
                   search.this.startActivity(c);
                }
                else{
                    AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
                    alertdialogbuilder.setMessage("We can't find anything.");

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
