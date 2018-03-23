package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    boolean isBookmarked=true;
    TextView title , body;

    String testTitle = "ورزش هوازی";
    String testBody = "تمرین هوازی (ایروبیک) (به انگلیسی: Aerobic exercise) نوعی فعالیت ورزشی بوده که هدف آن بهبود سیستم مصرف اکسیژن می باشد. هوازی ترجمه واژه ایروبیک (Aerobic) می باشد که به معنی با اکسیژن بوده و به نیاز به اکسیژن در سوخت و ساز بدن و فرایند تولید انرژی اشاره دارد. تمرین های هوازی بسیاری وجود دارند که با شدتی ملایم و بصورت مداوم انجام می شوند.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminology_item);
        context = getApplicationContext();
        changeActionBar();

        title = (TextView) findViewById(R.id.expression_name);
        body = (TextView) findViewById(R.id.expression_body);

        title.setText(testTitle);
        body.setText(testBody);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void changeActionBar() {
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText("");

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setElevation(0);
        toolbar.setBackgroundColor(Color.WHITE);

        ImageView bookmark = new ImageView(this);
        Toolbar.LayoutParams params1 = new Toolbar.LayoutParams(32,32);
        params1.gravity= Gravity.START;
        bookmark.setLayoutParams(params1);
        /*if(isBookmarked)
            bookmark.setImageDrawable();*/

        ImageView back = new ImageView(this);
        Toolbar.LayoutParams params2 = new Toolbar.LayoutParams(44 * (int)context.getResources().getDisplayMetrics().density,44 * (int)context.getResources().getDisplayMetrics().density);
        params2.gravity= Gravity.END;
        params2.leftMargin= 20 * (int)context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params2);
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TerminologyItemActivity.this,TerminologyActivity.class));
            }
        });
        toolbar.addView(back);
    }
}
