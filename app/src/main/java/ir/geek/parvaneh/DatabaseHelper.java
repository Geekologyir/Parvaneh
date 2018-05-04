package ir.geek.parvaneh;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Project.db";
//DataBase Tables
    public static final String TABLE_USER = "T_USERS";
    public static final String TABLE_USERPROFILE = "T_USERPROFILE";
    public static final String TABLE_PHYSICALINFO = "T_PHYSICALINFO";
    public static final String TABLE_PHRASE = "T_PHRASE";
    public static final String TABLE_SPORTPLAN = "T_SPORTPLAN";
    public static final String TABLE_SPORTITEMS = "T_SPORTITEMS";
    public static final String TABLE_DOINGSPORTPLAN = "T_DOINGSPORTPLAN";

//T_USERS Columns
    public static final String T_USER_ID = "ID";
    public static final String T_USER_EMAIL = "EMAIL";
    public static final String T_USER_USERNAME = "USERNAME";
    public static final String T_USER_PASSWORD = "PASSWORD";
    public static final String T_USER_PHONENUMBER = "PHONENUMBER";
    public static final String T_USER_REGISTRATIONDATE = "REGISTRATIONDATE";
//T_USERPROFILE Columns
    public static final String T_USERPROFILE_USERID = "USERID";//FK From T_USERS
    public static final String T_USERPROFILE_FIRSTNAME = "FIRSTNAME";
    public static final String T_USERPROFILE_LASTNAME = "LASTNAME";
    public static final String T_USERPROFILE_DOB = "DOB";
    public static final String T_USERPROFILE_CITY = "CITY";
//T_PYSICALINFO Columns
    public static final String T_PHYSICALINFO_USERID = "USERID";//FK From T_USERS
    public static final String T_PHYSICALINFO_HEIGHT = "HEIGHT";
    public static final String T_PHYSICALINFO_WEIGHT = "WEIGHT";
//T_PHRASE Columns
    public static final String T_PHRASE_ID = "ID";
    public static final String T_PHRASE_PHRASE = "PHRASE";
    public static final String T_PHRASE_DESCRIPTION = "DESCRIPTION";
//T_SPORTPLAN Columns
    public static final String T_SPORTPLAN_ID = "ID";
    public static final String T_SPORTPLAN_USERID = "USERID";//FK From T_USERS
    public static final String T_SPORTPLAN_CREATORID = "CREATIONID";
    public static final String T_SPORTPLAN_TOTALDURATION = "TOTALDURATION";
    public static final String T_SPORTPLAN_STARTTIME = "STARTTIME";
    public static final String T_SPORTPLAN_SUBMITIONDATE = "SUBMITIONDATE";
    public static final String T_SPORTPLAN_TITLE = "TITLE";
    public static final String T_SPORTPLAN_FINISHTIME = "FINISHTIME";
    public static final String T_SPORTPLAN_REPEATTYPE = "REPEATTYPE";
//T_SPORTITEMS Columns
    public static final String T_SPORTITEMS_ID = "ID";
    public static final String T_SPORTITEMS_ORDER = "ORDER_";
    public static final String T_SPORTITEMS_ISREST = "ISREST";
    public static final String T_SPORTITEMS_SPID = "SPID";//FK From SPORTPLAN
    public static final String T_SPORTITEMS_DURATION = "DURATION";
    public static final String T_SPORTITEMS_NAME = "NAME";
//T_DOINGSPORTPLAN Columns
    public static final String T_DOINGSPORTPLAN_SPID = "SPID";
    public static final String T_DOINGSPORTPLAN_SPIID = "SPIID";
    public static final String T_DOINGSPORTPLAN_STATUS = "STATUS";
    public static final String T_DOINGSPORTPLAN_FINISHTIME = "FINISHTIME";
    public static final String T_DOINGSPORTPLAN_STARTTIME = "STARTTIME";
    public static final String T_DOINGSPORTPLAN_PERCENTAGE = "PERCENTAGE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //shPref=getSharedPreferences(MyPref,Context.MODE_PRIVATE);

//TABLE_USER Creation
        db.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                T_USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                T_USER_EMAIL+" TEXT," +
                T_USER_USERNAME+" TEXT," +
                T_USER_PASSWORD+" TEXT," +
                T_USER_PHONENUMBER+" TEXT," +
                T_USER_REGISTRATIONDATE+" DATE)");

