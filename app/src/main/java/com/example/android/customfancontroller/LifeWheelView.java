package com.example.android.customfancontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LifeWheelView extends View {
    // member variables ( trivial types )
    private float mWidth;
    private float mHeight;
    private Paint mTextPaint;
    private Paint mCutLinesPaint;
    private Paint mDialPaint;
    private float mRadius;

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(40f);

        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDialPaint.setColor(Color.BLUE);

        mCutLinesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCutLinesPaint.setColor(Color.BLACK);
    }

    public LifeWheelView(Context context) {
        super(context);
        init();
    }

    public LifeWheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LifeWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // Calculate the radius from the width and height.
        mWidth = w;
        mHeight = h;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mWidth/2, mHeight/2, mRadius, mDialPaint);
        canvas.drawLine(mWidth/2,0f,mWidth/2, mHeight, mCutLinesPaint);
        canvas.drawLine(mWidth,0f,mWidth, mHeight, mCutLinesPaint);
        canvas.drawLine(0f,mHeight/2,mWidth, mHeight/2, mCutLinesPaint);
        canvas.drawLine(mWidth/2,0f,mWidth/2, mHeight, mCutLinesPaint);
    }
}
