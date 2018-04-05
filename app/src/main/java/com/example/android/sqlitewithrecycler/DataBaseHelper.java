package com.example.android.sqlitewithrecycler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.sqlitewithrecycler.Model.Info;

import java.util.LinkedList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;
    private final Context mContext;

    public static final String DATABASE_NAME = "sample.db";
    public final static String DATABASE_PATH = "/data/data/com.example.android.sqlitewithrecycler/databases/";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Information";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_HEAD = "head";
    public static final String COLUMN_BODY = "body";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Info> infoList() {
        Info info = null;
        List<Info> infoList = new LinkedList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            info = new Info(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
            info.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
            info.setHead(cursor.getString(cursor.getColumnIndex(COLUMN_HEAD)));
            info.setBody(cursor.getString(cursor.getColumnIndex(COLUMN_BODY)));
            infoList.add(info);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return infoList;
    }
}
