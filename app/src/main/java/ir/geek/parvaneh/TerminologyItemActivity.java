package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TerminologyItemActivity extends AppCompatActivity {
    Context context;
    ActivityUi activityUi;

    boolean isBookmarked;
    TextView title, body;

    String testTitle = "ورزش هوازی";
    String testBody = "تمرین هوازی (ایروبیک) (به انگلیسی: Aerobic exercise) نوعی فعالیت ورزشی بوده که هدف آن بهبود سیستم مصرف اکسیژن می باشد. هوازی ترجمه واژه ایروبیک (Aerobic) می باشد که به معنی با اکسیژن بوده و به نیاز به اکسیژن در سوخت و ساز بدن و فرایند تولید انرژی اشاره دارد. تمرین های هوازی بسیاری وجود دارند که با شدتی ملایم و بصورت مداوم انجام می شوند.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminology_item);

        initializeViews();

        retrieveData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void initializeViews(){
        context = getApplicationContext();
        activityUi = new ActivityUi(this);
        activityUi.changeActionBar("",TerminologyActivity.class,false);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setElevation(0);

        /*---------- Start Bookmark -------------*/
        final ImageView bookmark = new ImageView(this);
        Toolbar.LayoutParams params1 = new Toolbar.LayoutParams(40 * (int) context.getResources().getDisplayMetrics().density, 40 * (int) context.getResources().getDisplayMetrics().density);
        params1.gravity = Gravity.START;
        bookmark.setLayoutParams(params1);
        if (isBookmarked) {
            bookmark.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.bookmark_1));
        } else {
            bookmark.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.bookmark));
        }
        Runnable myRun = new Runnable() {
            @Override
            public void run() {
                bookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isBookmarked) {
                            // ToDo : debookmark
                            bookmark.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.bookmark));
                            isBookmarked = false;
                        } else {
                            // ToDo : bookmark
                            bookmark.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.bookmark_1));
                            isBookmarked = true;
                        }
                    }
                });
            }
        };
        new Thread(myRun).run();
        activityUi.addViewToToolbar(bookmark);

        /*---------- End Bookmark -------------*/

        title = (TextView) findViewById(R.id.expression_name);
        body = (TextView) findViewById(R.id.expression_body);
    }
    private void retrieveData(){
        // ToDo : Get date from db !
        title.setText(testTitle);
        body.setText(testBody);
        isBookmarked = true;
    }
}
