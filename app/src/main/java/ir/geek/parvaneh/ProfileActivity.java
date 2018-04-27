package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.geek.parvaneh.dataClasses.User;
import saman.zamani.persiandate.PersianDate;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    EditText fName, lName, city, dob, height, weight;
    List<EditText> personalFields, corporealFields;
    TextView editPersonal, editCorporeal, edite_show_account;
    private PersianCalendar persianCalendar;
    private String date;
    DatabaseHelper p_db = new DatabaseHelper(this);
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
         id= User.getInstance().getShId(getSharedPreferences("MyPrefers", Context.MODE_PRIVATE));
        initializeViews();
        retrieveData(id);
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
        android.support.v7.widget.Toolbar.LayoutParams params = new android.support.v7.widget.Toolbar.LayoutParams(44 * (int) context.getResources().getDisplayMetrics().density, 44 * (int) context.getResources().getDisplayMetrics().density);
        params.gravity = Gravity.END;
        params.leftMargin = 20 * (int) context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params);
        back.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MenuActivity.class));
            }
        });
        toolbar.addView(back);
    }

    private void initializeViews() {
        context = getApplicationContext();
        personalFields = new ArrayList<EditText>();
        fName = (EditText) findViewById(R.id.Fname_input);
        personalFields.add(fName);
        lName = (EditText) findViewById(R.id.Lname_input);
        personalFields.add(lName);
        city = (EditText) findViewById(R.id.city);
        personalFields.add(city);
        dob = (EditText) findViewById(R.id.DOB);
        personalFields.add(dob);

        corporealFields = new ArrayList<EditText>();
        height = (EditText) findViewById(R.id.height_input);
        corporealFields.add(height);
        weight = (EditText) findViewById(R.id.weight_input);
        corporealFields.add(weight);

        editPersonal = (TextView) findViewById(R.id.personal_info_block_edit);
        edite_show_account = (TextView) findViewById(R.id.edite_show_account);
        editCorporeal = (TextView) findViewById(R.id.corporeal_info_edit);
    }

    private void retrieveData(String id) {
        // ToDo : Change the values from db to make app dynamic
        profile_retrieve_personal_info(id);
        profile_retrieve_corporeal_info(id);

   /*     fName.setText("عباس");
        lName.setText("پروانه");
        dob.setText("6 مرداد 1372");
        city.setText("شازند");
        height.setText("180");
        weight.setText("75");*/
    }

    private void profile_retrieve_personal_info(String id) {
        Cursor p_cursor = p_db.profileDB_personalInfo_retrieve(id);
        String a = Integer.toString(p_cursor.getCount());
        Toast.makeText(ProfileActivity.this, a, Toast.LENGTH_SHORT).show();
        if (p_cursor.getCount() != 0) {
            p_cursor.moveToFirst();
            p_cursor.getString(0);
            fName.setText(p_cursor.getString(1).toString());//نام
            lName.setText(p_cursor.getString(2).toString());//نام خانوادگی
            dob.setText(p_cursor.getString(3).toString());//تاریخ تولد
            city.setText(p_cursor.getString(4).toString());//شهر
        }
    }

    private void profile_retrieve_corporeal_info(String id) {
        Cursor p_cursor = p_db.profileDB_corporealInfo_retrieve(id);
        if (p_cursor.getCount() != 0) {
            p_cursor.moveToFirst();
            p_cursor.getString(0);
            weight.setText(p_cursor.getString(1).toString());//نام
            height.setText(p_cursor.getString(2).toString());//نام خانوادگی

        }
    }

    private void setClickHanlders() {
        editPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText e : personalFields) {
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
                editPersonal.setTextColor(ContextCompat.getColor(context, R.color.link));
                editPersonal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        p_db.profileDB_personalInfo(id,fName.getText().toString(),lName.getText().toString(),dbdate,city.getText().toString());
                        // ToDo : Save changes in db personal information
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });

        editCorporeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText e : corporealFields) {
                    e.setFocusable(true);
                    e.setFocusableInTouchMode(true);
                    e.requestFocus();
                    e.setClickable(true);
                }
                editCorporeal.setText(getString(R.string.done));
                editCorporeal.setTextColor(ContextCompat.getColor(context, R.color.link));
                editCorporeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        p_db.profileDB_corporealInfo(id,height.getText().toString(),weight.getText().toString());
                        // ToDo : Save changes in db corporeal information
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });

        edite_show_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, InfoAccountActivity.class));
            }
        });
    }
    String dbdate;
    private void getDate() {
        final String[] persianMonths = {"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
        persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        date = dayOfMonth + " " + persianMonths[monthOfYear] + " " + year;
                        String monthtmp=null;
                        if(monthOfYear<10){
                            monthtmp="0"+Integer.toString(monthOfYear);
                        }
                        //dbdate=Integer.toString(year)+"-"+monthtmp+"-"+Integer.toString(dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        dbdate= sdf.format(toGregorian(year,monthOfYear,dayOfMonth));

                        dob.setText(date);
                    }
                },
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );

        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    private Date toGregorian(int shYear , int shMonth , int shDay){

        PersianDate shDate = new PersianDate();
        shDate.setShYear(shYear);
        shDate.setShMonth(shMonth+1);
        shDate.setShDay(shDay);

        return shDate.toDate();
    }


    private String getShEmail() {
        String MyPref = "MyPrefers";
        String shEmail = "shEmail";
        String shId = "shId";
        SharedPreferences shPref;
        shPref = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        return shPref.getString(shEmail, null);
    }
    /*private String getShId() {
        String MyPref = "MyPrefers";
        String shEmail = "shEmail";
        String shId = "shId";
        SharedPreferences shPref;
        shPref = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        return shPref.getString(shId, null);
    }*/
}
