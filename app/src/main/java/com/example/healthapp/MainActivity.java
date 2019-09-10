package com.example.healthapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.healthapp.MESSAGE";
    DatabaseHelper myDatabase;

    Button button;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        myDatabase = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                // Do something in response to button click
                Intent intent = new Intent(v.getContext(),AddHealth.class);
                EditText editText = (EditText) findViewById(R.id.editText);
                String name = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, name);

                boolean tester = myDatabase.checkAccount(name);

                if (tester == true) {
                    startActivity(intent);
                } else {
                    boolean isInserted = myDatabase.insertData(name, 0, 0);
                    if (isInserted == true) {
                        Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                    startActivity(intent);
                }
            }

        });


    }


    public void buttonClick(){
        button.setOnClickListener(new View.OnClickListener() {
          public void onClick (View v){

                // Do something in response to button click
                Intent intent = new Intent(v.getContext(),AddHealth.class);
                EditText editText = (EditText) findViewById(R.id.editText);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);

                boolean tester = myDatabase.checkAccount(message);

                if (tester == true) {
                    startActivity(intent);
                } else {
                    boolean isInserted = myDatabase.insertData(message, 0, 0);
                    if (isInserted == true) {
                        Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                    startActivity(intent);
                }
            }

        });

    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this,AddHealth.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        boolean tester = myDatabase.checkAccount(message);

        if(tester == true){
            startActivity(intent);
        }else{
            boolean isInserted = myDatabase.insertData(message, 0, 0);
            if(isInserted == true){
                Toast.makeText(MainActivity.this,"Data inserted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"Data not inserted", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
        }




        //startActivity(intent);
    }
}
