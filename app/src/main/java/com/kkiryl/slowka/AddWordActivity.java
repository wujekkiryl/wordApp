package com.kkiryl.slowka;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;

public class AddWordActivity extends Activity {

    private DBHellper dbHellper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        this.setFinishOnTouchOutside(false);
        // getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void addWord(View viwew)
    {
        dbHellper = new DBHellper(this);
        db = dbHellper.getWritableDatabase();
        EditText source = (EditText) this.findViewById(R.id.source);
        EditText translated = (EditText) this.findViewById(R.id.translation);
        dbHellper.addWordToDB(db, source.getText().toString(), translated.getText().toString());
        this.finish();
    }

}
