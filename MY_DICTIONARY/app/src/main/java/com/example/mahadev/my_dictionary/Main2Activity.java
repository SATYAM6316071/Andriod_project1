package com.example.mahadev.my_dictionary;



import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    int pos;


    public static ArrayList<Dictionary> data;
    Button right;
    Button left;
    TextView TV;
    TextView TV1;

    Cursor cursor1 = null;


    DataBaseHelper dbh = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TV = findViewById(R.id.textView2);
        TV1 = findViewById(R.id.textView3);


        //     tx = (TextSwitcher) findViewById(R.id.textSwitcher);

        right = findViewById(R.id.button3);

        left = findViewById(R.id.button2);


        dbh = new DataBaseHelper(this);

        Bundle b = getIntent().getExtras();
        String Word = b.getString("word");
        Cursor cursor = dbh.getrecord(Word);
        String name = cursor.getString(0);
        String def = cursor.getString(2);
        pos = cursor.getPosition();

        //Toast.makeText(getApplicationContext(),def,Toast.LENGTH_SHORT).show();
        TV.setText(def);
        TV1.setText(name);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos <= 0) {
                    Toast.makeText(Main2Activity.this, "can't go beyond this", Toast.LENGTH_LONG).show();
                } else {
                    pos--;
                    cursor1 = dbh.getAll(pos);

                    cursor1.moveToPosition(pos);

                    String val = cursor1.getString(2);
                    String name = cursor1.getString(0);
                    //    Toast.makeText(this, val, Toast.LENGTH_LONG).show();
                    TV.setText(val);
                    TV1.setText(name);
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor1 = dbh.getAll(pos);
             //   Toast.makeText(this, "inside rignt", Toast.LENGTH_LONG).show();
                if (pos > cursor1.getCount()) {
                    Toast.makeText(Main2Activity.this, "You are on last word!", Toast.LENGTH_LONG).show();
                } else {
                    pos++;
                    cursor1 = dbh.getAll(pos);
                    cursor1.moveToPosition(pos);
                    String name = cursor1.getString(0);
                    String val = cursor1.getString(2);
                    //      Toast.makeText(this, val, Toast.LENGTH_LONG).show();
                    TV.setText(val);
                    TV1.setText(name);
                }
            }

        });

    }
}
