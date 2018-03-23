package ir.geek.parvaneh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
public class TerminologyActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    EditText searchBox;
    ListView queryList;

    String[] words = {"ورزش","هوازی","بدنسازی","ورزش هوازی","ورزش بدنسازی"};
    List<String> queries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminology);

        initializeViews();
        changeActionBar(getString(R.string.activity_terminology_title));


        queries = Arrays.asList(words);

        setAdapterList(queries);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String word = searchBox.getText().toString();
                queries = new ArrayList<>();
                for(String w : words){
                    if (w.contains(word)) {
                        queries.add(w);
                    }
                }

                setAdapterList(queries);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void initializeViews(){
        searchBox = (EditText) findViewById(R.id.search_box);
        queryList = (ListView) findViewById(R.id.queries);
        queries = new ArrayList<String>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
    }
    private void setAdapterList(List<String> source){
        queryList.setAdapter(new ArrayAdapter<String>(TerminologyActivity.this,
                android.R.layout.simple_list_item_1, source));
        queryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(TerminologyActivity.this,TerminologyItemActivity.class));
                finish();
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
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
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

}
