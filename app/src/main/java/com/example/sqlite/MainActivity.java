package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name, age;
    private Button insert, delete, update, read;
    private DBhelper Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        insert = findViewById(R.id.insert);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        read = findViewById(R.id.read);
        Db = new DBhelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textname = name.getText().toString();
                String textage = age.getText().toString();
                if(Db.insertData(textname, textage)){
                    Toast.makeText(MainActivity.this, "Data upload successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data upload fail", Toast.LENGTH_LONG).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = Db.readData();
                StringBuffer stringBuffer = new StringBuffer("");
                while (cursor.moveToNext()){
                    stringBuffer.append("Name: " + cursor.getString(0) + "\n");
                    stringBuffer.append("Age: " + cursor.getString(1) + "\n");
                }
                Toast.makeText(MainActivity.this, stringBuffer, Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textname = name.getText().toString();
                if (Db.deleteData(textname)){
                    Toast.makeText(MainActivity.this, "Delete successfully" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}