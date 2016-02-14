package com.demo.raj.todolist.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raj on 2/14/2016.
 */
public class ToDosDb extends SQLiteOpenHelper {

    // tag for debugging
    private static final String LOG_TAG = ToDosDb.class.getSimpleName();

    // db name
    public static final String DATABASE_NAME = "todos.db";

    // db table
    private static final String TABLE_NAME = "items";

    // table columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TODO_DESCRIPTION = "desc";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_TIMESTAMP = "time";

    // db version
    public static final int DATABASE_VERSION = 1;


    public ToDosDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table query
        String createQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO_DESCRIPTION + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_TIMESTAMP + " TEXT" +
                ")";

        // create table
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert item
    public boolean insertToDo(String todo, String location, String timestamp){

        boolean success = true;

        ContentValues value = new ContentValues();
        value.put(COLUMN_TODO_DESCRIPTION, todo);
        value.put(COLUMN_LOCATION, location);
        value.put(COLUMN_TIMESTAMP, timestamp);

        SQLiteDatabase db = getWritableDatabase();
        long res = db.insert(TABLE_NAME, null, value);

        if(res == -1){

            success = false;
        }
        return success;
    }

    // get all the records
    public Cursor getAll(){

        Cursor cursor = null;

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        cursor = getReadableDatabase().rawQuery(selectQuery, null);

        return cursor;
    }
}
