package com.example.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {

    ArrayList<Author> list= new ArrayList<>();
    DbHelper dbHelper;
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        EditText et_id= findViewById(R.id.ed_id);
        EditText et_name= findViewById(R.id.ed_name);
        EditText et_address= findViewById(R.id.ed_address);
        EditText et_email= findViewById(R.id.ed_email);

        gv= findViewById(R.id.grig_view);
        dbHelper= new DbHelper(this);

        getAll();

        Button bt_save= findViewById(R.id.save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author= new Author();
                author.setId(Integer.parseInt(et_id.getText().toString()));
                author.setName(et_name.getText().toString());
                author.setAddress(et_address.getText().toString());
                author.setEmail(et_email.getText().toString());

                if(dbHelper.addAuthor(author)>0){
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
                    list= dbHelper.getAllAuthor();
                }else{
                    int id=Integer.parseInt(et_id.getText().toString());
                    if(ktraMa(id)==false){
                        Toast.makeText(getApplicationContext(),"Khong tim thay !!",Toast.LENGTH_SHORT).show();
                    }else{
                        list.removeAll(list);
                        Author author= dbHelper.getAuthorById(id);
                        listString.add(author.getId()+"");
                        listString.add(author.getName());
                        listString.add(author.getAddress());
                        listString.add(author.getEmail());
                        et_name.setText(author.getName());
                        et_address.setText(author.getAddress());
                        et_email.setText(author.getEmail());
                    }
                }

                for (Author au:list){
                    listString.add(au.getId()+"");
                    listString.add(au.getName());
                    listString.add(au.getAddress());
                    listString.add(au.getEmail());
                }

                ArrayAdapter<String> adapter= new ArrayAdapter<>(AuthorActivity.this, android.R.layout.simple_list_item_1,listString);
                gv.setAdapter(adapter);
            }
        });

        Button btn_update= findViewById(R.id.update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author= new Author();
                author.setId(Integer.parseInt(et_id.getText().toString()));
                author.setName(et_name.getText().toString());
                author.setAddress(et_address.getText().toString());
                author.setEmail(et_email.getText().toString());

                if(dbHelper.updateAuthor(author)>0){
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
                if(dbHelper.deleteAuthor(id)>0){
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
                Intent intent= new Intent(AuthorActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                et_id.setText(list.get(i).getId());
//                et_name.setText(list.get(i).getName());
//                et_address.setText(list.get(i).getAddress());
//                et_email.setText(list.get(i).getEmail());
//            }
//        });
    }

    public void getAll(){

        ArrayList<String> listString= new ArrayList<>();
        list.removeAll(list);
        listString.removeAll(listString);
        list= dbHelper.getAllAuthor();

        for (Author au:list){
            listString.add(au.getId()+"");
            listString.add(au.getName());
            listString.add(au.getAddress());
            listString.add(au.getEmail());
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<>(AuthorActivity.this, android.R.layout.simple_list_item_1,listString);
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