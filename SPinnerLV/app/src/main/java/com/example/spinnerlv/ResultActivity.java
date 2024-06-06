package com.example.spinnerlv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        if (intent == null) {

            TextView resultTextView = findViewById(R.id.resultTextView);

            String mainCourse = getIntent().getStringExtra("MAIN_COURSE");
            String sideDish = getIntent().getStringExtra("SIDE_DISH");
            String drink = getIntent().getStringExtra("DRINK");

            String result = "主餐：" + mainCourse + "\n附餐：" + sideDish + "\n飲料：" + drink;
            resultTextView.setText(result);
        }
    }
}