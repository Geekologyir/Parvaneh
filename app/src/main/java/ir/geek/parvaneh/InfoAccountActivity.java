package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.geek.parvaneh.dataClasses.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InfoAccountActivity extends AppCompatActivity {
    String userId;
    User user;

    EditText username,email,phone;
    List<EditText> accountFields;
    TextView editAccount, editPassword;
    Context context;
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);

        initializeViews();
        retrieveData();
        setClickHanlders();
        changeActionBar(getString(R.string.account_info_title) );
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void changeActionBar(String titleText) {
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);

        ImageView back = new ImageView(this);
        Toolbar.LayoutParams params2 = new Toolbar.LayoutParams(44 * (int) context.getResources().getDisplayMetrics().density, 44 * (int) context.getResources().getDisplayMetrics().density);
        params2.gravity = Gravity.END;
        params2.leftMargin = 20 * (int) context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params2);
        back.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoAccountActivity.this, ProfileActivity.class));
            }
        });

        toolbar.addView(back);
    }
    private void initializeViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        context = getApplicationContext();
        accountFields = new ArrayList<EditText>();
        username = (EditText) findViewById(R.id.username);
        accountFields.add(username);
        email = (EditText) findViewById(R.id.email);
        accountFields.add(email);
        phone = (EditText) findViewById(R.id.phone);
        accountFields.add(phone);

        editAccount = (TextView) findViewById(R.id.edit_account);
        editPassword = (TextView) findViewById(R.id.edit_password);

    }

    private void retrieveData() {
        userId = "1";
        user = new User(userId,context);
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
    }

    private void setClickHanlders() {
        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText e : accountFields) {
                    e.setFocusable(true);
                    e.setFocusableInTouchMode(true);
                    e.requestFocus();
                    e.setClickable(true);
                }

                editAccount.setText(getString(R.string.done));
                editAccount.setTextColor(ContextCompat.getColor(context, R.color.link));
                editAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // ToDo : Save changes in db
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });

        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoAccountActivity.this,EditPasswordActivity.class));
            }
        });
    }
}
