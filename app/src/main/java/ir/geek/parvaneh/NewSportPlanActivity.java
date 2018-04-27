package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ir.geek.parvaneh.dataClasses.SportPlan;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewSportPlanActivity extends AppCompatActivity {
    int spId;
    SportPlan sportPlan;

    EditText title;
    EditText starttime , finishTime;
    ImageView datePicker , datePicker2;
    GridLayout times;
    List<Button> timesBtns;
    Button selectedTimes;
    Button removeAtAll;
    PersianCalendar persianCalendar;
    String dateInput,timeInput;

    Context context;

    RelativeLayout finishTimeLayout;


    DragListView mDragListView;
    private ArrayList<Pair<Long, String>> mItemArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sport_plan);

        initializeViews();
        handleClicks();

        if(getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("spId"))
                spId = Integer.parseInt(getIntent().getExtras().getString("spId"));
                retrieveData();
                changeActionBar(getString(R.string.editplan) + " " + sportPlan.getTitle());
        }else {
            changeActionBar(getString(R.string.activity_new_sportplan_title));
        }

        showFragment();
    }
    public  void retrieveData(){
        sportPlan = new SportPlan(spId);

        title.setText(sportPlan.getTitle());

        starttime.setText(sportPlan.getStartDate());
        finishTime.setText(sportPlan.getFinishDate());

        //Reset selected time
        selectedTimes.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.textinputbg));
        selectedTimes.setTextColor(ContextCompat.getColor(context,R.color.grey));

        // Change selectedt time
        selectedTimes = timesBtns.get(sportPlan.getPeriodType());
        selectedTimes.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.selecectedtextinputbg));
        selectedTimes.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
        if (selectedTimes.getId()==R.id.times_1){
            finishTimeLayout.setVisibility(View.GONE);
        } else {
            finishTimeLayout.setVisibility(View.VISIBLE);
        }
    }
    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment myFrag = DragListFragment.newInstance();

        if(getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("spId")) {
                ArrayList<Integer> ids = new ArrayList<Integer>();
                //ToDo : Select Ids from sportplans where spId = spId
                ids.add(1);
                ids.add(2);
                ids.add(3);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("ids",ids);
                bundle.putString("type","SportPlanItem");
                myFrag.setArguments(bundle);
            }
        }
        transaction.replace(R.id.drag_list_container,myFrag, "sportPlanItem").commit();
    }
    private void changeActionBar(String titleText) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText(titleText);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

        TextView doneBtn = new TextView(this);
        doneBtn.setText(getString(R.string.done));
        doneBtn.setTextColor(ContextCompat.getColor(context,R.color.link));
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
        back.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewSportPlanActivity.this, SportPlansActivity.class));
            }
        });
        toolbar.addView(back);
    }
    private void initializeViews() {
        title = (EditText) findViewById(R.id.title);
        starttime = (EditText) findViewById(R.id.start_time);
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
        selectedTimes.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.selecectedtextinputbg));
        selectedTimes.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));

        removeAtAll = (Button) findViewById(R.id.removeSP);

    }
    private void handleClicks() {
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker(starttime);
            }
        });
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDatePicker(starttime);
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
                        btn.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.textinputbg));
                        btn.setTextColor(ContextCompat.getColor(context,R.color.grey));
                    }
                    b.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.selecectedtextinputbg));
                    b.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
                    selectedTimes = b;

                }
            });
        }

        removeAtAll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //ToDo : remove sportplan . delete from sportplans where id = spid
                //ToDo : remove items . delete from sportplan_items where spid = spid
                Toast.makeText(context,sportPlan.getTitle() + getString(R.string.removed),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,SportPlansActivity.class));
                finish();
                return false;
            }
        });
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
