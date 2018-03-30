package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {
    Context context;
    EditText fName , lName,city ,dob , height , weight;
    List<EditText> personalFields , corporealFields;
    TextView editPersonal , editCorporeal ,edite_show_account;
    private PersianCalendar persianCalendar;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        retrieveData();
        setClickHanlders();
        changeActionBar(getString(R.string.profile_title));

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void changeActionBar(String titleText) {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);

        ImageView back = new ImageView(this);
        android.support.v7.widget.Toolbar.LayoutParams params = new android.support.v7.widget.Toolbar.LayoutParams(44 * (int)context.getResources().getDisplayMetrics().density,44 * (int)context.getResources().getDisplayMetrics().density);
        params.gravity= Gravity.END;
        params.leftMargin= 20 * (int)context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params);
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MenuActivity.class));
            }
        });
        toolbar.addView(back);
    }
    private void initializeViews(){
        context = getApplicationContext();
        personalFields= new ArrayList<EditText>();
        fName = (EditText) findViewById(R.id.Fname_input);
        personalFields.add(fName);
        lName = (EditText) findViewById(R.id.Lname_input);
        personalFields.add(lName);
        city = (EditText) findViewById(R.id.city);
        personalFields.add(city);
        dob = (EditText) findViewById(R.id.DOB);
        personalFields.add(dob);

        corporealFields= new ArrayList<EditText>();
        height = (EditText) findViewById(R.id.height_input);
        corporealFields.add(height);
        weight = (EditText) findViewById(R.id.weight_input);
        corporealFields.add(weight);

        editPersonal = (TextView) findViewById(R.id.personal_info_block_edit);
        edite_show_account = (TextView) findViewById(R.id.edite_show_account);
        editCorporeal = (TextView) findViewById(R.id.corporeal_info_edit);
    }
    private void retrieveData(){
        // ToDo : Change the values from db to make app dynimic
        fName.setText("عباس");
        lName.setText("پروانه");
        city.setText("شازند");
        dob.setText("6 مرداد 1372");
        height.setText("180");
        weight.setText("75");
    }
    private void setClickHanlders(){
        editPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText e : personalFields){
                    e.setFocusable(true);
                    e.setFocusableInTouchMode(true);
                    e.requestFocus();
                    e.setClickable(true);
                }
                dob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDate();
                    }
                });
                editPersonal.setText(getString(R.string.done));
                editPersonal.setTextColor(getColor(R.color.link));
                editPersonal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // ToDo : Save changes in db
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });
        editCorporeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText e : corporealFields){
                    e.setFocusable(true);
                    e.setFocusableInTouchMode(true);
                    e.requestFocus();
                    e.setClickable(true);
                }
                editCorporeal.setText(getString(R.string.done));
                editCorporeal.setTextColor(getColor(R.color.link));
                editCorporeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // ToDo : Save changes in db
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });

        edite_show_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,InfoAccountActivity.class));
            }
        });
    }
    private void getDate() {
        final String[] persianMonths = {"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
        persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        date = dayOfMonth + " " + persianMonths[monthOfYear] + " " + year;
                        dob.setText(date);
                    }
                },
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }
}
