package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toolbar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SportPlansActivity extends AppCompatActivity {
    TextView title;
    ListView spList;
    Button newPlanBtn;
    Context context;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;

    String[] my_items = { "1","2","3","4","5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportplans);

        initializeViews();
        changeActionBar(getString(R.string.activity_sportplans_title));


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private class SpListAdapter extends ArrayAdapter<String> {

        public SpListAdapter(Context context, int resource, int textViewResourceId,
                         String[] strings) {
            super(context, resource, textViewResourceId, strings);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.sport_plans_item, parent, false);

            TextView edit = (TextView) row.findViewById(R.id.editBtn);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SportPlansActivity.this, NewSportPlanActivity.class));
                    finish();
                }
            });
            return row;
        }
    }
    private void initializeViews(){
        context = getApplicationContext();
        spList = (ListView) findViewById(R.id.spList);
        spList.setAdapter(new SpListAdapter(this,android.R.layout.simple_list_item_1,
                R.id.sp_item_subject,
                my_items));
        spList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // ToDo: Send sport plan id to next page
                startActivity(new Intent(SportPlansActivity.this,SportPlanActivity.class));
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
    private void changeActionBar(String titleText) {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        title = (TextView) findViewById(R.id.toolbar_title);
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
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
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

}
