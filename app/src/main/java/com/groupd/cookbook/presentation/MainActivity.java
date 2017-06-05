package com.groupd.cookbook.presentation;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
//
import com.groupd.cookbook.R;
import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {


    private AccessRecipe AR;
    private ArrayList<Recipe> Rlist;
    private ArrayAdapter<Recipe> RADP;
    private int RecyPosy = -1;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list /*list_and_search_menu*/);
       Main.startUp();
        AR = new AccessRecipe();
        Rlist = new ArrayList<Recipe>();
        String rlt = AR.getRecipe(Rlist);
        if (rlt != null) {
            Messages.fatalError(this, rlt);
        } else {

            RADP = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_1, android.R.id.text1, Rlist) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);


                    text1.setText(Rlist.get(position).getName());


                    return view;
                }
            };
            final ListView listView = (ListView) findViewById(R.id.RecyList);
            listView.setAdapter(RADP);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Button updateButton = (Button)findViewById(R.id.buttonCourseUpdate);
                    //Button deleteButton = (Button)findViewById(R.id.buttonCourseDelete);


                    RecyPosy = position;
                    selectRecipeAtPosition(position);

                    Intent reIntent = new Intent(MainActivity.this,showRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("recipeName",name);
                    reIntent.putExtras(b);
                    MainActivity.this.startActivity(reIntent);

                }
            });


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

    public void selectRecipeAtPosition(int position) {
        Recipe selected = RADP.getItem(position);
        EditText editName = (EditText)findViewById(R.id.recyTitle);
        name = selected.getName();
        editName.setText(selected.getName());
    }

    public void buttonOpenOnClick(View v) {
        EditText editName = (EditText)findViewById(R.id.recyTitle);
        String recipeName = editName.getText().toString();
        if(AR.search(recipeName)) {
            Intent reIntent = new Intent(MainActivity.this, showRecipe.class);
            Bundle b = new Bundle();
            b.putString("recipeName", recipeName);
            reIntent.putExtras(b);
            MainActivity.this.startActivity(reIntent);
        }
        else{
            AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
            alertdialogbuilder.setMessage("We couldn't find anything.");

            ;
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
