package com.example.android.sqlitewithrecycler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.sqlitewithrecycler.Model.Info;
import com.example.android.sqlitewithrecycler.Model.MyAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataBaseHelper dbHelper;
    private MyAdapter adapter;
    private Button mAddButton;
    private List<Info> mInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the variables
        mAddButton = (Button) findViewById(R.id.addInfoButton);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dbHelper = new DataBaseHelper(this);

        //populate recyclerview
        // populaterecyclerView();

        File database = getApplicationContext().getDatabasePath(DataBaseHelper.DATABASE_NAME);
        if (false == database.exists()) {
            dbHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //Get product list in db when db exists
        mInfoList = dbHelper.infoList();
        //Init adapter
        adapter = new MyAdapter(this, mInfoList, mRecyclerView);
        //Set adapter for listview
        mRecyclerView.setAdapter(adapter);
        registerForContextMenu(mRecyclerView);
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        registerForContextMenu(mRecyclerView);

//        mAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToAddUserActivity();
//            }
//        });
//    }
    }


    private boolean copyDatabase (Context context){
        try {

            InputStream inputStream = context.getAssets().open(DataBaseHelper.DATABASE_NAME);
            String outFileName = DataBaseHelper.DATABASE_PATH + DataBaseHelper.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


//    private void populaterecyclerView(){
//        dbHelper = new DataBaseHelper(this);
//        adapter = new MyAdapter(dbHelper.infoList(), this, mRecyclerView);
//        mRecyclerView.setAdapter(adapter);
//
//    }


//    private void goToAddUserActivity(){
//        Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
//        startActivity(intent);
//    }

        @Override
        protected void onResume () {
            super.onResume();
            adapter.notifyDataSetChanged();
        }
    }

