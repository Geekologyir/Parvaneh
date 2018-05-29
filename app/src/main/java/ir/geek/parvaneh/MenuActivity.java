package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MenuActivity extends AppCompatActivity {

    RelativeLayout document;
    RelativeLayout spplan;
    RelativeLayout fdplan;
    RelativeLayout talent;
    RelativeLayout sptest;
    RelativeLayout terminology;
    RelativeLayout massage;
    RelativeLayout bodybld;
    List<RelativeLayout> btns;

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //SharedPreferences shpref=getSharedPreferences(SplashActivity.MyPref,Context.MODE_PRIVATE);

        //Toast.makeText(MenuActivity.this,shpref.getString(LoginActivity.shEmail,null),Toast.LENGTH_LONG).show();


        ActivityUi activityUi = new ActivityUi(this);
        activityUi.changeActionBar(getString(R.string.activity_menu_title),null,true);


        btns = new ArrayList<RelativeLayout>();
        document = (RelativeLayout) findViewById(R.id.document);

        btns.add(document);
        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, ProfileActivity.class));

            }
        });
        spplan = (RelativeLayout) findViewById(R.id.spplan);
        btns.add(spplan);
        spplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SportPlansActivity.class));

            }
        });

        fdplan = (RelativeLayout) findViewById(R.id.fdplan);
        btns.add(fdplan);
        fdplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, FoodPlanActivity.class));

            }
        });

        talent = (RelativeLayout) findViewById(R.id.talent);
        btns.add(talent);
        talent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, AbilityActivity.class));

            }
        });

        sptest = (RelativeLayout) findViewById(R.id.sptest);
        btns.add(sptest);
        sptest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SportTestsActivity.class));

            }
        });

        terminology = (RelativeLayout) findViewById(R.id.terminology);
        btns.add(terminology);
        terminology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, TerminologyActivity.class));

            }
        });

        massage = (RelativeLayout) findViewById(R.id.massage);
        btns.add(massage);
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, MassagesActivity.class));

            }
        });

        bodybld = (RelativeLayout) findViewById(R.id.bodybld);
        btns.add(bodybld);
        bodybld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, BodyBuildActivity.class));

            }
        });


        GridLayout gridLayout = (GridLayout) findViewById(R.id.menuGrid);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = activityUi.dpFromPx(MenuActivity.this, displayMetrics.widthPixels);
        ViewGroup.LayoutParams params;
        for (RelativeLayout btn : btns) {
            params = btn.getLayoutParams();
            params.width = activityUi.pxFromDp(MenuActivity.this, ((deviceWidth - 40) / 2) - 18);
            params.height = activityUi.pxFromDp(MenuActivity.this, ((deviceWidth - 40) / 2) - 18);
            btn.setLayoutParams(params);
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
}