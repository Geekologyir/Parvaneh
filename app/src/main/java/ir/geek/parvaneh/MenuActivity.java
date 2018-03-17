package ir.geek.parvaneh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
                startActivity(new Intent(MenuActivity.this,SportPlan.class));
            }
        });

        Button fdplan=(Button)findViewById(R.id.fdplan);
        fdplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,FoodPlan.class));
            }
        });

        Button ability=(Button)findViewById(R.id.ability);
        ability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,Ability.class));
            }
        });

        Button sptest=(Button)findViewById(R.id.sptest);
        sptest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SportTest.class));
            }
        });

        Button terminology=(Button)findViewById(R.id.terminology);
        terminology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,Terminology.class));
            }
        });

        Button massage=(Button)findViewById(R.id.massage);
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,Massage.class));
            }
        });

        Button bodybld=(Button)findViewById(R.id.bodybld);
        bodybld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,BodyBuild.class));
            }
        });
    }
}
