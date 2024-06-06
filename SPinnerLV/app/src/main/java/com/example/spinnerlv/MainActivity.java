package com.example.spinnerlv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner, spinner2;
    private ListView listView;
    private TextView textView1, textView2, textView3;

    private String[] mainCourses = {"牛排", "雞肉", "魚"};
    private String[] sideDishes = {"薯條", "沙拉", "湯"};
    private String[] drinks = {"水", "汽水", "果汁"};

    private String selectedMainCourse = "";
    private String selectedSideDish = "";
    private String selectedDrink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        spinner2 = findViewById(R.id.spinner2);

        // 設置 Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.menu_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> actionAdapter = ArrayAdapter.createFromResource(this,
                R.array.action_categories, android.R.layout.simple_spinner_item);
        actionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(actionAdapter);

        // 設置初始值
        updateListView(0);

        // 設置 Spinner 的 onItemSelectedListener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // 設置 ListView 的 onItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) listView.getItemAtPosition(position);
                int selectedPosition = spinner.getSelectedItemPosition();
                updateTextView(selectedPosition, selectedItem);
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) { // 送出選項
                    submitSelection();
                } else if (position == 2) { // 取消選項
                    clearSelections();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

    }




    private void updateListView(int position) {
        String[] items;

        switch (position) {
            case 0:
                items = mainCourses;
                break;
            case 1:
                items = sideDishes;
                break;
            case 2:
                items = drinks;
                break;
            default:
                items = new String[]{};
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(listViewAdapter);
    }

    private void updateTextView(int position, String selectedItem) {
        switch (position) {
            case 0:
                selectedMainCourse = selectedItem;
                break;
            case 1:
                selectedSideDish = selectedItem;
                break;
            case 2:
                selectedDrink = selectedItem;
                break;
        }
        textView1.setText("主餐：" + selectedMainCourse);
        textView2.setText("附餐：" + selectedSideDish);
        textView3.setText("飲料：" + selectedDrink);
    }
    private void submitSelection() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("MAIN_COURSE", selectedMainCourse);
        intent.putExtra("SIDE_DISH", selectedSideDish);
        intent.putExtra("DRINK", selectedDrink);
        startActivity(intent);
    }

    private void clearSelections() {
        selectedMainCourse = "";
        selectedSideDish = "";
        selectedDrink = "";
        textView1.setText("主餐：");
        textView2.setText("附餐：");
        textView3.setText("飲料：");
        spinner2.setSelection(0);  // Reset action spinner to default
    }
}