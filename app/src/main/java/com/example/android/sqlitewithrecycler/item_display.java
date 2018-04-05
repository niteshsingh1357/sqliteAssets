package com.example.android.sqlitewithrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class item_display extends AppCompatActivity {

    TextView mtextView1, mtextView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_display);

        mtextView1 = findViewById(R.id.textView1);
        mtextView2 = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String question = intent.getExtras().getString("string1");
        String answer = intent.getExtras().getString("string2");

        mtextView1.setText(question);
        mtextView2.setText(answer);



    }
}
