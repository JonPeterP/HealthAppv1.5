package com.example.healthapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddHealth extends AppCompatActivity {

    DatabaseHelper myDatabase;

    TextView textView2;
    EditText editText3, editText4;
    Button button2, button4;

    public float weight;
    public float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);

        myDatabase = new DatabaseHelper(this);

        textView2 = (TextView) findViewById(R.id.textView2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);

        button2 = (Button) findViewById(R.id.button2);
        button4 = (Button) findViewById(R.id.button4);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.textView2);
        textView2.setText(message);

            showMessage("Testing", "Hello world.");

     //   viewData();
     //   insertData();


        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                String name = textView2.getText().toString();

                Cursor result = myDatabase.getAllData();

               if (result.getCount() == 0) {
                    showMessage("Error", "No data found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (result.moveToNext()) {
                        buffer.append("Id : " + result.getString(0) + "\n");
                        buffer.append("Name : " + result.getString(1) + "\n");
                        buffer.append("Weight : " + result.getString(2) + "\n");
                        buffer.append("Height : " + result.getString(3) + "\n\n");

                    }


                    //show all data
                    showMessage("Data", buffer.toString());

                }
            }

    });


        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name = textView2.getText().toString();

                weight = Float.valueOf(editText3.getText().toString());
                height = Float.valueOf(editText4.getText().toString());

                myDatabase.updateData(name, weight, height);

            }
        });
    }

    public void insertData(View view) {
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String name = textView2.getText().toString();

        weight = Float.valueOf(editText3.getText().toString());
        height = Float.valueOf(editText4.getText().toString());

        myDatabase.updateData(name, weight, height);


    }


    public void viewData(View view) {
        //String name;
   /*     button4.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
*/
                        String name = textView2.getText().toString();

                        Cursor result = myDatabase.getData(name);

                        if (result.getCount() == 0) {
                            showMessage("Error", "No data found");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (result.moveToNext()) {
                                buffer.append("Id : " + result.getString(0) + "\n");
                                buffer.append("Name : " + result.getString(1) + "\n");
                                buffer.append("Weight : " + result.getString(2) + "\n");
                                buffer.append("Height : " + result.getString(3) + "\n\n");

                            }

                            //show all data
                            showMessage("Data", buffer.toString());

                        }
           //         }
              //      }
      //  );

    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void DisplayData() {
        //studentNumber = (String) textResult.getText().subSequence(0, 11);
        String name = (String) textView2.getText();
        Cursor result = myDatabase.getData(name);

        if (result.getCount() == 0) {
            //showMessage("Error", "No data found");
            // textViewInfo.setText( "\t" + "No Student");
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (result.moveToNext()) {

                //calculate bmi;


                Float bmi;

                buffer.append("ID : " + result.getString(0) + "\n");
                buffer.append("Name : " + result.getString(1) + "\n");
                buffer.append("Weight : " + result.getString(2) + "\n");
                buffer.append("Height : " + result.getString(3) + "\n");

                //calculate bmi;


            }

            //show all data
            showMessage("Data", buffer.toString());
            //  textViewInfo.setText(buffer.toString());


        }
    }
}

