/*

* */
package com.groupd.cookbook.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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
import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
//
public class searchResult extends AppCompatActivity {
    private AccessRecipe AR;
    private List<Recipe> Rlist;
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
        setContentView(R.layout.search_result /*list_and_search_menu*/);
        AR = new AccessRecipe();
        Rlist = AR.getSearchResult();
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

                    Intent reIntent = new Intent(searchResult.this,showRecipe.class);
                    Bundle b = new Bundle();
                    b.putString("recipeName",name);
                    reIntent.putExtras(b);
                    searchResult.this.startActivity(reIntent);

                }
            });

    }
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }
    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
    public void selectRecipeAtPosition(int position) {
        Recipe selected = RADP.getItem(position);
        EditText editName = (EditText)findViewById(R.id.recyTitle);
        name = selected.getName();
    }


    public void buttonOpenOnClick(View v) {

        switch (v.getId())
        {
            case R.id.opRcy:
                Intent reIntent = new Intent(searchResult.this, search.class);
                searchResult.this.startActivity(reIntent);
                break;

            case R.id.Back:
                Intent i = new Intent(searchResult.this, MainActivity.class);
                searchResult.this.startActivity(i);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ADD_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                System.out.println("we get here");


                String[] returnedArray = data.getStringArrayExtra("RECIPE_DATA");
                String temp[] = returnedArray[INPUT_TAGS_INDEX].split(",");
                ArrayList<tag> tagsInObj = new ArrayList<tag>();
                for(int i = 0;i<temp.length;i++){
                    tagsInObj.add(new tag(temp[i]));
                }
                Recipe addedRecipe = new Recipe(returnedArray[INPUT_TITLE_INDEX],
                        returnedArray[INPUT_STEPS_INDEX],tagsInObj);

                AR.insertRecipe(addedRecipe);
                RADP.add(addedRecipe);

            }
        }
    }

}
