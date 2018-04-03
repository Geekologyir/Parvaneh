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
    private Button del_btn;
    DatabaseHelper p_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        p_db=new DatabaseHelper(this);
        email_et=(EditText)findViewById(R.id.email);
        password_et=(EditText)findViewById(R.id.password);
        Signup_Btn = (Button) findViewById(R.id.signupBtn);
        del_btn=(Button) findViewById(R.id.DeleteDB);
        loginLink = (TextView) findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
        });
        deleteDb();
        signup();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void deleteDb(){
        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = SignupActivity.this;
                context.deleteDatabase("Project.db");
            }
        });
    }

    public void signup(){
        Signup_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result =p_db.signupDB(email_et.getText().toString(),password_et.getText().toString());
                if (result == true) {
                    Toast.makeText(SignupActivity.this,"ثبت نام با موفقت انجام شد.َ",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this,MenuActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(SignupActivity.this,"عملیات با بروز مشکل مواجه شده است، لطفا مجددا امتحان نمایید.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
