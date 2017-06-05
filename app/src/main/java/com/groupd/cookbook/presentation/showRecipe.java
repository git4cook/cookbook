package com.groupd.cookbook.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.presentation.MainActivity;

import java.util.ArrayList;

public class showRecipe extends AppCompatActivity /*implements View.OnClickListener*/{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_show);
        Bundle b = getIntent().getExtras();
        String rcyTitle = b.getString("recipeName");
        AccessRecipe RAC = new AccessRecipe();
        Recipe vRcy = RAC.getR(rcyTitle);
        TextView rti = (TextView) findViewById(R.id.vTitle);
        TextView rta = (TextView) findViewById(R.id.vTags);
        TextView rct = (TextView) findViewById(R.id.vDes);
        rti.setText(vRcy.getName());
        rti.setText(vRcy.getTags());
        rti.setText(vRcy.getDirection());

    }

    public void buttonOpenOnClick(View v) {


        Intent home = new Intent(showRecipe.this, MainActivity.class);
        showRecipe.this.startActivities(home);

    }

}
