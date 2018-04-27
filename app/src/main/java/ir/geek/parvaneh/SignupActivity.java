package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ir.geek.parvaneh.dataClasses.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupActivity extends AppCompatActivity {
    User user;

    private TextView loginLink;
    private Button Signup_Btn;
    private EditText email_et,password_et;

    DatabaseHelper p_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        p_db=new DatabaseHelper(this);

        email_et=(EditText)findViewById(R.id.email);
        password_et=(EditText)findViewById(R.id.password);
        Signup_Btn = (Button) findViewById(R.id.signupBtn);

        loginLink = (TextView) findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
        });

        signup();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void signup(){
        Signup_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = User.getInstance().signUp(email_et.getText().toString(),password_et.getText().toString(),SignupActivity.this);

                //byte result =p_db.signupDB(email_et.getText().toString(),password_et.getText().toString());
                if(result == "Duplicate"){
                    Toast.makeText(SignupActivity.this,"ایمیل تکراری است.لطفا ایمیل دیگری را امتحان کنید.",Toast.LENGTH_SHORT).show();
                } else if (result == null) {
                    Toast.makeText(SignupActivity.this,"عملیات با بروز مشکل مواجه شده است، لطفا مجددا امتحان نمایید.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this,"ثبت نام با موفقت انجام شد.",Toast.LENGTH_SHORT).show();
                    //user = new User(email_et.getText().toString(),password_et.getText().toString());
                    addToSharedPreferences(result);
                    startActivity(new Intent(SignupActivity.this,MenuActivity.class));
                    finish();
                }
            }
        });
    }
    public void addToSharedPreferences(String userid){
        SharedPreferences shPref;

        String MyPref = "MyPrefers";
        String shEmail = "shEmail";
        String shId = "shId";

        shPref = getSharedPreferences(MyPref,Context.MODE_PRIVATE);

        SharedPreferences.Editor sEdit = shPref.edit();
        sEdit.putString(shEmail,email_et.getText().toString());
        String id=userid;
        sEdit.putString(shId,id);
        sEdit.commit();

        Toast.makeText(SignupActivity.this,shPref.getString(shEmail,null)+"\n"+shPref.getString(shId,null),Toast.LENGTH_LONG).show();
    }




}
