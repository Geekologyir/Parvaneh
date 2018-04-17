package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupActivity extends AppCompatActivity {

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
                byte result =p_db.signupDB(email_et.getText().toString(),password_et.getText().toString());
                if (result == 1) {
                    Toast.makeText(SignupActivity.this,"ثبت نام با موفقت انجام شد.َ",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this,MenuActivity.class));
                    finish();
                }
                else if(result==0){
                    Toast.makeText(SignupActivity.this,"عملیات با بروز مشکل مواجه شده است، لطفا مجددا امتحان نمایید.",Toast.LENGTH_SHORT).show();
                }
                else if(result==2){
                    Toast.makeText(SignupActivity.this,"ایمیل تکراری است.لطفا ایمیل دیگری را امتحان کنید.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
