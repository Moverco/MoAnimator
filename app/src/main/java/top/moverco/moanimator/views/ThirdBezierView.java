package top.moverco.moanimator.views;

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

public class ThirdBezierView extends View {
    private Paint bezierPaint;
    private Paint mFlagPaint;
    private Paint mTextPaint;

    public ThirdBezierView(Context context) {
        super(context);
        bezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bezierPaint.setStrokeWidth(8);
        bezierPaint.setStyle(Paint.Style.STROKE);

    }

    public ThirdBezierView(Context context, @Nullable AttributeSet attrs) {
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

    public ThirdBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ThirdBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private float mStartPointX;
    private float mStartPointY;
    private float mEndPointX;
    private float mEndPointY;
    private float mFlagPointX;
    private float mFlagPointY;
    private float mFlagPointM;
    private float mFlagPointN;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 3 / 4;
        mEndPointY = h / 2 - 200;

        mFlagPointX = w / 2;
        mFlagPointY = h / 2 - 300;

        mFlagPointM = w / 3;
        mFlagPointN = h / 3 - 300;

        mPath = new Path();


    }

    private Path mPath;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);
        mPath.cubicTo(mFlagPointX, mFlagPointY, mFlagPointM, mFlagPointN, mEndPointX, mEndPointY);

        canvas.drawPoint(mStartPointX, mStartPointY, mFlagPaint);
        canvas.drawText("start", mStartPointX, mStartPointY, mTextPaint);
        canvas.drawPoint(mEndPointX, mEndPointY, mFlagPaint);
        canvas.drawText("end", mEndPointX, mEndPointY, mTextPaint);
        canvas.drawPoint(mFlagPointX, mFlagPointY, mFlagPaint);
        canvas.drawPoint(mFlagPointM, mFlagPointN, mFlagPaint);
        canvas.drawText("flag", mFlagPointX, mFlagPointY, mTextPaint);
        canvas.drawText("flag2", mFlagPointM, mFlagPointN, mTextPaint);

        canvas.drawLine(mFlagPointX, mFlagPointY, mFlagPointM, mFlagPointN, mFlagPaint);
        canvas.drawLine(mStartPointX, mStartPointY, mFlagPointX, mFlagPointY, mFlagPaint);
        canvas.drawLine(mEndPointX, mEndPointY, mFlagPointM, mFlagPointN, mFlagPaint);

        canvas.drawPath(mPath, bezierPaint);
    }

    private boolean isMultiPoint = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                isMultiPoint = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isMultiPoint = false;
                break;
            case MotionEvent.ACTION_MOVE:
                mFlagPointX = event.getX(0);
                mFlagPointY = event.getY(0);
                if (isMultiPoint == true) {
                    mFlagPointM = event.getX(1);
                    mFlagPointN = event.getY(1);
                }
                invalidate();
                break;
        }
        return true;
    }
}
