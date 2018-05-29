package ir.geek.parvaneh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ir.geek.parvaneh.dataClasses.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupActivity extends AppCompatActivity {
    User user;

    private UserSignUpTask mAuthTask = null;

    private TextView loginLink;
    private Button Signup_Btn;
    private EditText email_et, password_et, confirm_et;
    private View mProgressView;

    DatabaseHelper p_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        p_db = new DatabaseHelper(this);

        email_et = (EditText) findViewById(R.id.email);
        password_et = (EditText) findViewById(R.id.password);
        confirm_et = (EditText) findViewById(R.id.passwordConfirm);
        Signup_Btn = (Button) findViewById(R.id.signupBtn);

        loginLink = (TextView) findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));

            }
        });

        mProgressView = findViewById(R.id.signup_progress);

        signup();
        if (getIntent().getExtras() != null) {
            email_et.setText(getIntent().getExtras().getString("Email"));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void signup() {
        Signup_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignup();
            }
        });
    }

    private void attemptSignup() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        email_et.setError(null);
        password_et.setError(null);
        confirm_et.setError(null);

        // Store values at the time of the activity_login attempt.
        String email = email_et.getText().toString();
        String password = password_et.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            password_et.setError(getString(R.string.error_empty_password));
            focusView = password_et;
            cancel = true;
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            password_et.setError(getString(R.string.error_invalid_password));
            focusView = password_et;
            cancel = true;
        } else if (!(password_et.getText().toString().equals(confirm_et.getText().toString()))) {
            confirm_et.setError(getString(R.string.error_invalid_confirm_password));
            focusView = confirm_et;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            email_et.setError(getString(R.string.error_field_required));
            focusView = email_et;
            cancel = true;
        } else if (!isEmailValid(email)) {
            email_et.setError(getString(R.string.error_invalid_email));
            focusView = email_et;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt activity_login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user activity_login attempt.
            showProgress(true);
            mAuthTask = new UserSignUpTask(email, password);
            mAuthTask.execute((Void) null);
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return (password.length() > 4);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
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

    /**
     * Represents an asynchronous activity_login/registration task used to authenticate
     * the user.
     */
    public class UserSignUpTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private String userId;

        UserSignUpTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(500);
                user= new User();
                String result = user.signUp(mEmail, mPassword, getApplicationContext());

                //byte result =p_db.signupDB(email_et.getText().toString(),password_et.getText().toString());
                if (result == null) {
                    //Toast.makeText(SignupActivity.this, "عملیات با بروز مشکل مواجه شده است، لطفا مجددا امتحان نمایید.", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    //Toast.makeText(SignupActivity.this, "ثبت نام با موفقت انجام شد.", Toast.LENGTH_SHORT).show();
                    //user = new User(email_et.getText().toString(),password_et.getText().toString());
                    userId = result;
                    return true;
                }
            } catch (InterruptedException e) {
                return false;
            }
            /*byte res=p_db.signup(mEmail,mPassword);
            //Log.d("ali",Byte.toString(res));

            if(res==0){
                return false;
            }
            else if(res==1){
                return true;
            }
            else if(res==2){
                return false;
            }

            return false;*/
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Toast.makeText(SignupActivity.this, "وارد شد", Toast.LENGTH_SHORT).show();
                addToSharedPreferences(userId);
                startActivity(new Intent(SignupActivity.this, MenuActivity.class));
                finish();

            } else {
                /*mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();*/
                Toast.makeText(SignupActivity.this, getString(R.string.login_error), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    public void addToSharedPreferences(String userid) {
        SharedPreferences shPref;

        String MyPref = "MyPrefers";
        String shEmail = "shEmail";
        String shId = "shId";

        shPref = getSharedPreferences(MyPref, Context.MODE_PRIVATE);

        SharedPreferences.Editor sEdit = shPref.edit();
        sEdit.putString(shEmail, email_et.getText().toString());
        String id = userid;
        sEdit.putString(shId, id);
        sEdit.commit();

        Toast.makeText(SignupActivity.this, shPref.getString(shEmail, null) + "\n" + shPref.getString(shId, null), Toast.LENGTH_LONG).show();
    }


}
