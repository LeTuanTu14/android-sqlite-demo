package com.example.sqllitedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button btn= findViewById(R.id.author);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(MainActivity.this,AuthorActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Button btnbook= findViewById(R.id.book);
//        btnbook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2= new Intent(MainActivity.this,BookActivity.class);
//                startActivity(intent2);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnXemDSAuthor:{
                Intent intent= new Intent(MainActivity.this,AuthorActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.mnXemDSBook:{
                Intent intent2= new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent2);
                break;
            }
            case R.id.mnXemDSBookTheoAuthor:{
                Intent intent3= new Intent(MainActivity.this,SearchBookByAuthorActivity.class);
                startActivity(intent3);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}