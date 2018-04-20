package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.geek.parvaneh.dataClasses.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Req_CoachActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    EditText searchBox;
    ListView queryList;

    Context context;

    List<User> allusers ;
    List<User> result ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_coach);

        initializeViews();
        changeActionBar(getString(R.string.activity_terminology_title));


        ///queries = Arrays.asList(words);

        //setAdapterList(queries);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String word = searchBox.getText().toString();
                /*queries = new ArrayList<>();
                for(String w : words){
                    if (w.contains(word)) {
                        queries.add(w);
                    }
                }

                setAdapterList(queries);*/
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void initializeViews(){
        searchBox = (EditText) findViewById(R.id.search_box);
        queryList = (ListView) findViewById(R.id.queries);
        allusers=new ArrayList<User>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        context = getApplicationContext();
    }
    private void setAdapterList(List<String> source){
        queryList.setAdapter(new ArrayAdapter<String>(Req_CoachActivity.this,
                android.R.layout.simple_list_item_1, source));
        queryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //startActivity(new Intent(Req_CoachActivity.this,Req_coachItemActivity.class));
                //finish();
            }
        });
    }
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void changeActionBar(String titleText) {
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar));
        setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(titleText);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setElevation(0);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);

        ImageView back = new ImageView(this);
        android.support.v7.widget.Toolbar.LayoutParams params = new android.support.v7.widget.Toolbar.LayoutParams(44 * (int)context.getResources().getDisplayMetrics().density,44 * (int)context.getResources().getDisplayMetrics().density);
        params.gravity= Gravity.END;
        params.leftMargin= 20 * (int)context.getResources().getDisplayMetrics().density;
        back.setLayoutParams(params);
        back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Req_CoachActivity.this, MenuActivity.class));
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

    private void retviatData(){
        //ToDo: get alluser from db
        allusers= new ArrayList<User>();
        allusers.add(new User(1));
        allusers.add(new User(2));
        allusers.add(new User(3));

        String searchword=searchBox.getText().toString();
        for(User user:allusers){
            if( user.getUsername().contains(searchword)){
                result.add(user);
            }

        }



    }

    public class coachAdapter extends ArrayAdapter<User>{
        public coachAdapter(Context context, int resource, int textViewResourceId,
                     User[] users){
            super(context, resource, textViewResourceId,users);

        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            return super.getView(position, convertView, parent);
        }
    }
}
