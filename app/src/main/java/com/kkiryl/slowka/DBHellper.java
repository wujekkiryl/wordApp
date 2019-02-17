package com.kkiryl.slowka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHellper extends SQLiteOpenHelper {
    public final static int DB_VER = 4;
    public final static String ID = "_id";
    public final static String DB_NAME = "words.db";
    public final static String TABLE_NAME = "words";
    public final static String COL1 = "source";
    public final static String COL2 = "translated";
    public final static String DB_CREATION = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 +
            " text not null, " + COL2 + " text );";
    private final static String DB_DELETION = "DROP TABLE IF EXISTS "+DB_NAME;

    public DBHellper(Context context)
    {
        super(context, DB_NAME, null , DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.w(DBHellper.class.getName(), DB_CREATION);
        db.execSQL(DB_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DBHellper.class.getName(), "upgrading DB from "+ oldVersion + " to " + newVersion);
        db.execSQL(DB_DELETION);
        onCreate(db);
    }

    public void addWordToDB(SQLiteDatabase db, String source, String translated)
    {
        ContentValues values = new ContentValues();
        values.put(this.COL1, source);
        values.put(this.COL2, translated);
        db.insert(this.TABLE_NAME, null, values);
    }

    public Cursor getLastWords(SQLiteDatabase db)
    {
        String[] cols = {this.COL1, this.COL2};
        Cursor cursor = db.query(this.TABLE_NAME, cols, null, null, null, null, null, null );

        return cursor;
    }
}
