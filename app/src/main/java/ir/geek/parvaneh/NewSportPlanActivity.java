package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewSportPlanActivity extends AppCompatActivity {
    TextView title;
    EditText time , finishTime;
    ImageView datePicker , datePicker2;
    GridLayout times;
    List<Button> timesBtns;
    Button selectedTimes;
    PersianCalendar persianCalendar;
    String dateInput,timeInput;

    Context context;

    RelativeLayout finishTimeLayout;
    DragListView mDragListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sport_plan);

        initializeViews();
        handleClicks();

        changeActionBar(getString(R.string.activity_new_sportplan_title));

        showFragment();


    }
    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drag_list_container, DragListFragment.newInstance(), "fragment").commit();
    }
    private void changeActionBar(String titleText) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

        TextView doneBtn = new TextView(this);
        doneBtn.setText(getString(R.string.done));
        doneBtn.setTextColor(getColor(R.color.link));
        doneBtn.setTextSize(18);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo : check errors and save data

                startActivity(new Intent(NewSportPlanActivity.this, SportPlansActivity.class));
                finish();
            }
        });
        toolbar.addView(doneBtn);

        ImageView back = new ImageView(this);
        Toolbar.LayoutParams params2 = new Toolbar.LayoutParams(40 * (int)context.getResources().getDisplayMetrics().density,40 * (int)context.getResources().getDisplayMetrics().density);
        params2.gravity= Gravity.END;
        params2.leftMargin= 20 * (int)context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params2);
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewSportPlanActivity.this, SportPlansActivity.class));
            }
        });
        toolbar.addView(back);
    }
    private void initializeViews() {
        time = (EditText) findViewById(R.id.time);
        datePicker = (ImageView) findViewById(R.id.new_sp_datepicker_btn);
        finishTime = (EditText) findViewById(R.id.finish_time);
        datePicker2 = (ImageView) findViewById(R.id.new_sp_datepicker_btn2);
        persianCalendar = new PersianCalendar();

        context = getApplicationContext();

        timesBtns=new ArrayList<Button>();
        timesBtns.add((Button)findViewById(R.id.times_1));
        timesBtns.add((Button)findViewById(R.id.times_2));
        timesBtns.add((Button)findViewById(R.id.times_3));
        timesBtns.add((Button)findViewById(R.id.times_4));

        finishTimeLayout = (RelativeLayout) findViewById(R.id.finish_time_input_block);

        selectedTimes = timesBtns.get(0); // ToDo : Add condition to get from defined intent parameters for edit mode.
        selectedTimes.setBackground(getDrawable(R.drawable.selecectedtextinputbg));
        selectedTimes.setTextColor(getColor(R.color.colorAccent));
        if (selectedTimes.getId()==R.id.times_1){
            finishTimeLayout.setVisibility(View.GONE);
        } else {
            finishTimeLayout.setVisibility(View.VISIBLE);
        }



    }
    private void handleClicks() {
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker(time);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker(time);
            }
        });
        datePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker(finishTime);
            }
        });
        finishTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker(finishTime);
            }
        });

        for(final Button b : timesBtns){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (b.getId()==R.id.times_1){
                        finishTimeLayout.setVisibility(View.GONE);
                    } else {
                        finishTimeLayout.setVisibility(View.VISIBLE);
                    }
                    for(Button btn : timesBtns) {
                        btn.setBackground(getDrawable(R.drawable.textinputbg));
                        btn.setTextColor(getColor(R.color.grey));
                    }
                    b.setBackground(getDrawable(R.drawable.selecectedtextinputbg));
                    b.setTextColor(getColor(R.color.colorAccent));
                    selectedTimes = b;

                }
            });
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void callDatePicker(final View obj) {
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
                        callTimePicker((EditText)obj);
                    }
                },
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    private void callTimePicker(final EditText obj) {

        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                        if(minute<10){
                            timeInput = hourOfDay + ":0" + minute;
                        }else {
                            timeInput = hourOfDay + ":" + minute;
                        }
                        obj.setText(dateInput + " , ساعت  " + timeInput);
                    }
                },
                persianCalendar.get(PersianCalendar.HOUR_OF_DAY),
                persianCalendar.get(PersianCalendar.MINUTE),
                true
        );
        timePickerDialog.show(getFragmentManager(), "Timepickerdialog");

    }

}
