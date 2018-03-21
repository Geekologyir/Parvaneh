package ir.geek.parvaneh;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class CalligraphyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iransans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
