package ir.geek.parvaneh.dataClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

/**
 * Created by Zahra on 4/15/2018.
 */

public class UserProfile {
    String firstName, lastName,city;
    Date dob;
    public UserProfile(int id){
        //ToDo: get profile from db select firstName from profile where id=id
        if(id==1){
            firstName ="zahra";
            lastName ="zahraabhari76@gmail.com";
            city="123456";
            try {
                dob=new SimpleDateFormat("yyyy-MM-dd").parse("1997-08-25");
            }
            catch (Exception e){}


        }
        else if(id==2){
            firstName ="ali";
            lastName ="zahraabhari76@gmail.com";
            city="98562";
            try {
                dob=new SimpleDateFormat("yyyy-MM-dd").parse("1997-01-5");
            }
            catch (Exception e){}

        }
        else if(id==3){
            firstName ="vahid";
            lastName ="zahraabhari76@gmail.com";
            city="4567";
            try {
                dob=new SimpleDateFormat("yyyy-MM-dd").parse("1997-03-23");
            }
            catch (Exception e){}
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
}
