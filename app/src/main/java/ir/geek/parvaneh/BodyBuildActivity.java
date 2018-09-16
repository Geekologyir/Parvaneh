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

public class BodyBuildActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    Context context;
    ActivityUi activityUi;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_tests);

        initializeViews();
        handleClicks();
    }

    private void initializeViews() {
        context = getApplicationContext();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        activityUi = new ActivityUi(this);
        activityUi.changeActionBar(getString(R.string.activity_sporttests_title), MenuActivity.class, true);

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        gridView = (GridView) findViewById(R.id.itemsGrid);
        gridView.setAdapter(new itemAdapter(context, android.R.layout.simple_list_item_1, R.id.itemImage, list));
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
                Intent intent = new Intent(context, SportTestActivity.class);
                intent.putExtra("id", layout.getTag().toString());
                startActivity(intent);

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

            File imgFile = new File("/sdcard/parvaneh/sporttest/images/" + sportTest.getImageFileName());
            if (imgFile.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                ImageView itemImage = (ImageView) row.findViewById(R.id.itemImage);
                itemImage.setImageBitmap(bitmap);
            }

            row.setTag(idList.get(position));
            return row;
        }
    }
}
