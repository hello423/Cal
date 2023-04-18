package com.example.cal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SecondScreen extends Activity {

    String[] list;
    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = getIntent().getStringArrayListExtra("list").toArray(new String[0]);
        setContentView(R.layout.second_screen);
        container = findViewById(R.id.container);

        for (String s : list) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            textView.setText(s);
            textView.setLayoutParams(layoutParams);
            container.addView(textView);
        }
    }
}
