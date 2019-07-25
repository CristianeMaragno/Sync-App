package com.example.user.sync;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.sync.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class AreaDR extends ActionBarActivity{

    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;

    DrDatabaseHelper drDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_dr);

        drDB = new DrDatabaseHelper(this);

        Cursor res = drDB.getAllData();

        itemList = new ArrayList<String>();

        itemList.add("Aqui ficarão tópicos a para serem resolvidos. Com um clique você pode dizer se a questão está ou não resolvida!" +
                "É um bom medidor de como anda as coisas.");

        res.moveToFirst();
        do{
            itemList.add(res.getString(1));
        }while(res.moveToNext());


        adapter=new ArrayAdapter<String>(this,R.layout.drlist_item,R.id.item_dr,itemList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = super.getView(position, convertView, parent);

                Cursor res2 = drDB.getReolvido(position);

                if(res2.getCount() > 0){
                    res2.moveToFirst();
                    int resolvido = res2.getInt(2);
                    String result = "Resultado" + resolvido;

                    if(resolvido ==  0)
                    {
                        row.setBackgroundColor (Color.RED); // some color
                    }
                    else
                    {
                        // default state
                        row.setBackgroundColor (Color.GREEN); // default coloe
                    }
                }else{
                    Log.d("Count ",String.valueOf(res2.getCount()));
                }

                return row;


            }
        };


        final ListView listV=(ListView)findViewById(R.id.Drlist);

        listV.setAdapter(adapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor res3 = drDB.getReolvido(position);

                if(res3.getCount() > 0) {
                    res3.moveToFirst();
                    int resolvido2 = res3.getInt(2);

                    boolean isUpdate = drDB.updateData(position,resolvido2);

                    if (isUpdate == true) {
                        Cursor res4 = drDB.getReolvido(position);
                        if(res4.getCount() > 0) {
                            res4.moveToFirst();
                            int resolvido3 = res4.getInt(2);

                            if (resolvido3 == 0){
                                parent.getChildAt(position).setBackgroundColor(Color.RED);
                            }else{
                                parent.getChildAt(position).setBackgroundColor(Color.GREEN);
                            }

                            Toast.makeText(AreaDR.this, "Data Update", Toast.LENGTH_LONG).show();
                        }else{
                            Log.d("Count ",String.valueOf(res3.getCount()));
                        }
                    } else {
                        Toast.makeText(AreaDR.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Log.d("Count ",String.valueOf(res3.getCount()));
                }
            }
        });

        editText=(EditText)findViewById(R.id.DrTextInput);
        Button btAdd=(Button)findViewById(R.id.DrbtAdd);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newItem = editText.getText().toString();

                String topicoDR = newItem;

                // add new item to arraylist
                itemList.add(topicoDR);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
                editText.getText().clear();

                boolean isInserted = drDB.insertData(topicoDR);

                if (isInserted == true) {
                    Toast.makeText(AreaDR.this, "Registro inserido na tabela", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AreaDR.this, "Registro não inserido na tabela", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}