package com.kkiryl.slowka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {

    private DBHellper dbHellper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWords();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getWords();
    }

    public void openAddWordModal(View viwew)
    {
        Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
        startActivity(intent);
    }

    private void getWords()
    {
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.text_layout);
        layout.removeAllViews();
        dbHellper = new DBHellper(this);
        db = dbHellper.getReadableDatabase();
        Cursor cursor = dbHellper.getLastWords(db);
        while (cursor.moveToNext())
        {
            String source = cursor.getString(0);
            String translated = cursor.getString(1);
            LayoutParams lparams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            TextView tv = new TextView(this);
            tv.setLayoutParams(lparams);
            tv.setText(source + " - " + translated);
            layout.addView(tv);
        }
    }
}
