package com.example.messanger.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.NAME, DatabaseHelper.LOGIN, DatabaseHelper.PASSWORD};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public UserEntity getEntity(){
        UserEntity entity = null;
        String query = String.format("SELECT * FROM %s LIMIT 1", DatabaseHelper.TABLE);
        Cursor cursor = database.rawQuery(query, new String[]{ });
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
            @SuppressLint("Range") String login = cursor.getString(cursor.getColumnIndex(DatabaseHelper.LOGIN));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PASSWORD));
            entity = new UserEntity(name, login, password);
        }
        cursor.close();
        return  entity;
    }

    public long insert(UserEntity entity){

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NAME, entity.getName());
        cv.put(DatabaseHelper.LOGIN, entity.getLogin());
        cv.put(DatabaseHelper.PASSWORD, entity.getPassword());

        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(String entityLogin){

        String whereClause = dbHelper.LOGIN + " = ?";
        String[] whereArgs = new String[]{entityLogin};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(UserEntity entity){

        String whereClause = DatabaseHelper.LOGIN + "=" + entity.getLogin();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NAME, entity.getName());
        cv.put(DatabaseHelper.LOGIN, entity.getLogin());
        cv.put(DatabaseHelper.PASSWORD, entity.getPassword());
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}
