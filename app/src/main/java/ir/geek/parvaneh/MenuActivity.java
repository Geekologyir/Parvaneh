package ir.geek.parvaneh;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {

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

        Button document=(Button)findViewById(R.id.document);
        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,documentActivity.class));
            }
        });
        Button spplan=(Button)findViewById(R.id.spplan);
        spplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SportPlanActivity.class));
            }
        });

        Button fdplan=(Button)findViewById(R.id.fdplan);
        fdplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,FoodPlanActivity.class));
            }
        });

        Button ability=(Button)findViewById(R.id.ability);
        ability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,AbilityActivity.class));
            }
        });

        Button sptest=(Button)findViewById(R.id.sptest);
        sptest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SportTestActivity.class));
            }
        });

        Button terminology=(Button)findViewById(R.id.terminology);
        terminology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,TerminologyActivity.class));
            }
        });

        Button massage=(Button)findViewById(R.id.massage);
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,MassageActivity.class));
            }
        });

        Button bodybld=(Button)findViewById(R.id.bodybld);
        bodybld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,BodyBuildActivity.class));
            }
        });
    }
}
