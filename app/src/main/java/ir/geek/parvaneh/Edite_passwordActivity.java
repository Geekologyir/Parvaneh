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

public class Edite_passwordActivity extends AppCompatActivity {

    EditText current_pass,new_pass,verify;
    List<EditText> passwordFields;
    TextView edite_password;
    Context context;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_password);

        initializeViews();
        retrieveData();
        setClickHanlders();
        changeActionBar("تغییر رمز ورود" );
    }

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
                startActivity(new Intent(Edite_passwordActivity.this, InfoAccountActivity.class));
            }
        });

        toolbar.addView(back);
    }
    private void initializeViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        context = getApplicationContext();
        passwordFields = new ArrayList<EditText>();
        current_pass = (EditText) findViewById(R.id.current_pass);
        passwordFields.add(current_pass);
        new_pass = (EditText) findViewById(R.id.new_pass);
        passwordFields.add(new_pass);
        verify = (EditText) findViewById(R.id.verify);
        passwordFields.add(verify);


        edite_password = (TextView) findViewById(R.id.edite_password);

    }

    private void retrieveData() {
        // ToDo : Change the values from db to make app dynimic
        current_pass.setText("123456");
        new_pass.setText("78954");
        verify.setText("78945");
    }

    private void setClickHanlders() {
        edite_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText e : passwordFields) {
                    e.setFocusable(true);
                    e.setFocusableInTouchMode(true);
                    e.requestFocus();
                    e.setClickable(true);
                }

                edite_password.setText(getString(R.string.done));
                edite_password.setTextColor(getColor(R.color.link));
                edite_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // ToDo : Save changes in db
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });
    }

}
