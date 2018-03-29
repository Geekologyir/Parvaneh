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
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SportTestActivity extends AppCompatActivity {
    TextView txt1;
    TextView txtdescription;
    TextView txttest;
    TextView txtvideotime;
    VideoView video;
    DrawerLayout mDrawerLayout;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_test);
        initializeViews();
        changeActionBar(getString(R.string.activity_sporttests_title));
    }

    private void initializeViews(){
        txt1=(TextView)findViewById(R.id.txt1);
        txtdescription=(TextView)findViewById(R.id.txtdescription);
        txttest=(TextView)findViewById(R.id.txttest);
        txtvideotime=(TextView)findViewById(R.id.txtvideotime);
        video=(VideoView)findViewById(R.id.video);
        context = getApplicationContext();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        video.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.video1);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.start();
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
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportTestActivity.this, SportTestsActivity.class));
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
