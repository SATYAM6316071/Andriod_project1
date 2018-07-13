package com.example.mahadev.my_dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String word=null;
    Button B;
    EditText E;
    DataBaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        B=(Button)findViewById(R.id.button);
        E=(EditText)findViewById(R.id.editText);

     //   dbhelper=new DataBaseHelper(getApplicationContext());


        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word = E.getText().toString();
                dbhelper = new DataBaseHelper(getApplicationContext());
                try {
                    dbhelper.createDataBase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Database Created", Toast.LENGTH_LONG).show();

                if (dbhelper.openDatabase()&& dbhelper.check(E.getText().toString())){
                    Intent i=new Intent(MainActivity.this,Main2Activity.class);
                    i.putExtra("word",E.getText().toString());
                    startActivity(i);
                }
             else Toast.makeText(getApplicationContext(),"word is not here!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

