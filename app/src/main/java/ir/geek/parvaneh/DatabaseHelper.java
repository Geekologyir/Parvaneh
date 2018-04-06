package ir.geek.parvaneh;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Project.db";

    public static final String TABLE_USER="T_USERS";

    public static final String T_USER_ID="ID";
    public static final String T_USER_EMAIL="EMAIL";
    public static final String T_USER_USERNAME="USERNAME";
    public static final String T_USER_PASSWORD="PASSWORD";
    public static final String T_USER_PHONENUMBER="PHONENUMBER";
    public static final String T_USER_REGISTRATIONDATE="REGISTRATIONDATE";



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_USER+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT,USERNAME TEXT,PASSWORD TEXT,PHONENUMBER TEXT,REGISTRATIONDATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);
    }
    public boolean signupDB(String email,String password){
        Log.d("A","Data Inserted.............");
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(T_USER_EMAIL,email);
        contentValues.put(T_USER_PASSWORD,password);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        String date = sdf.format(new Date());
        contentValues.put(T_USER_REGISTRATIONDATE,date);
        long result=db.insert(TABLE_USER,null,contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }
}
