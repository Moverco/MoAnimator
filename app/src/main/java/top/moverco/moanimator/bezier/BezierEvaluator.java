package top.moverco.moanimator.bezier;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import top.moverco.moanimator.BezierUtil;


public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mFlagPoint;

    public BezierEvaluator(PointF flagPoint) {
        mFlagPoint = flagPoint;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {
        return BezierUtil.CalculateBezierPointForQuadratic(v, pointF, mFlagPoint, t1);
    }
}
