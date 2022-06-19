package com.example.sqllitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, "myDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Authors(id integer primary key, name text, address text, email text);");
        sqLiteDatabase.execSQL("CREATE TABLE Books(id integer primary key, title text, id_author integer not null constraint id_author references Authors(id) ON DELETE CASCADE ON UPDATE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Authors");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    public int addAuthor(Author author){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues content= new ContentValues();
        content.put("id",author.getId()+"");
        content.put("name",author.getName());
        content.put("address",author.getAddress());
        content.put("email",author.getEmail());
        int res= (int)db.insert("Authors",null,content);
        db.close();
        return res;
    }

    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list= new ArrayList<>();
        String sql="select * from Authors";
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                cursor.moveToNext();

            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public Author getAuthorById(int id){
        Author list= new Author();
        String sql="select * from Authors where id="+id;
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list=new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                cursor.moveToNext();

            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public int updateAuthor(Author author){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues content= new ContentValues();
        content.put("id",author.getId()+"");
        content.put("name",author.getName());
        content.put("address",author.getAddress());
        content.put("email",author.getEmail());
        int res= (int)db.update("Authors",content,"id=?",new String[]{author.getId()+""});
        db.close();
        return res;
    }

    public int deleteAuthor(String id){
        SQLiteDatabase db= getWritableDatabase();
        int res= (int)db.delete("Authors","id=?",new String[]{id});
        db.close();
        return res;
    }

    public int addBook(Book book){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues content= new ContentValues();
        content.put("id",book.getId()+"");
        content.put("title",book.getTitle());
        content.put("id_author",book.getId_author());
        int res= (int)db.insert("Books",null,content);
        db.close();
        return res;
    }

    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list= new ArrayList<>();
        String sql="select * from Books";
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
                cursor.moveToNext();

            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public Book getBookById(int id){
        Book list= new Book();
        String sql="select * from Books where id="+id;
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list=new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
                cursor.moveToNext();

            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public ArrayList<Book> getBooksByAuthor(int idAuthor){
        ArrayList<Book> list= new ArrayList<>();
        String sql="select * from Books where id_author="+idAuthor;
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
                cursor.moveToNext();

            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public int updateBook(Book author){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues content= new ContentValues();
        content.put("id",author.getId()+"");
        content.put("title",author.getTitle());
        content.put("id_author",author.getId_author());
        int res= (int)db.update("Books",content,"id=?",new String[]{author.getId()+""});
        db.close();
        return res;
    }

    public int deleteBook(String id){
        SQLiteDatabase db= getWritableDatabase();
        int res= (int)db.delete("Books","id=?",new String[]{id});
        db.close();
        return res;
    }

//    public ArrayList<Book> getBooksByAuthorName(String nameAuthor){
//        ArrayList<Book> list= new ArrayList<>();
//        String sql="select * from Authors where name="+nameAuthor;
//        SQLiteDatabase db= getReadableDatabase();
//        Cursor cursor= db.rawQuery(sql,null);
//        if (cursor != null){
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()){
//                Book a=new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
//                cursor.moveToNext();
//
//            }
//            cursor.close();
//            db.close();
//        }
//        return list;
//    }

}
