package top.moverco.moanimator.bezier;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liuzongxiang on 23/05/2017.
 */

public class SecondBezierView extends View {
    private Paint bezierPaint;
    private Paint mFlagPaint;
    private Paint mTextPaint;

    public SecondBezierView(Context context) {
        super(context);
        bezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bezierPaint.setStrokeWidth(8);
        bezierPaint.setStyle(Paint.Style.STROKE);

    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bezierPaint.setStrokeWidth(8);
        bezierPaint.setStyle(Paint.Style.STROKE);

        mFlagPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFlagPaint.setStrokeWidth(3);
        mFlagPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(20);
        mTextPaint.setStyle(Paint.Style.STROKE);
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private float mStartPointX;
    private float mStartPointY;
    private float mEndPointX;
    private float mEndPointY;
    private float mFlagPointX;
    private float mFlagPointY;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 3 / 4;
        mEndPointY = h / 2 - 200;

        mFlagPointX = w / 2;
        mFlagPointY = h / 2 - 300;
        mPath = new Path();


    }

    private Path mPath;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);
        mPath.quadTo(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY);

        canvas.drawPoint(mStartPointX,mStartPointY,mFlagPaint);
        canvas.drawText("start",mStartPointX,mStartPointY,mTextPaint);
        canvas.drawPoint(mEndPointX,mEndPointY,mFlagPaint);
        canvas.drawText("end",mEndPointX,mEndPointY,mTextPaint);
        canvas.drawPoint(mFlagPointX,mFlagPointY,mFlagPaint);
        canvas.drawText("flag",mFlagPointX,mFlagPointY,mTextPaint);

        canvas.drawLine(mStartPointX,mStartPointY,mFlagPointX,mFlagPointY,mFlagPaint);
        canvas.drawLine(mEndPointX,mEndPointY,mFlagPointX,mFlagPointY,mFlagPaint);

        canvas.drawPath(mPath, bezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mFlagPointX = event.getX();
                mFlagPointY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
