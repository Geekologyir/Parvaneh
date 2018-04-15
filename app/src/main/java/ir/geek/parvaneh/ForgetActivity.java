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

public class ForgetActivity extends AppCompatActivity {
    DatabaseHelper p_db= new DatabaseHelper(this);
    private TextView loginLink;
    private Button forget_btn;
    private EditText email_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        email_et = (EditText) findViewById(R.id.email_et);
        forget_btn =(Button)findViewById(R.id.forgetBtn);
        loginLink = (TextView) findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetActivity.this,LoginActivity.class));
                finish();
            }
        });
        forgetPassword();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void forgetPassword(){
        forget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result =p_db.forgetPasswordDB(email_et.getText().toString());
                
                if (result == true) {
                    Toast.makeText(ForgetActivity.this,"کد بازیابی به "+email_et.getText().toString()+" ارسال شد.",Toast.LENGTH_SHORT).show();
                    //TODO:سیستم ارسال ایمیل به آدرس ایمیل مورد نظر
                    startActivity(new Intent(ForgetActivity.this,MenuActivity.class));
                    finish();
                }
                else if(result==false){
                    //TODO:Check Connection to Database
                    Toast.makeText(ForgetActivity.this,"ارتباط برقرار نشد یا ایمیل مورد نظر یافت نشد",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
