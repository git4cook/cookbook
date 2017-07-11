package com.groupd.cookbook.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.groupd.cookbook.R;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.step;

import java.util.List;

public class showScroll extends Activity {
    private LayoutInflater inflater;
    private PageView mPageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_scroll);
        Bundle b = getIntent().getExtras();
        String name = b.getString("recipeName");

        inflater = LayoutInflater.from(this);
        mPageView = (PageView) findViewById(R.id.pageview);





        //删除一个页面
//      mPageView.removePage(1);
    }
    private void addSteps(List<step>steps){
        for(int i = 1;i<=steps.size();i++){
            RelativeLayout relativeLayout = new RelativeLayout(this);

            RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);

            TextView text1 = new TextView(this);
            text1.setText("Step "+i);
            text1.setTextSize(70);
            TextView text2 = new TextView(this);
            text2.setText(steps.get(i-1).getStepsName());
            text2.setTextSize(40);
            Button button1 = new Button(this);
            button1.setText("Back");
            AddTextLayout(text1, RelativeLayout.CENTER_HORIZONTAL);
            AddTextLayout(text2, RelativeLayout.CENTER_IN_PARENT);
            AddButtonLayout(button1, RelativeLayout.ALIGN_PARENT_RIGHT);
            relativeLayout.addView(text1);
            relativeLayout.addView(text2);

            mPageView.addPage(relativeLayout);
        }
    }
    public void buttonBackOnClick (View v) throws myException {
        Intent back;
        back = new Intent(this, MainActivity.class);
        showScroll.this.startActivity(back);

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