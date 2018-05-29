package ir.geek.parvaneh.dataClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import ir.geek.parvaneh.DatabaseHelper;
import ir.geek.parvaneh.MenuActivity;
import ir.geek.parvaneh.R;
import ir.geek.parvaneh.SignupActivity;

/**
 * Created by Zahra on 4/15/2018.
 */

public class User {
    String username;
    String email;
    String phoneNumber;
    Date registeraionDate;
    UserProfile profile;
    DatabaseHelper p_db;

    private static User instance = new User();
    public String getUsername() {
        return username;
    }

    public static User getInstance() {
        return instance;
    }

    public User(){
        username="";
    }

    public User(String id,Context context){
        // get user info from db. select  username from Users where id=id
        DatabaseHelper p_db=new DatabaseHelper(context);

             try {
                 Cursor userdb=p_db.retrieve_User_Class_Info(id);
                 userdb.moveToFirst();
                 //Log.d("TEst",",,,,,,,,,,,,,,,,,,");
                 username=userdb.getString(2);
                 email=userdb.getString(1);
                 phoneNumber =userdb.getString(4);
                 registeraionDate = new SimpleDateFormat("yyyy-MM-dd").parse(userdb.getString(5));
             }

             catch (Exception e){
                 Toast.makeText(context,"Error!",Toast.LENGTH_SHORT).show();
             }

             profile=new UserProfile(id,context);

    }

    public User(String email,String password,Context context){
        p_db=new DatabaseHelper(context);

    }

    public UserProfile getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShId(SharedPreferences shPref) {
        //String MyPref = "";
        String shEmail = "shEmail";
        String shId = "shId";
        return shPref.getString(shId, null);
    }


    public String signUp(String email, String password, final Context context){
        DatabaseHelper p_db=new DatabaseHelper(context);
        byte result = p_db.signupDB(email,password);

        if (result == 1) {
                    //Toast.makeText(context,"ثبت نام با موفقت انجام شد.",Toast.LENGTH_SHORT).show();
            return p_db.getId_from_email(email);
        }
        else if(result==0){ //Fault
            return null;
        }
        else if(result==2){ //Duplicate
            return "Duplicate";
        }
        return null;
    }
}
