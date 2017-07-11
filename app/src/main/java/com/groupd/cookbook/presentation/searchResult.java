/*

* */
package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.business.search;

import java.util.ArrayList;
import java.util.List;
//
public class searchResult extends AppCompatActivity {
    private AccessRecipe AR;
    private List<String> Rlist;
    private ArrayAdapter<String> RADP;
    private int RecyPosy = -1;
    private String name = "";
    final int ADD_REQUEST_CODE = 1; //request code for adding recipes's startActivityForResult
    private final int INPUT_TITLE_INDEX = 0;
    private final int INPUT_TAGS_INDEX = 1;
    private final int INPUT_STEPS_INDEX = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result /*list_and_search_menu*/);
        Intent i = getIntent();
        ArrayList<String> searchResult= i.getStringArrayListExtra("searchResult");
        AR = new AccessRecipe();
        Rlist = searchResult;
       RADP = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, android.R.id.text1, Rlist) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);


                    text1.setText(Rlist.get(position));


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

                    Intent reIntent = new Intent(searchResult.this,showRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("recipeName",name);
                    reIntent.putExtras(b);
                    searchResult.this.startActivity(reIntent);

                }
            });

    }
    public void selectRecipeAtPosition(int position) {
        String selected = RADP.getItem(position);
        EditText editName = (EditText)findViewById(R.id.recyTitle);
        name = selected;
    }


    public void buttonOpenOnClick(View v) {
        Intent i = new Intent(searchResult.this, MainActivity.class);
        searchResult.this.startActivity(i);
    }




}
