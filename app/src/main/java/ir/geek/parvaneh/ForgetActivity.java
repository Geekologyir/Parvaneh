package ir.geek.parvaneh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ForgetActivity extends AppCompatActivity {
    DatabaseHelper p_db= new DatabaseHelper(this);
    private TextView loginLink;
    private Button forget_btn;
    private EditText email_et;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        email_et = (EditText) findViewById(R.id.email_et);
        forget_btn =(Button)findViewById(R.id.forgetBtn);
        loginLink = (TextView) findViewById(R.id.loginLink);
        mProgressView = findViewById(R.id.forget_progress);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetActivity.this,LoginActivity.class));

            }
        });
        forgetPassword();
        if (getIntent().getExtras() != null) {
            email_et.setText(getIntent().getExtras().getString("Email"));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }


    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            /*mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });*/

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            //mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    public void forgetPassword(){
        forget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isEmailValid(email_et.getText().toString())){
                    Toast.makeText(ForgetActivity.this, getString(R.string.error_invalid_email), Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = p_db.forgetPasswordDB(email_et.getText().toString());
                    if (result == true) {
                        Toast.makeText(ForgetActivity.this, "کد بازیابی به " + email_et.getText().toString() + " ارسال شد.", Toast.LENGTH_SHORT).show();
                        //TODO:سیستم ارسال ایمیل به آدرس ایمیل مورد نظر
                        ((RelativeLayout) findViewById(R.id.forget_form)).setVisibility(View.GONE);
                        ((RelativeLayout) findViewById(R.id.verify_form)).setVisibility(View.VISIBLE);
                    } else if (result == false) {
                        //TODO:Check Connection to Database
                        Toast.makeText(ForgetActivity.this, getString(R.string.email_not_found), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
