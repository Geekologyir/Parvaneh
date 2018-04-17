package ir.geek.parvaneh.dataClass;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zahra on 4/15/2018.
 */

public class User_profile {
    String firstname, lastname,city;
    Date dob;
    public User_profile(int id){
        //ToDo: get profile from db select firstname from profile where id=id
        if(id==1){
            firstname="zahra";
            lastname="zahraabhari76@gmail.com";
            city="123456";
            try {
                dob=new SimpleDateFormat("yyyy-MM-dd").parse("1997-08-25");
            }
            catch (Exception e){}


        }
        else if(id==2){
            firstname="ali";
            lastname="zahraabhari76@gmail.com";
            city="98562";
            try {
                dob=new SimpleDateFormat("yyyy-MM-dd").parse("1997-08-25");
            }
            catch (Exception e){}

        }
        else if(id==3){
            firstname="vahid";
            lastname="zahraabhari76@gmail.com";
            city="4567";
            try {
                dob=new SimpleDateFormat("yyyy-MM-dd").parse("1997-08-25");
            }
            catch (Exception e){}
        }
    }

}
