package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MassagesActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Context context;
    ImageView massage1;
    ImageView massage2;
    ImageView massage3;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massages);

        initializeViews();
        changeActionBar(getString(R.string.massage_learning));
        handleClicks();
    }

    private void initializeViews(){
        context = getApplicationContext();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        massage1=(ImageView)findViewById(R.id.massage1);
        massage2=(ImageView)findViewById(R.id.massage2);
        massage3=(ImageView)findViewById(R.id.massage3);

        gridLayout= (GridLayout) findViewById(R.id.items);
    }
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);

        ImageView back = new ImageView(this);
        Toolbar.LayoutParams params2 = new Toolbar.LayoutParams(44 * (int) context.getResources().getDisplayMetrics().density, 44 * (int) context.getResources().getDisplayMetrics().density);
        params2.gravity = Gravity.END;
        params2.leftMargin = 20 * (int) context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params2);
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MassagesActivity.this, MenuActivity.class));
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
        for(int i=0;i< gridLayout.getChildCount();i++){
            gridLayout.getChildAt(i).setOnClickListener(itemsClick);
        }
    }
    View.OnClickListener itemsClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            squareLayout layout = (squareLayout) view;
            TextView titleView = (TextView) layout.getChildAt(1);
            Intent intent = new Intent(context,MassageActivity.class);
            intent.putExtra("title", titleView.getText().toString());
            startActivity(intent);
        }
    };



}

