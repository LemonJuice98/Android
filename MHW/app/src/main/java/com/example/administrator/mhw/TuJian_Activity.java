package com.example.administrator.mhw;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TuJian_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_jian);

        final List<TuJian> tujianList = new ArrayList<>();
        TuJianAdapter adapter = new TuJianAdapter(TuJian_Activity.this, R.layout.tujian_item, tujianList);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "MHW.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Monster", null, null, null, null, null, null);
        if( cursor.moveToFirst() ) {
            do {
                String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String classify = cursor.getString(cursor.getColumnIndex("Classify"));
                String weak = cursor.getString(cursor.getColumnIndex("Weak"));
                String state = cursor.getString(cursor.getColumnIndex("State"));

                if(state == null) state=state+"（无突出项）";
                TuJian tujian = new TuJian(pinyin, name, classify, weak, state);
                tujianList.add(tujian);
            } while(cursor.moveToNext());
        }
        cursor.close();

        ListView listView = (ListView) findViewById(R.id.tujian_list);
        listView.setAdapter(adapter);
    }
}
