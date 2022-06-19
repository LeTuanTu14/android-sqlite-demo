package com.example.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchBookByAuthorActivity extends AppCompatActivity {

    ArrayList<Book> list= new ArrayList<>();
    DbHelper dbHelper;
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book_by_author);

        EditText et_id= findViewById(R.id.ed_idAuthor);
        gv= findViewById(R.id.grig_viewBook);
        dbHelper= new DbHelper(this);

        Button bt_select= findViewById(R.id.select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> listString= new ArrayList<>();
                listString.removeAll(listString);
                if (et_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Nhap id author can tim !!",Toast.LENGTH_SHORT).show();
                }else{
                    int id=Integer.parseInt(et_id.getText().toString());
                    list.removeAll(list);
                    list= dbHelper.getBooksByAuthor(id);
                    for (Book au:list){
                        listString.add(au.getId()+"");
                        listString.add(au.getTitle());
                        listString.add(au.getId_author()+"");
                    }

                    ArrayAdapter<String> adapter= new ArrayAdapter<>(SearchBookByAuthorActivity.this, android.R.layout.simple_list_item_1,listString);
                    gv.setAdapter(adapter);
                }

            }
        });

        Button btn_exit= findViewById(R.id.exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SearchBookByAuthorActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}