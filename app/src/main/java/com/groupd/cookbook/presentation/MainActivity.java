package com.groupd.cookbook.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.application.Main;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    //
    private AccessRecipe AR;
    private ArrayList<Recipe> Rlist;
    private ArrayAdapter<Recipe> RADP;
    private int RecyPosy = -1;

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
                    //TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(Rlist.get(position).getName());
                    //text2.setText(Rlist.get(position).getTags());

                    return view;
                }
            };
            final ListView listView = (ListView) findViewById(R.id.listRecipe);
            listView.setAdapter(RADP);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button) findViewById(R.id.buttonRecipeUpdate);
                    Button deleteButton = (Button) findViewById(R.id.buttonRecipeDelete);

                    if (position == RecyPosy) {
                        listView.setItemChecked(position, false);
                        updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        RecyPosy = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        updateButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        RecyPosy = position;
                        selectRecipeAtPosition(position);
                    }
                }
            });

            final EditText editRecipeName = (EditText) findViewById(R.id.editRecipeName);
            final Button buttonOpen = (Button) findViewById(R.id.buttonOpenRecipe);
            editRecipeName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    buttonOpen.setEnabled(editRecipeName.getText().toString().length() > 0);
                }
            });
        }
    }

           public void selectRecipeAtPosition(int position) {
            Recipe selected = RADP.getItem(position);
               EditText editName = (EditText)findViewById(R.id.editRecipeName);

               editName.setText(selected.getName());
           }

            public void buttonOpenOnClick(View v) {
                EditText editName = (EditText)findViewById(R.id.editRecipeName);
                String recipeName = editName.getText().toString();

                Intent reIntent = new Intent(MainActivity.this,openRecipeActivity.class);
                Bundle b = new Bundle();
                b.putString("recipeName",recipeName);
                reIntent.putExtras(b);
                MainActivity.this.startActivity(reIntent);

            }

    public void buttonRecipeCreateOnClick(View v) {
        Recipe recipe = createRecipeFromEditText();
        String result;

        result = validateRecipeData(recipe, true);
        if (result == null) {
            result = AR.insertRecipe(recipe);
            if (result == null) {
                AR.getRecipe(Rlist);
                RADP.notifyDataSetChanged();
                int pos = Rlist.indexOf(recipe);
                if (pos >= 0) {
                    ListView listView = (ListView)findViewById(R.id.listRecipe);
                    listView.setSelection(pos);
                }
            } else {
                Messages.fatalError(this, result);
            }
        } else {
            Messages.warning(this, result);
        }
    }
    public void buttonRecipeUpdateOnClick(View v) {
        Recipe recipe = createRecipeFromEditText();
        String result;

        result = validateRecipeData(recipe, true);
        if (result == null) {
            result = AR.insertRecipe(recipe);
            if (result == null) {
                AR.getRecipe(Rlist);
                RADP.notifyDataSetChanged();
                int pos = Rlist.indexOf(recipe);
                if (pos >= 0) {
                    ListView listView = (ListView)findViewById(R.id.listRecipe);
                    listView.setSelection(pos);
                }
            } else {
                Messages.fatalError(this, result);
            }
        } else {
            Messages.warning(this, result);
        }
    }
    public void buttonRecipeDeleteOnClick(View v) {
        Recipe recipe = createRecipeFromEditText();
        String result;

        result = AR.deleteRecipe(recipe);
        if (result == null) {
            int pos = Rlist.indexOf(recipe);
            if (pos >= 0) {
                ListView listView = (ListView) findViewById(R.id.listRecipe);
                listView.setSelection(pos);
            }
            AR.getRecipe(Rlist);
            RADP.notifyDataSetChanged();
        } else {
            Messages.warning(this, result);
        }
    }
    private Recipe createRecipeFromEditText() {
        EditText editName = (EditText)findViewById(R.id.editRecipeName);

        Recipe recipe = new Recipe(editName.getText().toString(), editName.getText().toString());

        return recipe;
    }

    private String validateRecipeData(Recipe recipe, boolean isNewRecipe) {
        if (recipe.getName().length() == 0) {
            return "Recipe Name required";
        }

        if (isNewRecipe && AR.getRandom(recipe.getName()) != null) {
            return "Course ID " + recipe.getName() + " already exists.";
        }

        return null;
    }


        }

















        /*Button addButton = (Button)findViewById(R.id.addButtonID);
        addButton.setOnClickListener(this);*/
        //Button addButton = (Button)findViewById(R.







