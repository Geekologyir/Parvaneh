package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.geek.parvaneh.dataClasses.SportPlan;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SportPlansActivity extends AppCompatActivity {
    int userId;
    List<String> title;
    List<String> nextTime;
    List<String> exercises = new ArrayList<String>();
    List<Integer> duration;

    ListView spList;
    Button newPlanBtn;
    Context context;
    ActivityUi activityUi;

    private DrawerLayout mDrawerLayout;


    List<Integer> itemIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportplans);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        userId = 1; // ToDo : Get validate user id

        initializer();


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initializer(){
        context = getApplicationContext();
        activityUi = new ActivityUi(this);
        activityUi.changeActionBar(getString(R.string.activity_sportplans_title),MenuActivity.class,true);

        spList = (ListView) findViewById(R.id.spList);
        spList.setAdapter(new SpListAdapter(this,android.R.layout.simple_list_item_1,
                R.id.exercises,
                getItemIds()));
        spList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SportPlansActivity.this,SportPlanActivity.class);
                intent.putExtra("spId",Integer.parseInt(view.getTag().toString()));
                startActivity(intent);
            }
        });
        newPlanBtn = (Button) findViewById(R.id.new_sp_btn);
        newPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportPlansActivity.this, NewSportPlanActivity.class));
            }
        });
    }
    private List<Integer> getItemIds(){
        List<Integer> ids = new ArrayList<Integer>();
        // ToDo : Select id from sport_plans where user_id = userId
        ids.add(1);
        ids.add(2);
        ids.add(3);

        return ids;
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

    private class SpListAdapter extends ArrayAdapter<Integer> {
        List<Integer> idList;
        public SpListAdapter(Context context, int resource, int textViewResourceId,
                         List<Integer> ids) {
            super(context, resource, textViewResourceId, ids);
            idList = ids;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.sport_plans_item, parent, false);

            SportPlan sportPlan = new SportPlan(idList.get(position));

            TextView titleView = (TextView) row.findViewById(R.id.title);
            titleView.setText(sportPlan.getTitle());
            TextView nextTimeView = (TextView) row.findViewById(R.id.nextTime);
            nextTimeView.setText(sportPlan.nextDate());
            TextView exercisesView = (TextView) row.findViewById(R.id.exercises);
            exercisesView.setText(sportPlan.getExercisesStr());
            TextView durationView = (TextView) row.findViewById(R.id.duration);
            durationView.setText(sportPlan.getDuration() + getString(R.string.minutes));

            TextView edit = (TextView) row.findViewById(R.id.editBtn);
            edit.setTag(idList.get(position));
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SportPlansActivity.this,NewSportPlanActivity.class);
                    intent.putExtra("spId",view.getTag().toString());
                    startActivity(intent);
                }
            });
            row.setTag(idList.get(position));
            return row;
        }
    }

}
