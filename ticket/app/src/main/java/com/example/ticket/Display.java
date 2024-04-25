package com.example.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent=getIntent();
        String gender = getIntent().getStringExtra("gender");
        String ticketType = getIntent().getStringExtra("ticketType");
        int ticketCount = getIntent().getIntExtra("ticketCount", 0);
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);

        TextView genderTextView = findViewById(R.id.textView);
        genderTextView.setText("性別：" + gender);
        TextView ticketTypeTextView = findViewById(R.id.textView_ticketType);
        ticketTypeTextView.setText("票種：" + ticketType);
        TextView ticketCountTextView = findViewById(R.id.textView_ticketCount);
        ticketCountTextView.setText("張數：" + ticketCount);
        TextView totalPriceTextView = findViewById(R.id.textView_totalPrice);
        totalPriceTextView.setText("總金額：" + totalPrice + "元");
    }
}
