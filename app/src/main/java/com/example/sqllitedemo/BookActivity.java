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

public class BookActivity extends AppCompatActivity {

    ArrayList<Book> list= new ArrayList<>();
    DbHelper dbHelper;
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        EditText et_id= findViewById(R.id.ed_idBook);
        EditText et_title= findViewById(R.id.ed_title);
        EditText et_id_author= findViewById(R.id.ed_idAuthor);

        gv= findViewById(R.id.grig_viewBook);
        dbHelper= new DbHelper(this);

        getAll();

        Button bt_save= findViewById(R.id.save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book= new Book();
                book.setId(Integer.parseInt(et_id.getText().toString()));
                book.setTitle(et_title.getText().toString());
                book.setId_author(Integer.parseInt(et_id_author.getText().toString()));

                if(dbHelper.addBook(book)>0){
                    getAll();
                    Toast.makeText(getApplicationContext(),"Ban da luu thanh cong",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Luu that bai",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button bt_select= findViewById(R.id.select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> listString= new ArrayList<>();
                listString.removeAll(listString);
                if (et_id.getText().toString().equals("")){
                    list.removeAll(list);
                    list= dbHelper.getAllBook();
                }else{
                    int id=Integer.parseInt(et_id.getText().toString());
                    if(ktraMa(id)==false){
                        Toast.makeText(getApplicationContext(),"Khong tim thay !!",Toast.LENGTH_SHORT).show();
                    }else {
                        list.removeAll(list);
                        Book book= dbHelper.getBookById(id);
                        listString.add(book.getId()+"");
                        listString.add(book.getTitle());
                        listString.add(book.getId_author()+"");
                        et_title.setText(book.getTitle());
                        et_id_author.setText(book.getId_author()+"");
                    }

                }

                for (Book au:list){
                    listString.add(au.getId()+"");
                    listString.add(au.getTitle());
                    listString.add(au.getId_author()+"");
                }

                ArrayAdapter<String> adapter= new ArrayAdapter<>(BookActivity.this, android.R.layout.simple_list_item_1,listString);
                gv.setAdapter(adapter);
            }
        });

        Button btn_update= findViewById(R.id.update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book= new Book();
                book.setId(Integer.parseInt(et_id.getText().toString()));
                book.setTitle(et_title.getText().toString());
                book.setId_author(Integer.parseInt(et_id_author.getText().toString()));

                if(dbHelper.updateBook(book)>0){
                    getAll();
                    Toast.makeText(getApplicationContext(),"Ban da update thanh cong",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Update that bai",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn_delete= findViewById(R.id.delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= et_id.getText().toString();
                if(dbHelper.deleteBook(id)>0){
                    getAll();
                    Toast.makeText(getApplicationContext(),"Ban da delete thanh cong",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Delete that bai",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn_exit= findViewById(R.id.exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(BookActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getAll(){

        ArrayList<String> listString= new ArrayList<>();
        list.removeAll(list);
        listString.removeAll(listString);
        list= dbHelper.getAllBook();

        for (Book au:list){
            listString.add(au.getId()+"");
            listString.add(au.getTitle());
            listString.add(au.getId_author()+"");
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<>(BookActivity.this, android.R.layout.simple_list_item_1,listString);
        gv.setAdapter(adapter);
    }

    public boolean ktraMa(int id){
        for (int i=0;i<list.size();i++){
            if((list.get(i).getId())==id)
                return true;
        }
        return false;
    }
}