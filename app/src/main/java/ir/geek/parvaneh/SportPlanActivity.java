package ir.geek.parvaneh;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import ir.geek.parvaneh.dataClasses.SportPlan;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SportPlanActivity extends AppCompatActivity {
    int spid;

    SportPlan sportPlan;
    TextView durationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_plan);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initializeViews();
        showFragment();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void initializeViews() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("spId"))
                spid = getIntent().getExtras().getInt("spId");
        }
        sportPlan = new SportPlan(spid);
        durationView = (TextView) findViewById(R.id.duration);
        durationView.setText(sportPlan.getDuration() + " " + getString(R.string.minutes));
    }

    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment myFrag = DragListFragment.newInstance();


        ArrayList<Integer> ids = new ArrayList<Integer>();
        //Select Ids from sportplans where spId = spId
        ids.add(1);
        ids.add(2);
        ids.add(3);
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("ids", ids);
        bundle.putString("type", "SportPlanItem");
        myFrag.setArguments(bundle);

        transaction.replace(R.id.drag_list_container, myFrag, "sportPlanItem").commit();
    }
}
