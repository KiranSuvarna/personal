package com.journaldev.youplusmegaevent.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class SolidWithTopGradientDrawable extends Drawable {

    private final Paint mGradientPaint;
    private final Paint mBackgroundPaint;

    private float mGradientHeight;

    private int mHeight;
    private int mWidth;

    public SolidWithTopGradientDrawable(Context context) {
        mGradientHeight = 40 * context.getResources().getDisplayMetrics().density;

        mGradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGradientPaint.setShader(new LinearGradient(0, 0, 0, mGradientHeight,
                Color.TRANSPARENT, Color.BLACK, Shader.TileMode.MIRROR));

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(Color.BLACK);
    }

    @Override
    public void draw(Canvas canvas) {
        float gradientHeight = Math.min(getIntrinsicHeight(), mGradientHeight);
        canvas.drawRect(0, 0, getIntrinsicWidth(), gradientHeight, mGradientPaint);

        canvas.drawRect(0, gradientHeight, getIntrinsicWidth(), getIntrinsicHeight(), mBackgroundPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mHeight = bounds.height();
        mWidth = bounds.width();
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth > 0 ? mWidth : super.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mHeight > 0 ? mHeight : super.getIntrinsicHeight();
    }
}
