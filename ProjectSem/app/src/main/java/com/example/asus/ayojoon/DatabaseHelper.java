package com.example.asus.ayojoon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String Database_name= "userdetails.db" ;
    private static final String Table_name= "info";
    private static final String User = " Username";
    private static final String Name = " Name";
    private static final String Gender = "Gender";
    private static final String PhoneNumber="_Phonenumber" ;
    private static final String Password= "Password" ;
    private static final int Version_number=5 ;

    private Context context ;

    private static  final String Create_Table = " CREATE TABLE "+Table_name+"( "+Name+" VARCHAR(40),"+User+" VARCHAR(20) UNIQUE,"+Gender+" VARCHAR(6),"+PhoneNumber+" INTEGER PRIMARY KEY,"+Password+" INTEGER) ;" ;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+Table_name ;
    public DatabaseHelper(Context context ) {
        super(context, Database_name, null, Version_number);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Create_Table);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();

        }

    }


    public long insertData(Userinfo userinfo)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(Name,userinfo.getName());
        contentValues.put(User,userinfo.getUser());
        contentValues.put(Gender,userinfo.getGender());
        contentValues.put(PhoneNumber,userinfo.getPhone());
        contentValues.put(Password,userinfo.getPassword());

        long rowId=sqLiteDatabase.insert(Table_name,null,contentValues) ;
        return rowId ;
    }


    public boolean findPassword ( String uname ,String upassword)
    {
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase() ;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+Table_name,null);
        Boolean result = false ;

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"There are no users ",Toast.LENGTH_LONG).show();

        }
        else {
            while (cursor.moveToNext())
            {
                String users=cursor.getString(1) ;
                String passs=cursor.getString(4) ;

                if(users.equals(uname)&& passs.equals(upassword))
                {
                    result = true ;
                    Toast.makeText(context,"Signed In ",Toast.LENGTH_LONG).show();

                    break ;

                }




            }

        }
        return result ;
    }

}