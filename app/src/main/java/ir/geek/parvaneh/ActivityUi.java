package ir.geek.parvaneh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityUi {
    Context context;
    AppCompatActivity activity;

    public ActivityUi(Context context) {
        this.context = context;
        activity = (AppCompatActivity) context;
    }

    public static int dpFromPx(final Context context, final int px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static int pxFromDp(final Context context, final int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public void changeActionBar(String titleText, final Class previousActivity, Boolean hasDrawer) {

        View row = (View) activity.findViewById(R.id.container);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.my_toolbar);

        activity.setSupportActionBar(toolbar);
        activity.setTitle("");
        TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
        title.setText(titleText);


        if (previousActivity != null) {
            ImageView back = new ImageView(context);
            Toolbar.LayoutParams params2 = new Toolbar.LayoutParams(44 * (int) context.getResources().getDisplayMetrics().density, 44 * (int) context.getResources().getDisplayMetrics().density);
            params2.gravity = Gravity.END;
            params2.leftMargin = 20 * (int) context.getResources().getDisplayMetrics().density;
            back.setLayoutParams(params2);
            back.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_back));
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, previousActivity));
                }
            });

            toolbar.addView(back);
        }

        if (hasDrawer) {
            ActionBar actionbar = activity.getSupportActionBar();
            final DrawerLayout mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
            ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle((Activity) context, mDrawerLayout, R.string.open, R.string.close);
            mDrawerLayout.addDrawerListener(mToogle);

            prepareDrawerMenu();
        }
    }

    public void addViewToToolbar(View view) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.my_toolbar);
        toolbar.addView(view);
    }

    private void prepareDrawerMenu() {
        final ListView list = (ListView) activity.findViewById(R.id.list_menu_items);
        list.setAdapter(new Drawmenu(context, android.R.layout.simple_list_item_1, R.id.drawermenu_item, context.getResources().getStringArray(R.array.drawermenu)));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        context.startActivity(new Intent(context, ProfileActivity.class));
                        break;
                }

            }
        });
    }

    private class Drawmenu extends ArrayAdapter<String> {

        public Drawmenu(Context context, int resource, int textViewResourceId,
                        String[] strings) {
            super(context, resource, textViewResourceId, strings);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.drawermenu_item, parent, false);

            TextView drawermenu_item = (TextView) row.findViewById(R.id.drawermenu_item);
            drawermenu_item.setText(context.getResources().getStringArray(R.array.drawermenu)[position]);

            return row;
        }


    }
}
