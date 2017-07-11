package com.groupd.cookbook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.groupd.cookbook.R;
import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.objects.myException;

public class showScroll extends AppCompatActivity {
    private LayoutInflater inflater;
    private PageView mPageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_scroll);
        Bundle b = getIntent().getExtras();
        String input = b.getString("steps");
        String[] steps = input.split("\n");

        inflater = LayoutInflater.from(this);
        mPageView = (PageView) findViewById(R.id.pageview);
        addSteps(steps);

    }
    private void addSteps(String []steps){
        for(int i = 1;i<=steps.length;i++){
            RelativeLayout relativeLayout = new RelativeLayout(this);

            RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);

            TextView text1 = new TextView(this);
            text1.setText("Step "+i);
            text1.setTextSize(70);
            TextView text2 = new TextView(this);
            text2.setText(steps[i-1]);
            text2.setTextSize(40);
            Button button1 = new Button(this);
            button1.setText("Back");
            final Intent back;
            back = new Intent(this, MainActivity.class);
            button1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v){

                    showScroll.this.startActivity(back);

                }
            });
            AddTextLayout(text1, RelativeLayout.CENTER_HORIZONTAL);
            AddTextLayout(text2, RelativeLayout.CENTER_IN_PARENT);
            AddButtonLayout(button1, RelativeLayout.ALIGN_PARENT_RIGHT);
            relativeLayout.addView(text1);
            relativeLayout.addView(text2);
            relativeLayout.addView(button1);

            mPageView.addPage(relativeLayout);
        }
    }


    private void LayoutAddText(TextView textView, int centerInParent, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        // Defining the layout parameters of the Button
        RelativeLayout.LayoutParams textViewLayoutParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add Margin to the LayoutParameters
        textViewLayoutParameters.setMargins(marginLeft, marginTop, marginRight, marginBottom);

        // Add Rule to Layout
        textViewLayoutParameters.addRule(centerInParent);

        // Setting the parameters on the Button
        textView.setLayoutParams(textViewLayoutParameters);
    }

    private void AddTextLayout(TextView textView, int centerInParent) {
        // Just call the other AddButtonLayout Method with Margin 0
        LayoutAddText(textView, centerInParent, 0, 0, 0, 0);
    }

    private void LayoutAddButton(Button button, int centerInParent, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        // Defining the layout parameters of the Button
        RelativeLayout.LayoutParams textViewLayoutParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add Margin to the LayoutParameters
        textViewLayoutParameters.setMargins(marginLeft, marginTop, marginRight, marginBottom);

        // Add Rule to Layout
        textViewLayoutParameters.addRule(centerInParent);

        // Setting the parameters on the Button
        button.setLayoutParams(textViewLayoutParameters);
    }

    private void AddButtonLayout(Button button, int centerInParent) {
        // Just call the other AddButtonLayout Method with Margin 0
        LayoutAddButton(button, centerInParent, 0, 0, 0, 0);
    }
}