package com.groupd.cookbook.presentation;

/**
 * Created by Zhang Jiashen on 2017/7/11.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class PageView extends HorizontalScrollView {
    private int mBaseScrollX;
    private int mScreenWidth;
    private int mScreenHeight;

    private LinearLayout mContainer;
    private boolean flag;
    private int mPageCount;

    private int mScrollX = 200;

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics dm = context.getApplicationContext().getResources()
                .getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }


    public void addPage(View page) {
        addPage(page, -1);
    }


    public void addPage(View page, int index) {
        if(!flag) {
            mContainer = (LinearLayout) getChildAt(0);
            flag = true;
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mScreenWidth, mScreenHeight);
        if(index == -1) {
            mContainer.addView(page, params);
        } else {
            mContainer.addView(page, index, params);
        }
        mPageCount++;
    }


    public void removePage(int index) {
        if(mPageCount < 1) {
            return;
        }
        if(index<0 || index>mPageCount-1) {
            return;
        }
        mContainer.removeViewAt(index);
        mPageCount--;
    }


    public void removeAllPages() {
        if(mPageCount > 0) {
            mContainer.removeAllViews();
        }
    }


    public int getPageCount() {
        return mPageCount;
    }


    private int getBaseScrollX() {
        return getScrollX() - mBaseScrollX;
    }


    private void baseSmoothScrollTo(int x) {
        smoothScrollTo(x + mBaseScrollX, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollX = getBaseScrollX();
                if (scrollX > mScrollX) {
                    baseSmoothScrollTo(mScreenWidth);
                    mBaseScrollX += mScreenWidth;
                }
                else if (scrollX > 0) {
                    baseSmoothScrollTo(0);
                }
                else if(scrollX > -mScrollX) {
                    baseSmoothScrollTo(0);
                }
                else {
                    baseSmoothScrollTo(-mScreenWidth);
                    mBaseScrollX -= mScreenWidth;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
