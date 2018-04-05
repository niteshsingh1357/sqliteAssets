//package com.example.android.sqlitewithrecycler;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.android.sqlitewithrecycler.Model.Info;
//
//public class AddRecordActivity extends AppCompatActivity {
//
//    private EditText mHeadEditText, mBodyEditText;
//    private Button mAddButton;
//
//    private DBHelper dbHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_record);
//
//        mHeadEditText = (EditText)findViewById(R.id.head);
//        mBodyEditText = (EditText)findViewById(R.id.body);
//        mAddButton = (Button)findViewById(R.id.addButton);
//
//        mAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                savePerson();
//            }
//        });
//    }
//
//    public void savePerson(){
//        String head = mHeadEditText.getText().toString().trim();
//        String body = mBodyEditText.getText().toString().trim();
//        dbHelper = new DBHelper(this);
//
//        if(head.isEmpty()){
//            Toast.makeText(this, "Cannot be NULL", Toast.LENGTH_SHORT).show();
//        }
//
//        if(body.isEmpty()){
//            Toast.makeText(this, "Cannot be NULL", Toast.LENGTH_SHORT).show();
//        }
//
//        //Create new Info
//        Info info = new Info(head,body);
//        if((head.isEmpty()|| body.isEmpty())==false){
//            dbHelper.saveNewInfo(info);
//        }
//
//        Log.d("TAG","item added");
//        System.out.print(info);
//        goBackHome();
//    }
//
//    private void goBackHome(){
//        startActivity(new Intent(AddRecordActivity.this, MainActivity.class));
//    }
//}
