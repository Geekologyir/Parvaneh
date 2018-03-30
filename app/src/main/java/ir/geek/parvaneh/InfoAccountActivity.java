package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
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

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InfoAccountActivity extends AppCompatActivity {

    EditText user_name,email,phone;
    List<EditText> accountFields;
    TextView edite_account , edite_password;
    Context context;
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);

        initializeViews();
        retrieveData();
        setClickHanlders();
        changeActionBar("اطلاعات حساب کاربری" );
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
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
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
        user_name = (EditText) findViewById(R.id.user_name);
        accountFields.add(user_name);
        email = (EditText) findViewById(R.id.email);
        accountFields.add(email);
        phone = (EditText) findViewById(R.id.phone);
        accountFields.add(phone);

        edite_account = (TextView) findViewById(R.id.edite_account);
        edite_password = (TextView) findViewById(R.id.edite_password);

    }

    private void retrieveData() {
        // ToDo : Change the values from db to make app dynimic
        user_name.setText("زهرا");
        email.setText("zahraabhari76@gmail.com");
        phone.setText("09199749769");
    }

    private void setClickHanlders() {
        edite_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText e : accountFields) {
                    e.setFocusable(true);
                    e.setFocusableInTouchMode(true);
                    e.requestFocus();
                    e.setClickable(true);
                }

                edite_account.setText(getString(R.string.done));
                edite_account.setTextColor(getColor(R.color.link));
                edite_account.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // ToDo : Save changes in db
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });

        edite_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoAccountActivity.this,Edite_passwordActivity.class));
            }
        });
    }
}
