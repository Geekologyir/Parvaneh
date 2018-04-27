package ir.geek.parvaneh.dataClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zahra on 4/15/2018.
 */

public class User {
    String username;
    String email;
    String phoneNumber;
    Date registeraionDate;
    UserProfile profile;

    public String getUsername() {
        return username;
    }

    public User(int id){
        // ToDo : get user info from db. select  username from Users where id=id
         if(id==1){
             username="zahra";
             email="zahraabhari76@gmail.com";
             phoneNumber ="12346";
             try {
                 registeraionDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-4-25");
             }
             catch (Exception e){

             }
             profile=new UserProfile(id);


         }
        else if(id==2){
            username="ali";
            email="zahraabhari76@gmail.com";
            phoneNumber ="98562";
             try {
                 registeraionDate =new SimpleDateFormat("yyyy-MM-dd").parse("2018-4-25");
             }
             catch (Exception e){

             }
             profile=new UserProfile(id);

        }
        else{
             username="vahid";
             email="zahraabhari76@gmail.com";
             phoneNumber ="4567";
             try {
                 registeraionDate =new SimpleDateFormat("yyyy-MM-dd").parse("2018-4-25");
             }
             catch (Exception e){

             }
             profile=new UserProfile(id);
        }
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
}
