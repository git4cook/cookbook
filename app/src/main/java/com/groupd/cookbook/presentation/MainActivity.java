package com.groupd.cookbook.presentation;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    final int ADD_REQUEST_CODE = 1; //request code for adding recipes's startActivityForResult
    private final int INPUT_TITLE_INDEX = 0;
    private final int INPUT_TAGS_INDEX = 1;
    private final int INPUT_STEPS_INDEX = 2;

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


                    RecyPosy = position;
                    selectRecipeAtPosition(position);

                    Intent reIntent = new Intent(MainActivity.this,showRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("recipeName",name);
                    reIntent.putExtras(b);
                    MainActivity.this.startActivity(reIntent);

                }
            });
        }
    }

    public void selectRecipeAtPosition(int position) {
        Recipe selected = RADP.getItem(position);
        EditText editName = (EditText)findViewById(R.id.recyTitle);
        name = selected.getName();
    }


    public void buttonOpenOnClick(View v) {

        // Added by Glenn b/c added new button for add
        switch (v.getId())
        {
            case R.id.opRcy:
                // moved Tao's code here
                Intent reIntent = new Intent(MainActivity.this, search.class);
                MainActivity.this.startActivity(reIntent);
                break;

            case R.id.addButton:
                Intent i;
                i = new Intent(MainActivity.this, addNewRecipe.class);
                //startActivity(i);
                // when request code >0 go to onActivityResult when activity exists.
               startActivityForResult(i, ADD_REQUEST_CODE);

                break;

        }
    }

    /*
    PURPOSE:
                Runs after the calling startActivityForResult is done
                Performs action based on the request code.
                Retrieves input from layout as a string array and adds recipe to GUI and Logic AL

    PARAMETERS:
                requestCode that identifies which activity we exited from and on.
                resultCode created by exited activity to say there is data to return.
                data, holds a string array of input that will be extracted.
    AUTHOR: Glenn
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ADD_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                //Uri addURI = data.getData();
                System.out.println("we get here");


                String[] returnedArray = data.getStringArrayExtra("RECIPE_DATA");

                Recipe addedRecipe = new Recipe(returnedArray[INPUT_TITLE_INDEX],
                        returnedArray[INPUT_STEPS_INDEX],returnedArray[INPUT_TAGS_INDEX]);

                AR.insertRecipe(addedRecipe);
                RADP.add(addedRecipe);

            }
        }
    }


}
