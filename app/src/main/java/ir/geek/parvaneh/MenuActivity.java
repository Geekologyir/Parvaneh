package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    private ActionBarDrawerToggle mToogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setTitle("");

        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);

        btns = new ArrayList<RelativeLayout>();
        document=(RelativeLayout)findViewById(R.id.document);

        btns.add(document);
        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,documentActivity.class));
            }
        });
        spplan=(RelativeLayout)findViewById(R.id.spplan);
        btns.add(spplan);
        spplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SportPlanActivity.class));
            }
        });

        fdplan=(RelativeLayout)findViewById(R.id.fdplan);
        btns.add(fdplan);
        fdplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,FoodPlanActivity.class));
            }
        });

        talent=(RelativeLayout)findViewById(R.id.talent);
        btns.add(talent);
        talent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,AbilityActivity.class));
            }
        });

        sptest=(RelativeLayout)findViewById(R.id.sptest);
        btns.add(sptest);
        sptest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SportTestActivity.class));
            }
        });

        terminology=(RelativeLayout)findViewById(R.id.terminology);
        btns.add(terminology);
        terminology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,TerminologyActivity.class));
            }
        });

        massage=(RelativeLayout)findViewById(R.id.massage);
        btns.add(massage);
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,MassageActivity.class));
            }
        });

        bodybld=(RelativeLayout)findViewById(R.id.bodybld);
        btns.add(bodybld);
        bodybld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,BodyBuildActivity.class));
            }
        });


        GridLayout gridLayout = (GridLayout) findViewById(R.id.menuGrid);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels ;
        ViewGroup.LayoutParams params;
        for (RelativeLayout btn:btns) {
            params = btn.getLayoutParams();
            params.width = (deviceWidth / 2) - 20;
            params.height = (deviceWidth / 2) - 20;
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
                if (!mDrawerLayout.isDrawerOpen(GravityCompat.END)){
                    mDrawerLayout.openDrawer(GravityCompat.END);
                    return true;
                }else{
                    mDrawerLayout.closeDrawer(GravityCompat.END);
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

}
