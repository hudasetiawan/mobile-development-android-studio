package com.example.twoactivitieslifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.getStringArrayList("list") != null) {
            items = savedInstanceState.getStringArrayList("list");
            drawView();
        }
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("list", items);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            String item = data.getStringExtra(SecondActivity.EXTRA_MESSAGE);
            items.add(item);
            drawView();
        }
    }

    public void drawView() {
        TextView tv = findViewById(R.id.textView);
        tv.setText("");

        // Tampilkan semua item dalam daftar
        for (String item : items) {
            tv.setText(tv.getText() + item + "\n");
        }
    }
}
