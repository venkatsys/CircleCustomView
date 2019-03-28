package com.smart.circlecustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ModuleStatusView extends View {

    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;


    private boolean[] mModuleStatus;
    private float mOutlineWidth;
    private float mShapeSize;
    private float mSpacing;
    private Rect[] mModuleRectangles;

    private int mOutlineColor;
    private Paint mPaintOutline;

    private int mFillColor;
    private Paint mPaintFill;
    private float mRadius;
    private int EDIT_MODE_MODULE_COUNT = 7;



    public boolean[] getmModuleStatus() {
        return mModuleStatus;
    }

    public void setmModuleStatus(boolean[] mModuleStatus) {
        this.mModuleStatus = mModuleStatus;
    }



    public ModuleStatusView(Context context) {
        super(context);
        init(null, 0);
    }

    public ModuleStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ModuleStatusView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        if(isInEditMode())
            setupEditModeValues();

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ModuleStatusView, defStyle, 0);
        a.recycle();

        mOutlineWidth = 6f;
        mShapeSize = 144f;
        mSpacing= 30f;

        mRadius = (mShapeSize - mOutlineWidth) / 2;

        setupModuleRectangles();

        mOutlineColor = Color.BLACK;
        mPaintOutline = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintOutline.setStyle(Paint.Style.STROKE);
        mPaintOutline.setStrokeWidth(mOutlineWidth);
        mPaintOutline.setColor(mOutlineColor);

        mFillColor = Color.YELLOW;
        mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFill.setStyle(Paint.Style.FILL);
        mPaintFill.setColor(mFillColor);

    }

    private void setupEditModeValues() {
        boolean[] exampleModuleValues = new boolean[EDIT_MODE_MODULE_COUNT];

        int middle = EDIT_MODE_MODULE_COUNT / 2;

        for(int i = 0 ; i < middle ; i ++){
            exampleModuleValues[i] = true;
        }
        setmModuleStatus(exampleModuleValues);
    }

    private void setupModuleRectangles() {
        mModuleRectangles = new Rect[mModuleStatus.length];

        for (int moduleIndex = 0; moduleIndex < mModuleRectangles.length; moduleIndex++){
            int x = (int) (moduleIndex * (mShapeSize + mSpacing));
            int y = 0;
            mModuleRectangles[moduleIndex] = new Rect(x, y, x + (int) mShapeSize , y + (int) mShapeSize);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int moduleIndex = 0; moduleIndex < mModuleRectangles.length; moduleIndex++){

            float x = mModuleRectangles[moduleIndex].centerX();
            float y =  mModuleRectangles[moduleIndex].centerY();

            if(mModuleStatus[moduleIndex])
                canvas.drawCircle(x,y,mRadius,mPaintFill);

            canvas.drawCircle(x,y,mRadius,mPaintOutline);
        }
    }
}