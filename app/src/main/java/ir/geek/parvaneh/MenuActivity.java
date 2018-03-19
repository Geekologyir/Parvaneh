package ir.geek.parvaneh;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MenuActivity extends AppCompatActivity {

    Button document;
    Button spplan;
    Button fdplan;
    Button ability;
    Button sptest;
    Button terminology;
    Button massage;
    Button bodybld;
    List<Button> btns;

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

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);

        btns = new ArrayList<Button>();
        document=(Button)findViewById(R.id.document);
        btns.add(document);
        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,documentActivity.class));
            }
        });
        spplan=(Button)findViewById(R.id.spplan);
        btns.add(spplan);
        spplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SportPlanActivity.class));
            }
        });

        fdplan=(Button)findViewById(R.id.fdplan);
        btns.add(fdplan);
        fdplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,FoodPlanActivity.class));
            }
        });

        ability=(Button)findViewById(R.id.ability);
        btns.add(ability);
        ability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,AbilityActivity.class));
            }
        });

        sptest=(Button)findViewById(R.id.sptest);
        btns.add(sptest);
        sptest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SportTestActivity.class));
            }
        });

        terminology=(Button)findViewById(R.id.terminology);
        btns.add(terminology);
        terminology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,TerminologyActivity.class));
            }
        });

        massage=(Button)findViewById(R.id.massage);
        btns.add(massage);
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,MassageActivity.class));
            }
        });

        bodybld=(Button)findViewById(R.id.bodybld);
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
        int gridWidth = displayMetrics.widthPixels ;
        ViewGroup.LayoutParams params;
        for (Button btn:btns) {
            params = btn.getLayoutParams();
            params.width = gridWidth/2;
            params.height = gridWidth/2;
            btn.setLayoutParams(params);
        }







    }
}
