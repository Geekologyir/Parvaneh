package ir.geek.parvaneh.dataClass;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zahra on 4/15/2018.
 */

public class User {
    String username;
    String email;
    String phone_number;
    Date registeraion_date;
    User_profile profile;

    public String getUsername() {
        return username;
    }

    public User(int id){
        // ToDo : get user info from db. select  username from Users where id=id
         if(id==1){
             username="zahra";
             email="zahraabhari76@gmail.com";
             phone_number="123456";
             try {
                 registeraion_date=new SimpleDateFormat("yyyy-MM-dd").parse("2018-4-25");
             }
             catch (Exception e){

             }
             profile=new User_profile(id);


         }
        else if(id==2){
            username="ali";
            email="zahraabhari76@gmail.com";
            phone_number="98562";
             try {
                 registeraion_date=new SimpleDateFormat("yyyy-MM-dd").parse("2018-4-25");
             }
             catch (Exception e){

             }
             profile=new User_profile(id);

        }
        else{
             username="vahid";
             email="zahraabhari76@gmail.com";
             phone_number="4567";
             try {
                 registeraion_date=new SimpleDateFormat("yyyy-MM-dd").parse("2018-4-25");
             }
             catch (Exception e){

             }
             profile=new User_profile(id);
        }

    }
}
