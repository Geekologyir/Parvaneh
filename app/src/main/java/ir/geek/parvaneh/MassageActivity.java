package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import ir.geek.parvaneh.dataClasses.Massage;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MassageActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback {
    int massageId;
    Massage massage;

    android.support.v7.widget.Toolbar toolbar;
    TextView difficultyView;
    TextView durationView;
    TextView descriptionView;

    DrawerLayout mDrawerLayout;
    Context context;

    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";

    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;

    View mBottomLayout;
    View mVideoLayout;
    RelativeLayout mStart;

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage);

        retrieveData();
        initializeViews();


    }

    private void retrieveData() {
        massageId = Integer.parseInt(getIntent().getExtras().getString("id"));
       // Toast.makeText(getApplicationContext(), massageId + "", Toast.LENGTH_SHORT).show();
        massage = new Massage(massageId, null);
    }

    private void initializeViews() {
        context = getApplicationContext();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        changeActionBar(massage.getTitle());


        difficultyView = (TextView) findViewById(R.id.difficulty);
        switch (massage.getDifficulty()) {
            case 0:
                difficultyView.setText(getString(R.string.easy));
                break;
            case 1:
                difficultyView.setText(getString(R.string.medium));
                break;
            case 2:
                difficultyView.setText(getString(R.string.hard));
                break;
        }
        durationView = (TextView) findViewById(R.id.videoDuration);
        durationView.setText(massage.getDuration() + " " + getString(R.string.minutes));
        descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(massage.getDescription());

        mVideoLayout = findViewById(R.id.video_layout);
        mBottomLayout = findViewById(R.id.bottom_layout);
        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        mStart = (RelativeLayout) findViewById(R.id.playOverlay);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart.setVisibility(View.GONE);
                if (mSeekPosition > 0) {
                    mVideoView.seekTo(mSeekPosition);
                }
                mVideoView.start();
            }
        });
        mMediaController.setTitle(massage.getTitle());
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion ");
            }
        });
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void changeActionBar(String titleText) {
        setSupportActionBar(toolbar);
        setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);

        toolbar.setElevation(0);

        ImageView back = new ImageView(this);
        Toolbar.LayoutParams params2 = new Toolbar.LayoutParams(44 * (int) context.getResources().getDisplayMetrics().density, 44 * (int) context.getResources().getDisplayMetrics().density);
        params2.gravity = Gravity.END;
        params2.leftMargin = 20 * (int) context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params2);
        back.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MassageActivity.this, MassagesActivity.class));
            }
        });
        toolbar.addView(back);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.GONE);
            toolbar.setVisibility(View.GONE);
            if (mVideoView.isPlaying())
                mStart.setVisibility(View.GONE);

        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);

        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {

        if (show) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPause UniversalVideoView callback");
        mStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            mVideoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Position=" + mVideoView.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
        Log.d(TAG, "onRestoreInstanceState Position=" + mSeekPosition);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause ");
        if (mVideoView != null && mVideoView.isPlaying()) {
            mStart.setVisibility(View.VISIBLE);
            mSeekPosition = mVideoView.getCurrentPosition();
            Log.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
            mVideoView.pause();
        }
    }

    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath("/sdcard/parvaneh/massages/videos/" + massage.getVideoFileName());
                mVideoView.requestFocus();
            }
        });
    }
}