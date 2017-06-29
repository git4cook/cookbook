package com.groupd.cookbook.presentation;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.tag;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {


    private AccessRecipe AR;
    private ArrayList<Recipe> Rlist;
    private ArrayAdapter<Recipe> RADP;
    private String name = "";
    final int ADD_REQUEST_CODE = 1; //request code for adding recipes's startActivityForResult
    private final int INPUT_TITLE_INDEX = 0;
    private final int INPUT_TAGS_INDEX = 1;
    private final int INPUT_STEPS_INDEX = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list /*list_and_search_menu*/);
        copyDatabaseToDevice();
        try {
            Main.startUp();
        } catch (myException e) {
            Messages.warning(this, "1"+e.getMessage());
        }
        AR = new AccessRecipe();
        try {
            Rlist = AR.getRecipeList();
        } catch (myException e) {
            Messages.warning(this, "2"+e.getMessage());
        }
        if (Rlist != null) {

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
    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            Main.shutDown();
        } catch (myException e) {
            Messages.warning(this, e.getMessage());
        }
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
       // EditText editName = (EditText)findViewById(R.id.recyTitle);
        name = selected.getName();
    }

    /*
    UPDATE: Code here for method "onActivityResult" was deleted by someone.
    Note that startActivityForResult(i, ADD_REQUEST_CODE) is looking for onActivityResult.
    Leaving these comments here as a marker to get this resolved.
    Remember to comment if you are editing someone's code else its confusing - Glenn

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

    /*
    PURPOSE:
                Replaced buttons with menu icons, saves space, material design, cleaner.
                Gist of old button code: https://gist.github.com/Glenn-Jaya/1b2c886baf76be5be0f8d3737a43cf07
                Note: for 2 methods below.
                Creates the menu (onCreateOptionsMenu)at the top
                and handles presses of icons (onOptionsItemSelected).

    PARAMETERS:
                Menu and menuItem object.
    AUTHOR: Glenn (note I also did main_menu.xml and imported icons :)
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_add) {
            Intent i;
            i = new Intent(this, addNewRecipe.class);
            //startActivity(i);
            // when request code >0 go to onActivityResult when activity exists.
            startActivityForResult(i, ADD_REQUEST_CODE);
            return true;
        }

        if (id == R.id.action_search) {
            Intent reIntent = new Intent(MainActivity.this, search.class);
            MainActivity.this.startActivity(reIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
