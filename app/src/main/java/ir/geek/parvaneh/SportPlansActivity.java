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

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;


    List<Integer> itemIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportplans);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        userId = 1; // ToDo : Get validate user id

        initializer();
        changeActionBar(getString(R.string.activity_sportplans_title));


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initializer(){
        context = getApplicationContext();
        spList = (ListView) findViewById(R.id.spList);
        itemIds = getItemIds();
        spList.setAdapter(new SpListAdapter(this,android.R.layout.simple_list_item_1,
                R.id.exercises,
                itemIds));
        spList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SportPlansActivity.this,SportPlanActivity.class);
                intent.putExtra("spId",Integer.parseInt(view.getTag().toString()));
                startActivity(intent);
                finish();
            }
        });
        newPlanBtn = (Button) findViewById(R.id.new_sp_btn);
        newPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportPlansActivity.this, NewSportPlanActivity.class));
                finish();
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
    private void changeActionBar(String titleText) {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);


        ImageView back = new ImageView(this);
        android.support.v7.widget.Toolbar.LayoutParams params = new android.support.v7.widget.Toolbar.LayoutParams(44 * (int)context.getResources().getDisplayMetrics().density,44 * (int)context.getResources().getDisplayMetrics().density);
        params.gravity= Gravity.END;
        params.leftMargin= 20 * (int)context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params);
        back.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportPlansActivity.this, MenuActivity.class));
            }
        });
        toolbar.addView(back);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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
            /*title = new ArrayList<String>();
            nextTime = new ArrayList<String>();
            exercises = new ArrayList<String>();
            duration = new ArrayList<Integer>();

            for(String id : ids){
                SportPlan sportPlan = new SportPlan(Integer.parseInt(id));
                title.add(sportPlan.getTitle());
                nextTime.add(sportPlan.nextDate());
                exercises.add(sportPlan.getExercisesStr());
                duration.add(sportPlan.getDuration());
            }*/
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
            edit.setTag(itemIds.get(position));
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SportPlansActivity.this,NewSportPlanActivity.class);
                    intent.putExtra("spId",view.getTag().toString());
                    startActivity(intent);
                    finish();
                }
            });
            row.setTag(itemIds.get(position));
            return row;
        }
    }

}
