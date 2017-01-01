package com.journaldev.youplusmegaevent.custom_view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.journaldev.youplusmegaevent.R;

import java.util.HashMap;
import java.util.Map;


public class YouPlusButton extends View {

    private Rect mRect;

    private int mPrimaryColor;
    private int mPressedColor;
    private int mBackgroundColor;

    private float mCornerRadius;

    private Paint mColoredPaint;
    private Paint mBackgroundPaint;
    private Paint mTextPaint;

    private float mTextHeight;
    private float mTextWidth;
    private int mIconHeight;
    private int mIconWidth;
    private int mIconTextInnerPadding;
    private RectF mBgRect = new RectF();

    private int mDesiredWidth;
    private int mDesiredHeight;

    @Nullable
    private CharSequence mText;

    @Nullable
    private Drawable mIcon;
    private Style mStyle;

    public YouPlusButton(Context context) {
        super(context);

        init(context, null, 0);
    }

    public YouPlusButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0);
    }

    public YouPlusButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // Load defaults from resources
        final Resources res = getResources();
        final int primaryColor = ContextCompat.getColor(context,
                R.color.default_youplus_button_primary_color);
        final int pressedColor = ContextCompat.getColor(context,
                R.color.default_youplus_button_pressed_color);
        final int defBackgroundColor = ContextCompat.getColor(context,
                R.color.default_youplus_button_background_color);
        final int defStyleCode = Style.NORMAL.mCode;
        final float defTextSize = res.getDimension(R.dimen.default_youplus_button_text_size);

        // Retrieve styles attributes
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YouPlusButton,
                defStyleAttr, 0);
        float textSize;
        try {
            mPrimaryColor = a.getColor(R.styleable.YouPlusButton_primaryColor, primaryColor);
            mPressedColor = a.getColor(R.styleable.YouPlusButton_pressedColor, pressedColor);
            mBackgroundColor = a.getColor(R.styleable.YouPlusButton_backgroundColor, defBackgroundColor);
            textSize = a.getDimension(R.styleable.YouPlusButton_textSize, defTextSize);
            mText = a.getText(R.styleable.YouPlusButton_text);
            mIcon = a.getDrawable(R.styleable.YouPlusButton_icon);
            mStyle = Style.getByCode(a.getInt(R.styleable.YouPlusButton_fillStyle, defStyleCode));
        } finally {
            a.recycle();
        }

        float density = getResources().getDisplayMetrics().density;

        mCornerRadius = 5 * density;

        mColoredPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        updatePaintsStyles();
        updatePantsColors(false);

        updateTextRectAndMeasurements();
    }

    private void updatePaintsStyles() {
        if (mStyle == Style.NORMAL) {
            mColoredPaint.setStyle(Paint.Style.STROKE);
            mColoredPaint.setStrokeWidth(getResources().getDisplayMetrics().density);
        } else if (mStyle == Style.FILLED) {
            mColoredPaint.setStyle(Paint.Style.FILL);
            mColoredPaint.setStrokeWidth(0);
        }
    }

    private void updatePantsColors(boolean touched) {
        if (!touched) {
            mColoredPaint.setColor(mPrimaryColor);
        } else {
            mColoredPaint.setColor(mPressedColor);
        }

        if (mStyle == Style.NORMAL) {
            if (!touched) {
                mTextPaint.setColor(mPrimaryColor);

                if (mIcon != null) {
                    int red = Color.red(mPrimaryColor);
                    int green = Color.green(mPrimaryColor);
                    int blue = Color.blue(mPrimaryColor);
                    mIcon.setColorFilter(new ColorMatrixColorFilter(new float[] {
                            0, 0, 0, 0, red,
                            0, 0, 0, 0, green,
                            0, 0, 0, 0, blue,
                            0, 0, 0, 1, 0
                    }));
                }
            } else {
                mTextPaint.setColor(mPressedColor);

                if (mIcon != null) {
                    int red = Color.red(mPressedColor);
                    int green = Color.green(mPressedColor);
                    int blue = Color.blue(mPressedColor);
                    mIcon.setColorFilter(new ColorMatrixColorFilter(new float[] {
                            0, 0, 0, 0, red,
                            0, 0, 0, 0, green,
                            0, 0, 0, 0, blue,
                            0, 0, 0, 1, 0
                    }));
                }
            }
            mBackgroundPaint.setColor(mBackgroundColor);
        } else if (mStyle == Style.FILLED) {
            mTextPaint.setColor(mBackgroundColor);

            mBackgroundPaint.setColor(Color.TRANSPARENT);

            if (mIcon != null) {
                int red = Color.red(mBackgroundColor);
                int green = Color.green(mBackgroundColor);
                int blue = Color.blue(mBackgroundColor);
                mIcon.setColorFilter(new ColorMatrixColorFilter(new float[] {
                        0, 0, 0, 0, red,
                        0, 0, 0, 0, green,
                        0, 0, 0, 0, blue,
                        0, 0, 0, 1, 0
                }));
            }
        }
    }

    private void updateTextRectAndMeasurements() {
        float density = getResources().getDisplayMetrics().density;

        int padding = (int) (8 * 2 * density);
        mTextHeight = Math.abs(mTextPaint.descent() + mTextPaint.ascent());
        mTextWidth = !TextUtils.isEmpty(mText)
                ? mTextPaint.measureText(mText, 0, mText.length())
                : 0;
        mIconHeight = 0;
        mIconWidth = 0;
        if (mIcon != null) {
            mIconHeight = (int) (mTextHeight * 1.5f);
            mIconWidth = mIcon.getIntrinsicWidth() * mIconHeight / mIcon.getIntrinsicHeight();
        }
        mIconTextInnerPadding = (mTextWidth > 0 && mIconHeight > 0)
                ? (int) (5 * density)
                : 0;

        mDesiredWidth = (int) (padding + mTextWidth + mIconHeight + mIconTextInnerPadding);
        mDesiredHeight = (int) Math.max(48 * getResources().getDisplayMetrics().density,
                mTextHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = mDesiredWidth;
        int desiredHeight = mDesiredHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        // Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            // Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            // Be whatever you want
            width = desiredWidth;
        }

        // Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            // Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            // Be whatever you want
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        drawBackground(canvas);

        drawLabels(canvas);
    }

    private void drawBackground(Canvas canvas) {
        float borderWidth = mColoredPaint.getStrokeWidth();
        //noinspection SuspiciousNameCombination
        mBgRect.set(borderWidth, borderWidth, getWidth() - borderWidth, getHeight() - borderWidth);

        canvas.drawRoundRect(mBgRect, mCornerRadius, mCornerRadius, mBackgroundPaint);
        canvas.drawRoundRect(mBgRect, mCornerRadius, mCornerRadius, mColoredPaint);
    }

    private void drawLabels(Canvas canvas) {
        int labelLeft = (int) ((getWidth() - mIconWidth - mTextWidth - mIconTextInnerPadding) / 2);

        if (mIcon != null) {
            mIcon.setBounds(labelLeft, (getHeight() - mIconHeight) / 2, labelLeft + mIconWidth,
                    (getHeight() + mIconHeight) / 2);
            mIcon.draw(canvas);
        }

        CharSequence text = getText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        canvas.drawText(text.toString(),
                labelLeft + mIconWidth + mIconTextInnerPadding + mTextWidth / 2,
                getHeight() / 2 + mTextHeight / 2, mTextPaint);
    }

    private void onTouch(boolean touched) {
        updatePantsColors(touched);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        boolean result = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onTouch(true);

                mRect = new Rect(getLeft(), getTop(), getRight(), getBottom());

                invalidate();

                result = true;
                break;
            case MotionEvent.ACTION_UP:
                if (mRect != null) {
                    onTouch(false);

                    mRect = null;
                    performClick();
                    result = true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mRect != null) {
                    onTouch(false);

                    mRect = null;
                    result = false;
                }
            case MotionEvent.ACTION_MOVE:
                if (mRect != null && !mRect.contains(getLeft() + (int) event.getX(), getTop() +
                        (int) event.getY())) {
                    onTouch(false);

                    mRect = null;
                    result = false;
                }
                break;
        }
        return result;
    }

    @Nullable
    public CharSequence getText() {
        return mText;
    }

    public void setText(@Nullable CharSequence text) {
        mText = text;

        updateTextRectAndMeasurements();

        invalidate();
    }

    public void setText(@StringRes int stringRes) {
        mText = getResources().getString(stringRes);

        updateTextRectAndMeasurements();

        invalidate();
    }

    public void setIconResource(@DrawableRes int drawableRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIcon = getResources().getDrawable(drawableRes, null);
        } else {
            //noinspection deprecation
            mIcon = getResources().getDrawable(drawableRes);
        }

        updateTextRectAndMeasurements();

        invalidate();
    }

    public void setIconDrawable(@Nullable Drawable drawable) {
        mIcon = drawable;

        updateTextRectAndMeasurements();

        invalidate();
    }

    public void setIconBitmap(@Nullable Bitmap loadedImage) {
        mIcon = new BitmapDrawable(getResources(), loadedImage);

        updateTextRectAndMeasurements();

        invalidate();
    }

    public enum Style {
        // note: this values must be the same as for R.styleable.YouPlusButton_style values
        /** Regular colors will be used for text color */
        NORMAL(0),
        /** Background color will be used for text for this style */
        FILLED(1);

        private static final Map<Integer, Style> CODE_TO_STYLE = new HashMap<>();

        private final int mCode;

        static {
            for (Style style : Style.values()) {
                CODE_TO_STYLE.put(style.mCode, style);
            }
        }

        Style(int code) {
            mCode = code;
        }

        public static Style getByCode(int code) {
            return CODE_TO_STYLE.get(code);
        }
    }
}