//TABLE_USERPROFILE Creation
        db.execSQL("CREATE TABLE " + TABLE_USERPROFILE + " (" +
                T_USERPROFILE_USERID+" INTEGER PRIMARY KEY," +
                T_USERPROFILE_FIRSTNAME+" TEXT," +
                T_USERPROFILE_LASTNAME+" TEXT ," +
                T_USERPROFILE_DOB+" DATE," +
                T_USERPROFILE_CITY+" TEXT," +
                "FOREIGN KEY("+ T_USERPROFILE_USERID +") REFERENCES "+ TABLE_USER+"("+ T_USER_ID +"))");

//TABLE_PHYSICALINFO Creation
        db.execSQL("CREATE TABLE " + TABLE_PHYSICALINFO + " (" +
                T_PHYSICALINFO_USERID+" INTEGER," +
                T_PHYSICALINFO_HEIGHT+" INTEGER," +
                T_PHYSICALINFO_WEIGHT+" FLOAT," +
                "FOREIGN KEY("+T_PHYSICALINFO_USERID+") REFERENCES "+ TABLE_USER+"("+ T_USER_ID+"))");

//TABLE_PHRASE Creation
        db.execSQL("CREATE TABLE " + TABLE_PHRASE + " (" +
                T_PHRASE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                T_PHRASE_PHRASE+" TEXT," +
                T_PHRASE_DESCRIPTION+" TEXT," +
                "FOREIGN KEY ("+ T_PHRASE_ID+") REFERENCES "+TABLE_USER+"("+T_USER_ID+"))");

//TABLE_SPORTPLAN Creation
        db.execSQL("CREATE TABLE " + TABLE_SPORTPLAN + " (" +
                T_SPORTPLAN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                T_SPORTPLAN_USERID+" INTEGER," +
                T_SPORTPLAN_CREATORID +" TEXT," +
                T_SPORTPLAN_TOTALDURATION+" TEXT," +
                T_SPORTPLAN_SUBMITIONDATE+" TEXT," +
                T_SPORTPLAN_STARTTIME+" TEXT," +
                T_SPORTPLAN_TITLE+" TEXT," +
                T_SPORTPLAN_FINISHTIME+" TEXT," +
                T_SPORTPLAN_REPEATTYPE+" TEXT," +
                "FOREIGN KEY("+T_SPORTPLAN_USERID+") REFERENCES "+TABLE_USER+"("+T_USER_ID+"))");

//TABLE_SPORTITEMS Creation
        db.execSQL("CREATE TABLE " + TABLE_SPORTITEMS + " (" +
                T_SPORTITEMS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                T_SPORTITEMS_ORDER+" TEXT," +
                T_SPORTITEMS_SPID+" TEXT," +
                T_SPORTITEMS_ISREST+" TEXT," +
                T_SPORTITEMS_DURATION+" TEXT," +
                T_SPORTITEMS_NAME+" TEXT," +
                "FOREIGN KEY("+T_SPORTITEMS_SPID+") REFERENCES "+TABLE_SPORTPLAN+"("+T_SPORTPLAN_ID+"))");

