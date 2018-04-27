package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ir.geek.parvaneh.dataClasses.SportTest;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SportTestsActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    Context context;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_tests);

        initializeViews();
        changeActionBar(getString(R.string.activity_sporttests_title));
        handleClicks();
    }
    private void initializeViews(){
        context = getApplicationContext();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        gridView= (GridView) findViewById(R.id.itemsGrid);
        gridView.setAdapter(new itemAdapter(context,android.R.layout.simple_list_item_1,R.id.itemImage,list));
    }
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void changeActionBar(String titleText) {
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);



        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);

        ImageView back = new ImageView(this);
        Toolbar.LayoutParams params2 = new Toolbar.LayoutParams(44 * (int) context.getResources().getDisplayMetrics().density, 44 * (int) context.getResources().getDisplayMetrics().density);
        params2.gravity = Gravity.END;
        params2.leftMargin = 20 * (int) context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params2);
        back.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportTestsActivity.this, MenuActivity.class));
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

    private void handleClicks() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                squareLayout layout = (squareLayout) view;
                TextView titleView = (TextView) layout.getChildAt(1);
                Intent intent = new Intent(context,SportTestActivity.class);
                intent.putExtra("id", layout.getTag().toString());
                startActivity(intent);
                finish();
            }
        });
    }
    private class itemAdapter extends ArrayAdapter<Integer> {
        List<Integer> idList;
        public itemAdapter(Context context, int resource, int textViewResourceId,
                           List<Integer> ids) {
            super(context, resource, textViewResourceId, ids);
            idList = ids;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.massages_item, parent, false);


            SportTest sportTest = new SportTest(idList.get(position));

            TextView itemTitle = (TextView) row.findViewById(R.id.itemTitle);
            itemTitle.setText(sportTest.getTitle());

            File imgFile = new File("/sdcard/parvaneh/sporttest/images/"+sportTest.getImageFileName());
            if(imgFile.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                ImageView itemImage = (ImageView) row.findViewById(R.id.itemImage);
                itemImage.setImageBitmap(bitmap);
            }

            row.setTag(idList.get(position));
            return row;
        }
    }
}
