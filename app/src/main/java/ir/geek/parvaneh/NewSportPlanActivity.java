package ir.geek.parvaneh;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.TypefaceHelper;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.nio.channels.CancelledKeyException;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewSportPlanActivity extends AppCompatActivity {
    TextView title;
    EditText time;
    ImageView datePicker;
    RadioGroup colorTags;
    RadioButton checkedTag;
    PersianCalendar persianCalendar;
    String dateInput,timeInput;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sport_plan);

        changeActionBar(getString(R.string.activity_new_sportplan_title));

        initializeViews();
        handleClicks();


    }
    private void changeActionBar(String titleText) {
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        setTitle("");
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
    }
    private void initializeViews() {
        time = (EditText) findViewById(R.id.time);
        datePicker = (ImageView) findViewById(R.id.new_sp_datepicker_btn);
        persianCalendar = new PersianCalendar();
        colorTags = (RadioGroup) findViewById(R.id.color_tag);
        checkedTag = (RadioButton) findViewById(colorTags.getCheckedRadioButtonId());
        ViewGroup.LayoutParams  params = checkedTag.getLayoutParams();
        params.width = getResources().getDimensionPixelSize(R.dimen.checked_size);
        params.height = getResources().getDimensionPixelSize(R.dimen.checked_size);
        checkedTag.setLayoutParams(params);
        colorTags.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkedTag = (RadioButton) findViewById(colorTags.getCheckedRadioButtonId());
                for (int j=0;j<radioGroup.getChildCount();j++) {
                    ViewGroup.LayoutParams  params = radioGroup.getChildAt(j).getLayoutParams();
                    params.width = getResources().getDimensionPixelSize(R.dimen.unchecked_size);
                    params.height = getResources().getDimensionPixelSize(R.dimen.unchecked_size);
                    radioGroup.getChildAt(j).setLayoutParams(params);
                }
                ViewGroup.LayoutParams  params = checkedTag.getLayoutParams();
                params.width = getResources().getDimensionPixelSize(R.dimen.checked_size);
                params.height = getResources().getDimensionPixelSize(R.dimen.checked_size);
                checkedTag.setLayoutParams(params);
            }
        });
    }
    private void handleClicks() {
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker();
            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void callDatePicker() {
        final String[] persianMonths = {"فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"};
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        int diffrentDate = dayOfMonth - persianCalendar.getPersianDay();
                        if(persianCalendar.getPersianYear() == year && persianCalendar.getPersianMonth() == monthOfYear && diffrentDate < 2 && diffrentDate >=0) {
                            if (diffrentDate == 0){
                                dateInput = getString(R.string.today);
                            }else {
                                dateInput = getString(R.string.tommorrow);
                            }
                        } else {
                            dateInput = dayOfMonth + " " + persianMonths[monthOfYear] + " " + year;
                        }
                        callTimePicker();
                    }
                },
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    private void callTimePicker() {

        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                        if(minute<10){
                            timeInput = hourOfDay + ":0" + minute;
                        }else {
                            timeInput = hourOfDay + ":" + minute;
                        }
                        time.setText(dateInput + " , ساعت  " + timeInput);
                    }
                },
                persianCalendar.get(PersianCalendar.HOUR_OF_DAY),
                persianCalendar.get(PersianCalendar.MINUTE),
                true
        );
        timePickerDialog.show(getFragmentManager(), "Timepickerdialog");

    }

}
