//package com.example.android.sqlitewithrecycler.Utils;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;
//
//import com.example.android.sqlitewithrecycler.Model.Info;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//    public static final String DB_NAME = "sample.db";
//    public static final String DB_PATH = "";
//    public static final int DB_VERSION = 1;
//    public static final String TABLE_NAME = "Information";
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_HEAD = "head";
//    public static final String COLUMN_BODY = "body";
//
//    public DBHelper(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_HEAD + " TEXT NOT NULL, " +
//                COLUMN_BODY + " TEXT NOT NULL);"
//        );
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        this.onCreate(db);
//    }
//
//    /** Create records **/
//
//    public void saveNewInfo(Info info){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_HEAD, info.getHead());
//        values.put(COLUMN_BODY, info.getBody());
//
//        //insert
//        db.insert(TABLE_NAME, null, values);
//        db.close();
//    }
//
//    public List<Info> infoList(){
//        String query;
//
//        query = "SELECT * FROM Information";
//        List<Info> infoLinkedList = new LinkedList<>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Info info;
//
//        if(cursor.moveToFirst()){
//            do{
//                info = new Info();
//
//                info.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
//                info.setHead(cursor.getString(cursor.getColumnIndex(COLUMN_HEAD)));
//                info.setBody(cursor.getString(cursor.getColumnIndex(COLUMN_BODY)));
//                infoLinkedList.add(info);
//            } while (cursor.moveToNext());
//        }
//
//        System.out.print(infoLinkedList);
//        return infoLinkedList;
//    }
//
//    /**Query only 1 record**/
//    public Info getInfo(long id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE id="+ id;
//        Cursor cursor = db.rawQuery(query, null);
//
//        Info receivedInfo = new Info();
//        if(cursor.getCount() > 0) {
//            cursor.moveToFirst();
//
//            receivedInfo.setHead(cursor.getString(cursor.getColumnIndex(COLUMN_HEAD)));
//            receivedInfo.setBody(cursor.getString(cursor.getColumnIndex(COLUMN_BODY)));
//        }
//
//        return receivedInfo;
//    }
//
//    /**delete record**/
//    public void deletePersonRecord(long id, Context context) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
//        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();
//
//    }
//
//    /**update record**/
//    public void updatePersonRecord(long personId, Context context, Info updatedinfo) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        //you can use the constants above instead of typing the column names
//        db.execSQL("UPDATE  "+TABLE_NAME+" SET name ='"+ updatedinfo.getHead() + "', age ='" + updatedinfo.getBody()+ "'  WHERE _id='" + personId + "'");
//        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();
//
//
//    }
//}
