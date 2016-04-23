package pb.tuyademo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ColorView extends View {

    private int titleBackgroundColor = Color.WHITE;

    private Paint mPaint;

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final Resources.Theme theme = context.getTheme();
        TypedArray a = theme.obtainStyledAttributes(attrs,
                R.styleable.ColorView, defStyleAttr, 0);
        if (null != a) {
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.ColorView_cv_color:
                        titleBackgroundColor = a.getColor(attr, Color.RED);
                        break;
                }
            }
            a.recycle();
            init();
        }
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(titleBackgroundColor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2f, getWidth() / 2f, getWidth() / 2f, mPaint);
    }
}