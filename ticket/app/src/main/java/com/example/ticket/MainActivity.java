package com.example.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int ADULT_TICKET_PRICE = 500; // 調整成正確的價格
    private static final int CHILD_TICKET_PRICE = 250;
    private static final int STUDENT_TICKET_PRICE = 400;
    private boolean totalPrice;private TextView textViewResult;
    private RadioGroup rgGender, rgType;
    private EditText edtTickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.textViewResult);
        rgGender = findViewById(R.id.rgGender);
        rgType = findViewById(R.id.rgType);
        edtTickets = findViewById(R.id.edtTickets);

        // 设置性别和票种的选择监听器
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateResultText();
            }
        });

        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateResultText();
            }
        });

        // 设置票数输入框的监听器
        edtTickets.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    updateResultText();
                }
            }
        });
    }


    // 更新结果文本
    private void updateResultText() {
        RadioButton selectedGenderButton = findViewById(rgGender.getCheckedRadioButtonId());
        RadioButton selectedTypeButton = findViewById(rgType.getCheckedRadioButtonId());

        String gender = selectedGenderButton.getText().toString();
        String ticketType = selectedTypeButton.getText().toString();
        int ticketCount = 0;
        try {
            ticketCount = Integer.parseInt(edtTickets.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        // 根据票种计算总价
        int totalPrice = 0;

        if (selectedTypeButton.getId() == R.id.rdbAdult) {
            totalPrice = ADULT_TICKET_PRICE * ticketCount;
        } else if (selectedTypeButton.getId() == R.id.rdbChild) {
            totalPrice = CHILD_TICKET_PRICE * ticketCount;
        } else if (selectedTypeButton.getId() == R.id.rdbStudent) {
            totalPrice = STUDENT_TICKET_PRICE * ticketCount;
        }



        String resultText = "性別：" + gender + "\n票種：" + ticketType + "\n張數：" + ticketCount + "\n總價：" + totalPrice;
        textViewResult.setText(resultText);
    }

    // 按钮点击事件



    public void button_Click(View view) {
        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        String gender = "";
        if (boy.isChecked())
            gender = "男生";
        else if (girl.isChecked())
            gender = "女生";

        RadioGroup type = findViewById(R.id.rgType);
        String ticketType = "";
        int selectedTypeId = type.getCheckedRadioButtonId();
        if (selectedTypeId == R.id.rdbAdult)
            ticketType = "全票";
        else if (selectedTypeId == R.id.rdbChild)
            ticketType = "兒童票";
        else if (selectedTypeId == R.id.rdbStudent)
            ticketType = "學生票";

        EditText editText=findViewById(R.id.edtTickets);
        int ticketCount = Integer.parseInt(editText.getText().toString()); // 讀取票數
        int totalPrice = 0; // 初始化總價

        // 根據票種計算總價格
        if(selectedTypeId == R.id.rdbAdult)
            totalPrice = ADULT_TICKET_PRICE * ticketCount;
        else if(selectedTypeId == R.id.rdbChild)
            totalPrice = CHILD_TICKET_PRICE * ticketCount;
        else
            totalPrice = STUDENT_TICKET_PRICE * ticketCount;

        Intent intent = new Intent(MainActivity.this, Display.class);
        intent.putExtra("gender", gender);
        intent.putExtra("ticketType", ticketType);
        intent.putExtra("ticketCount", ticketCount);
        intent.putExtra("totalPrice", totalPrice); // 傳遞總價格

        startActivity(intent);


    }
}
