package ir.geek.parvaneh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class squareLayout extends RelativeLayout {
    public squareLayout(Context context) {
        super(context);
    }

    public squareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public squareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec == heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }

    }
}