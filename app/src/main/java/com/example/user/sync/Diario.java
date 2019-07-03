package com.example.user.sync;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.sync.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Diario extends ActionBarActivity{

    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;

    Calendar calendar;
    SimpleDateFormat simpledateformat;
    String Date;

    DiarioDatabaseHelper diarioDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        diarioDB = new DiarioDatabaseHelper(this);

        Cursor res = diarioDB.getAllData();

        itemList = new ArrayList<String>();

        res.moveToFirst();
        do{
            itemList.add(res.getString(1));
        }while(res.moveToNext());

        adapter=new ArrayAdapter<String>(this,R.layout.diariolist_item,R.id.item_diario,itemList);
        ListView listV=(ListView)findViewById(R.id.diariolist);

        listV.setAdapter(adapter);

        editText=(EditText)findViewById(R.id.diarioTextInput);
        Button btAdd=(Button)findViewById(R.id.diariobtAdd);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date = simpledateformat.format(calendar.getTime());

                String newItem = editText.getText().toString();

                String registroDiario = Date + "\n" + newItem;

                // add new item to arraylist
                itemList.add(registroDiario);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
                editText.getText().clear();

                boolean isInserted = diarioDB.insertData(registroDiario);

                if (isInserted == true) {
                    Toast.makeText(Diario.this, "Registro inserido na tabela", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Diario.this, "Registro não inserido na tabela", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}