package ir.geek.parvaneh.dataClasses;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import ir.geek.parvaneh.DatabaseHelper;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

/**
 * Created by Zahra on 4/15/2018.
 */

public class UserProfile {
    String firstName, lastName, city;
    Date dob;

    public UserProfile(String id, Context context) {
        //ToDo: get profile from db select firstName from profile where id=id
        DatabaseHelper p_db = new DatabaseHelper(context);

        try {
            Cursor userprofiledb = p_db.profileDB_personalInfo_retrieve(id);
            userprofiledb.moveToFirst();
            firstName = userprofiledb.getString(1);
            lastName = userprofiledb.getString(2);
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(userprofiledb.getString(3));
            city = userprofiledb.getString(4);
        } catch (Exception e) {
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();

        }


    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return new PersianDateFormat("Y-m-d").format(new PersianDate(dob));
    }

    public void update(String userid, String firstname, String lastname, String dob, String city) {

        try {
            this.firstName = firstname;
            this.lastName = lastname;
            this.city=city;
            this.dob = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        } catch (Exception e) {
        }
    }
}
