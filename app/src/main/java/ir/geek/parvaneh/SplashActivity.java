package ir.geek.parvaneh;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make Activiy Fullscreen
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
                startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 1500); // time based on ms
    }
}
