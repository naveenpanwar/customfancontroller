package com.example.android.customfancontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class LifeWheelView extends View {
    // member variables ( trivial types )
    private float mWidth;
    private float mHeight;
    private Paint mCutLinesPaint;
    private float mRadius;
    private Paint paintsDark[] = new Paint[8];
    private Paint paintsLight[] = new Paint[8];
    private Paint paintsVeryLight[] = new Paint[8];
    private RectF rects[] = new RectF[10];

    // sections variables
    private int bodyScore = 0;
    private int mindScore = 0;
    private int careerScore = 0;
    private int moneyScore = 0;
    private int communityScore = 0;
    private int funScore = 0;
    private int homeScore = 0;
    private int loveScore = 0;

    //float radiusStep = mRadius/5;


    private float[] computeXYForPosition(final int pos, final float radius) {
        float[] result = new float[2];
        Double startAngle = Math.PI * 0;   // Angles are in radians.
        Double angle = startAngle + (pos * (Math.PI / 4));
        result[0] = (float) (radius * Math.cos(angle)) + (mWidth / 2);
        result[1] = (float) (radius * Math.sin(angle)) + (mHeight / 2);
        return result;
    }

    private void init(Context context) {
        mCutLinesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCutLinesPaint.setColor(Color.WHITE);
        mCutLinesPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCutLinesPaint.setStrokeWidth(10f);

        paintsDark[0] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[0].setColor(ContextCompat.getColor(context, R.color.body));
        paintsDark[1] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[1].setColor(ContextCompat.getColor(context, R.color.mind));
        paintsDark[2] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[2].setColor(ContextCompat.getColor(context, R.color.career));
        paintsDark[3] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[3].setColor(ContextCompat.getColor(context, R.color.money));
        paintsDark[4] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[4].setColor(ContextCompat.getColor(context, R.color.community));
        paintsDark[5] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[5].setColor(ContextCompat.getColor(context, R.color.fun));
        paintsDark[6] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[6].setColor(ContextCompat.getColor(context, R.color.home));
        paintsDark[7] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsDark[7].setColor(ContextCompat.getColor(context, R.color.love));

        paintsLight[0] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[0].setColor(ContextCompat.getColor(context, R.color.body_light));
        paintsLight[1] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[1].setColor(ContextCompat.getColor(context, R.color.mind_light));
        paintsLight[2] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[2].setColor(ContextCompat.getColor(context, R.color.career_light));
        paintsLight[3] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[3].setColor(ContextCompat.getColor(context, R.color.money_light));
        paintsLight[4] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[4].setColor(ContextCompat.getColor(context, R.color.community_light));
        paintsLight[5] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[5].setColor(ContextCompat.getColor(context, R.color.fun_light));
        paintsLight[6] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[6].setColor(ContextCompat.getColor(context, R.color.home_light));
        paintsLight[7] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsLight[7].setColor(ContextCompat.getColor(context, R.color.love_light));

        paintsVeryLight[0] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[0].setColor(ContextCompat.getColor(context, R.color.body_very_light));
        paintsVeryLight[1] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[1].setColor(ContextCompat.getColor(context, R.color.mind_very_light));
        paintsVeryLight[2] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[2].setColor(ContextCompat.getColor(context, R.color.career_very_light));
        paintsVeryLight[3] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[3].setColor(ContextCompat.getColor(context, R.color.money_very_light));
        paintsVeryLight[4] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[4].setColor(ContextCompat.getColor(context, R.color.community_very_light));
        paintsVeryLight[5] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[5].setColor(ContextCompat.getColor(context, R.color.fun_very_light));
        paintsVeryLight[6] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[6].setColor(ContextCompat.getColor(context, R.color.home_very_light));
        paintsVeryLight[7] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintsVeryLight[7].setColor(ContextCompat.getColor(context, R.color.love_very_light));

    }

    public LifeWheelView(Context context) {
        super(context);
        init(context);
    }

    public LifeWheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LifeWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // Calculate the radius from the width and height.
        mWidth = w;
        mHeight = h;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
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

    public void setBody(int score) {
        if ( score >= 0 && score <= 5) {
            bodyScore = score;
        }
    }

    public void setMind(int score) {
        if ( score >= 0 && score <= 5) {
            mindScore = score;
        }
    }

    public void setCareer(int score) {
        if ( score >= 0 && score <= 5) {
            careerScore = score;
        }
    }

    public void setMoney(int score) {
        if ( score >= 0 && score <= 5) {
            moneyScore = score;
        }
    }

    public void setCommunity(int score) {
        if ( score >= 0 && score <= 5) {
            communityScore = score;
        }
    }

    public void setFun(int score) {
        if ( score >= 0 && score <= 5) {
            funScore = score;
        }
    }

    public void setHome(int score) {
        if ( score >= 0 && score <= 5) {
            homeScore = score;
        }
    }

    public void setLove(int score) {
        if ( score >= 0 && score <= 5) {
            loveScore = score;
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
        float centerX = mWidth/2;
        float centerY = mHeight/2;
        for( int i=0; i < 8; i++) {
            for( int j=0; j<10; j++ ) {
                if( j%2 == 1)
                    canvas.drawArc(rects[j], (float) 45 * i, 45, true, mCutLinesPaint);
                else
                    canvas.drawArc(rects[j], (float) 45 * i, 45, true, paintsVeryLight[i]);
            }
        }

        for( int i=0; i<8; i++) {
            float[] xyData = computeXYForPosition(i, mRadius);
            float x = xyData[0];
            float y = xyData[1];
            canvas.drawLine(centerX, centerY, x, y, mCutLinesPaint);
        }

    }
}
