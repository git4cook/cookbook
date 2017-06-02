package com.groupd.cookbook.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

<<<<<<< HEAD:app/src/main/java/com/groupd/cookbook/MainActivity.java
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
=======
import com.groupd.cookbook.R;

public class MainActivity extends AppCompatActivity {
>>>>>>> 37d28dae4df6f9e78448154843e5e89adc3ee134:app/src/main/java/com/groupd/cookbook/application/MainActivity.java

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_and_search_menu);

        Button addButton = (Button)findViewById(R.id.addButtonID);
        addButton.setOnClickListener(this);
        //Button addButton = (Button)findViewById(R.

    }//dont delete this activity


<<<<<<< HEAD:app/src/main/java/com/groupd/cookbook/MainActivity.java
    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, addNewRecipe.class);
        startActivity(i);
    }


=======
>>>>>>> 37d28dae4df6f9e78448154843e5e89adc3ee134:app/src/main/java/com/groupd/cookbook/application/MainActivity.java
}
