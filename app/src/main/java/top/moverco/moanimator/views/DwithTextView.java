package top.moverco.moanimator.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by liuzongxiang on 24/05/2017.
 */

public class DwithTextView extends View {
    private ImageView mImageView;
    private TextView mTextView;
    private Path mPath;
    private Paint imagPaint;
    private Paint textPaint;
    private Bitmap mBitmap;
    private float posX,posY;
    private float currentValue;

    public ImageView getImageView(){
        return mImageView;
    }
    public TextView getTextView(){
        return mTextView;
    }
    public DwithTextView(Context context) {
        super(context);
        imagPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        imagPaint.setStyle(Paint.Style.STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.STROKE);
        mImageView = new ImageView(context);
        mTextView = new TextView(context);
        mPath = new Path();
    }

    public DwithTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        imagPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        imagPaint.setStyle(Paint.Style.STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mImageView = new ImageView(context);
        mTextView = new TextView(context);
        mPath = new Path();
        mTextView.setTextSize(30);


    }

    public DwithTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(100,100);
        mPath.quadTo(200,200,100,200);
        canvas.drawPath(mPath,imagPaint);
        canvas.drawText("TAG",posX,posY,textPaint);

    }
    public void onClick(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                float t = 1 - currentValue;
                posX = t*t*100+2*t*currentValue*100+currentValue*currentValue*200;
                posY = t*t*100+2*t*currentValue*200+currentValue*currentValue*200;
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
