package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
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

    EditText username, email, phone;
    List<EditText> accountFields;
    TextView editAccount, editPassword;
    Context context;
    DrawerLayout mDrawerLayout;
    DatabaseHelper p_db = new DatabaseHelper(this);
    ActivityUi activityUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);

        initializeViews();
        retrieveData();
        setClickHanlders();

    }

    private void initializeViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        context = getApplicationContext();
        activityUi = new ActivityUi(this);
        activityUi.changeActionBar(getString(R.string.change_password_title), ProfileActivity.class, true);

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
        user = new User(userId, context);
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

                        startActivity(getIntent());

                    }
                });
            }
        });

        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoAccountActivity.this, EditPasswordActivity.class));

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