//TABLE_DOINGSPORTPLAN Creation
        db.execSQL("CREATE TABLE " + TABLE_DOINGSPORTPLAN + " (" +
                T_DOINGSPORTPLAN_SPID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                T_DOINGSPORTPLAN_SPIID+" INTEGER," +
                T_DOINGSPORTPLAN_STATUS+" TEXT," +
                T_DOINGSPORTPLAN_FINISHTIME+" TEXT," +
                T_DOINGSPORTPLAN_PERCENTAGE+" TEXT," +
                T_DOINGSPORTPLAN_STARTTIME+" DATE," +
                "FOREIGN KEY ("+T_DOINGSPORTPLAN_SPID+")REFERENCES "+TABLE_SPORTPLAN+"("+T_SPORTPLAN_ID+"),"+
                "FOREIGN KEY ("+T_DOINGSPORTPLAN_SPIID+")REFERENCES "+TABLE_SPORTITEMS+"("+T_SPORTITEMS_ID+"))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHYSICALINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHRASE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERPROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPORTPLAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPORTITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOINGSPORTPLAN);
        onCreate(db);
    }

    public long profileDB_personalInfo(String userid,String firstname,String lastname,String dob,String city){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //Log.d("UserProfile add","userid: "+userid+" fname: "+firstname+" lastname: "+lastname+" dob: "+dob+" city: "+city);
        contentValues.put(T_USERPROFILE_USERID,userid);
        contentValues.put(T_USERPROFILE_FIRSTNAME,firstname);
        contentValues.put(T_USERPROFILE_LASTNAME,lastname);
        contentValues.put(T_USERPROFILE_DOB,dob);
        contentValues.put(T_USERPROFILE_CITY,city);
        long result = db.insert(TABLE_USERPROFILE, null, contentValues);
        return result;
    }

    public long profileDB_corporealInfo(String userid,String height,String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T_PHYSICALINFO_USERID,userid);
        contentValues.put(T_PHYSICALINFO_HEIGHT,height);
        contentValues.put(T_PHYSICALINFO_WEIGHT,weight);
        long result = db.insert(TABLE_PHYSICALINFO, null, contentValues);
        return result;
    }

    public byte signupDB(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM [" + TABLE_USER + "] WHERE " + T_USER_EMAIL + "='" + email + "'", null);
        if (res.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(T_USER_EMAIL, email);
            contentValues.put(T_USER_PASSWORD, password);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
            String date = sdf.format(new Date());
            contentValues.put(T_USER_REGISTRATIONDATE, date);
            long result = db.insert(TABLE_USER, null, contentValues);

            if (result == -1) {
                //data not inserted
                return 0;
            }

            else {
                //data inserted
                //Log.d("SQLITE", "DATA INSERTED: Email: " + email + " Password: " + password);
                return 1;
            }

        }
        else {
            //email is exists
            return 2;
        }

    }

    public byte addtoDB(Context context,String id, String username,String email,String phonenumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        int sum0=0;
        int sum1=0;
        int sum2=0;
        Cursor res0 = db.rawQuery("SELECT * FROM [" + TABLE_USER + "] WHERE " + T_USER_USERNAME + "='" + username + "'", null);
        if (res0.getCount() == 0) {
            sum0++;
        }
        else{
            Toast.makeText(context,"نام کاربری تکراری است!",Toast.LENGTH_SHORT).show();
        }
        Cursor res1=db.rawQuery("SELECT * FROM [" + TABLE_USER + "] WHERE " + T_USER_EMAIL + "='" + email + "'", null);
        if (res1.getCount()==0){
            sum1++;
        }
        else{
            Toast.makeText(context,"ایمیل تکراری است!",Toast.LENGTH_SHORT).show();
        }
        Cursor res2=db.rawQuery("SELECT * FROM [" + TABLE_USER + "] WHERE " + T_USER_PHONENUMBER + "='" + email + "'", null);
        if (res1.getCount()==0){
            sum2++;
        }
        else{
            Toast.makeText(context,"شماره تلفن همراه تکراری است!",Toast.LENGTH_SHORT).show();
        }
        if(sum0==1 && sum1==1 && sum2==1){
            ContentValues contentValues = new ContentValues();
            contentValues.put(T_USER_EMAIL, email);
            contentValues.put(T_USER_USERNAME, username);
            contentValues.put(T_USER_PHONENUMBER, phonenumber);
            long result=db.insert(TABLE_USER,null,contentValues);
            if (result == -1) {

                return 0;
            }

            else {
                return 1;
            }
        }
        else {
            return 2;
        }
    }

    public boolean forgetPasswordDB(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM [" + TABLE_USER + "] WHERE " + T_USER_EMAIL + "='" + email + "'", null);
        if(res.getCount()>0)    {return true;}
        else {return false;}
    }

    public byte loginDB(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM [" + TABLE_USER + "] WHERE " + T_USER_EMAIL + "='" + email + "'", null);
        res.moveToFirst();
        //Log.d("Db Count",Integer.toString(res.getCount()));
        //Log.d("test",res.getString(3).toString());
        //Log.d("pass",password);
        if (res.getCount() > 0) {
            if (password.equals(res.getString(3).toString())) {
                //username and password are true
                return 1;
            } else {//username or password in incorrect
                return 0;
            }
        }
        else {
            //nothing found
            return 2;
        }
    }

    public Cursor profileDB_personalInfo_retrieve(String id){
        Log.d("database Helper...",id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM ["+TABLE_USERPROFILE+"] WHERE "+T_USERPROFILE_USERID+"='"+id+"';",null);
        return res;
    }

    public Cursor profileDB_corporealInfo_retrieve(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM ["+TABLE_PHYSICALINFO+"] WHERE "+T_PHYSICALINFO_USERID+"='"+id+"';",null);
        return res;
    }

    public String getId_from_email(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT ["+ T_USER_ID+"] FROM ["+TABLE_USER+"] WHERE "+T_USER_EMAIL+"='"+email+"';",null);
        res.moveToFirst();
        return res.getString(0).toString();
    }

    public Cursor retrieve_User_Class_Info(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM ["+TABLE_USER+"] WHERE "+T_USER_ID+"='"+id+"';",null);
        return res;
    }

    public Cursor viewAll(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM [" + TABLE_USER + "]", null);
        return res;
    }
}
