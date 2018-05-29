package ir.geek.parvaneh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditPasswordActivity extends AppCompatActivity {

    EditText current_pass, new_pass, verify;
    TextView doneBtn;
    Context context;
    DrawerLayout mDrawerLayout;
    ActivityUi activityUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        initializeViews();
        setClickHanlders();
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initializeViews() {
        activityUi = new ActivityUi(context);
        activityUi.changeActionBar(getString(R.string.change_password_title),InfoAccountActivity.class,true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        context = getApplicationContext();
        current_pass = (EditText) findViewById(R.id.current_pass);
        new_pass = (EditText) findViewById(R.id.new_pass);
        verify = (EditText) findViewById(R.id.verify);
        doneBtn = (TextView) findViewById(R.id.done_btn);


    }

    private void setClickHanlders() {
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ToDo : Check and Save changes in db
                startActivity(new Intent(EditPasswordActivity.this, InfoAccountActivity.class));

            }
        });
    }

}
