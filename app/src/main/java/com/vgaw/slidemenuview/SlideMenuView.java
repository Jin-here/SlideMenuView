package com.vgaw.slidemenuview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

/**
 * Created by caojin on 2016/10/18.
 */

/**
 * you only need to configure two attrs:
 * direction: the alignment, can either be left, right, top, bottom
 * isIn: a boolean value that tells whether the {@link SlideMenuView} will show to the user
 */
public class SlideMenuView extends LinearLayout{
    // 滑入滑出方向
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 2;
    public static final int DIRECTION_BOTTOM = 3;

    private int mWidth;
    private int mHeight;
    private int leftMargin;
    private int rightMargin;
    private int topMargin;
    private int bottomMargin;

    private boolean isIn = false;
    private int direction;

    public SlideMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlideMenuView);
        direction = a.getInteger(R.styleable.SlideMenuView_direction, -1);
        isIn = a.getBoolean(R.styleable.SlideMenuView_isIn, true);
        a.recycle();
        if (direction == -1){
            throw new RuntimeException("the direction of the SlideMenuView should be set");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        MarginLayoutParams params = ((MarginLayoutParams)getLayoutParams());
        this.leftMargin = params.leftMargin;
        this.rightMargin = params.rightMargin;
        this.topMargin = params.topMargin;
        this.bottomMargin = params.bottomMargin;
        slide();
    }

    public void slide(){
        // 始终为正数
        float distance;
        switch (direction){
            case DIRECTION_UP:
                distance = topMargin + mHeight;
                animate().yBy(isIn ? distance : -distance).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_BOTTOM:
                distance = bottomMargin + mHeight;
                animate().yBy(isIn ? -distance : distance).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_LEFT:
                distance = leftMargin + mWidth;
                animate().xBy(isIn ? distance : -distance).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_RIGHT:
                distance = rightMargin + mWidth;
                animate().xBy(isIn ? -distance : distance).setInterpolator(new OvershootInterpolator()).start();
                break;
        }
        isIn = !isIn;
    }
}
