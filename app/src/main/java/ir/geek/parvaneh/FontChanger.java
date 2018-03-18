package ir.geek.parvaneh;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FontChanger {
    private Context context;

    public FontChanger(Context current){
        this.context = current;
    }
    void changeAllViewsFonts(ViewGroup views, String fontName){
        Typeface face = Typeface.createFromAsset(context.getResources().getAssets(),"Font/" + fontName + ".ttf");

        for (int i =0;i<views.getChildCount();i++){
            if (views.getChildAt(i) instanceof TextView){
                TextView view= (TextView) views.getChildAt(i);
                view.setTypeface(face);
            }
            else if (views.getChildAt(i) instanceof Button){
                Button view= (Button) views.getChildAt(i);
                view.setTypeface(face);
            }
        }
    }
}
