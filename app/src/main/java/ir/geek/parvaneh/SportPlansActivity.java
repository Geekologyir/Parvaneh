package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SportPlansActivity extends AppCompatActivity {
    TextView title;
    ListView spList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;

    String[] my_items = { "1","2","3","4","5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportplans);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        setTitle("");
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(getText(R.string.activity_sportplans_title));
        changeActionBar(getString(R.string.activity_sportplans_title));
        spList = (ListView) findViewById(R.id.spList);
        spList.setAdapter(new SpListAdapter(this,android.R.layout.simple_list_item_1,
                R.id.sp_item_subject,
                my_items));
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

            /*String stringName = "string_key_" + String.valueOf(position + 1);
            int string_res_ID = getResources().getIdentifier(stringName, "string", getPackageName());
            String my_string = getResources().getString(string_res_ID);
            TextView tv = (TextView) row.findViewById(R.id.textView1);
            tv.setText(my_string);

            String imageName = "key_" + String.valueOf(position + 1);
            int image_res_ID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            ImageView iv = (ImageView) row.findViewById(R.id.imageView1);
            iv.setImageResource(image_res_ID);*/
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
    private void changeActionBar(String titleText) {
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        setTitle("");
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
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
