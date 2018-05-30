package com.example.android.customfancontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private Paint paints[] = new Paint[8];
    private RectF rects[] = new RectF[10];

    //float radiusStep = mRadius/5;

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(40f);

        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDialPaint.setColor(Color.BLUE);

        mCutLinesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCutLinesPaint.setColor(Color.WHITE);
        mCutLinesPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCutLinesPaint.setStrokeWidth(4.5f);

        paints[0] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[0].setColor(getResources().getColor(R.color.body));
        paints[1] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[1].setColor(getResources().getColor(R.color.mind));
        paints[2] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[2].setColor(getResources().getColor(R.color.career));
        paints[3] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[3].setColor(getResources().getColor(R.color.money));
        paints[4] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[4].setColor(getResources().getColor(R.color.community));
        paints[5] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[5].setColor(getResources().getColor(R.color.fun));
        paints[6] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[6].setColor(getResources().getColor(R.color.home));
        paints[7] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paints[7].setColor(getResources().getColor(R.color.love));
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
        float step = mRadius/5;
        float lRadius = mRadius;
        for( int i=0; i<10;i++) {
            rects[i] = new RectF(
                    mWidth / 2 - lRadius,
                    mHeight / 2 - lRadius,
                    mWidth / 2 + lRadius,
                    mHeight / 2 + lRadius
            );
            if( i%2 == 0 ) {
                lRadius -= (mRadius * 0.15);
            }
            else {
                if ( i == 9 )
                    continue;
                lRadius -= (mRadius * 0.02);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //int paintvar = 0;

        /*
        for( float i=mRadius; i>=radiusStep; i-=radiusStep) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, i, paints[paintvar++]);
        }
        */
        for( int i=0; i < 8; i++) {
            for( int j=0; j<10; j++ ) {
                if( j%2 == 1)
                    canvas.drawArc(rects[j], (float) 45 * i, 45-2, true, mCutLinesPaint);
                else
                    canvas.drawArc(rects[j], (float) 45 * i, 45-2, true, paints[i]);
            }
        }
        canvas.drawLine(mWidth/2,0f,mWidth/2, mHeight, mCutLinesPaint); // vertical bisector
        canvas.drawLine(0f,mHeight/2,mWidth, mHeight/2, mCutLinesPaint);// horizontal bisector


        canvas.drawLine(mWidth/2,mHeight/2,mWidth/2 - mRadius, mHeight/2 - mRadius, mCutLinesPaint);               // right diagonal
        canvas.drawLine(mWidth/2,mHeight/2,mWidth/2 + mRadius, mHeight/2 + mRadius, mCutLinesPaint);
        canvas.drawLine(mWidth/2,mHeight/2,mWidth/2 + mRadius, mHeight/2 - mRadius, mCutLinesPaint);
        canvas.drawLine(mWidth/2,mHeight/2,mWidth/2 - mRadius, mHeight/2 + mRadius, mCutLinesPaint);
    }
}
