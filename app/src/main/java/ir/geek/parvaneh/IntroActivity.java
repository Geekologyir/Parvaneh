package ir.geek.parvaneh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IntroActivity extends Activity {

    FontChanger fontChanger;
    RelativeLayout layout;
    ViewPager viewPager;
    Button nextBtn, skipBtn;
    LinearLayout toolbar;
    TextView[] dots;
    int[] layouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_intro);

        layout = (RelativeLayout) findViewById(R.id.activity_intro);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar =(LinearLayout) findViewById(R.id.introToolbar);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        skipBtn = (Button) findViewById(R.id.skipBtn);

        layouts = new int[]{
                R.layout.intro1,
                R.layout.intro2,
                R.layout.intro3,
                R.layout.intro4
        };
        addButton(0);
        changeStatusBarColor();


        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(changeListener);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchHomeScreen();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current=getItem()+1;
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {
                    LaunchHomeScreen();
                }
            }
        });

        fontChanger=new FontChanger(this);
        fontChanger.changeAllViewsFonts(layout,"behdad");
    }
    void addButton(int current){
        dots = new TextView[layouts.length];

        toolbar.removeAllViews();
        for(int i=0; i< dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText("○");
            dots[i].setTextSize(20);
            dots[i].setTextColor(Color.WHITE);
            toolbar.addView(dots[i]);
        }
        if(dots.length>0) {
            dots[current].setText("●");
            dots[current].setTextColor(Color.WHITE);
        }

    }
    private int getItem() {
        return viewPager.getCurrentItem();
    }

    private void LaunchHomeScreen() {
        finish();
        startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addButton(position);

            if (position == layouts.length - 1) {
                nextBtn.setText(getString(R.string.start));
                skipBtn.setVisibility(View.GONE);


            } else {
                nextBtn.setText(getString(R.string.next));
                skipBtn.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
