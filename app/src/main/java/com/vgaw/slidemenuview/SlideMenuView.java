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
 * isShow: a boolean value that tells whether the {@link SlideMenuView} will show to the user
 */
public class SlideMenuView extends LinearLayout{
    // 滑入滑出方向
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_TOP = 2;
    public static final int DIRECTION_BOTTOM = 3;

    private int mWidth;
    private int mHeight;
    private int leftMargin;
    private int rightMargin;
    private int topMargin;
    private int bottomMargin;

    private boolean isIn = false;

    private int direction;
    private boolean isShow;

    public SlideMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlideMenuView);
        direction = a.getInteger(R.styleable.SlideMenuView_direction, -1);
        isShow = a.getBoolean(R.styleable.SlideMenuView_isShow, true);
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
        if (!isShow){
            slide();
        }
    }

    public void slide(){
        // 始终为正数
        switch (direction){
            case DIRECTION_TOP:
                animate().y(isIn ? (topMargin + mHeight) : -(bottomMargin + mHeight)).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_BOTTOM:
                animate().y(isIn ? (DensityUtil.getScreenHeight(getContext()) - (mHeight + bottomMargin)) : (DensityUtil.getScreenHeight(getContext()) + topMargin)).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_LEFT:
                animate().x(isIn ? (leftMargin + mWidth) : -(rightMargin + mWidth)).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_RIGHT:
                animate().x(isIn ? (DensityUtil.getScreenWidth(getContext()) - (mWidth + rightMargin)) : (DensityUtil.getScreenWidth(getContext()) + leftMargin)).setInterpolator(new OvershootInterpolator()).start();
                break;
        }
        isIn = !isIn;
    }

    /*
    // 方案二
    public void slide(){
        switch (direction){
            case DIRECTION_TOP:
                animate().y(isIn ? (topMargin + mHeight) : -(bottomMargin + mHeight)).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_BOTTOM:
                animate().y(isIn ? (DensityUtil.getScreenHeight(getContext()) - (mHeight + bottomMargin)) : (DensityUtil.getScreenHeight(getContext()) + topMargin)).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_LEFT:
                animate().x(isIn ? (leftMargin + mWidth) : -(rightMargin + mWidth)).setInterpolator(new OvershootInterpolator()).start();
                break;
            case DIRECTION_RIGHT:
                animate().x(isIn ? (DensityUtil.getScreenWidth(getContext()) - (mWidth + rightMargin)) : (DensityUtil.getScreenWidth(getContext()) + leftMargin)).setInterpolator(new OvershootInterpolator()).start();
                break;
        }
        isIn = !isIn;
    }*/

    /*
    // 方案三
    public void slide(){
        switch (direction){
            case DIRECTION_TOP:
                if (isIn){
                    setVisibility(VISIBLE);
                    animate().y(topMargin).setInterpolator(new OvershootInterpolator()).start();
                }else {
                    setVisibility(GONE);
                    setY(-(bottomMargin + mHeight));
                }
                break;
            case DIRECTION_BOTTOM:
                if (isIn){
                    setVisibility(VISIBLE);
                    animate().y((heightLimit - (mHeight + bottomMargin))).setInterpolator(new OvershootInterpolator()).start();
                }else {
                    setVisibility(GONE);
                    setY(heightLimit + topMargin);
                }
                break;
            case DIRECTION_LEFT:
                if (isIn){
                    setVisibility(VISIBLE);
                    animate().x(leftMargin).setInterpolator(new OvershootInterpolator()).start();
                }else {
                    setVisibility(GONE);
                    setX(-(rightMargin + mWidth));
                }
                break;
            case DIRECTION_RIGHT:
                if (isIn){
                    setVisibility(VISIBLE);
                    animate().x((widthLimit - (mWidth + rightMargin))).setInterpolator(new OvershootInterpolator()).start();
                }else {
                    setVisibility(GONE);
                    setX(widthLimit + leftMargin);
                }
                break;
        }
        isIn = !isIn;
    }*/
}
