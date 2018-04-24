package ir.geek.parvaneh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;


public class SplashActivity extends Activity {
    RelativeLayout layout;
    public static final String MyPref = "MyPrefers";
    public static final String shEmail = "shEmail";
    public static final String shId = "shId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Make Activity Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        layout = (RelativeLayout) findViewById(R.id.activity_splash);
        //Run IntroActivity after activity_splash
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                ///IntroActivity : the activity that runs after a few seconds
                //startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 1500); // time based on ms

    }

/*
    public static void setName(String nameArg) {
        s = nameArg;
        globalId = nameArg;
    }
*/

}
