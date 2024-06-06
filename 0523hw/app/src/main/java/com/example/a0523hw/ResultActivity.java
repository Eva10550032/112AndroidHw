package com.example.a0523hw;


    import android.os.Bundle;
    import android.widget.TextView;
    import androidx.appcompat.app.AppCompatActivity;

    public class ResultActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            TextView resultTextView = findViewById(R.id.resultTextView);

            String mainCourse = getIntent().getStringExtra("MAIN_COURSE");
}
    }
