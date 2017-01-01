package com.journaldev.youplusmegaevent.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.journaldev.youplusmegaevent.FontsStorage;
import com.journaldev.youplusmegaevent.R;


public class TypefaceTextView extends TextView {

    public TypefaceTextView(final Context context) {
        this(context, null);
    }

    public TypefaceTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TypefaceTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) {
            return;
        }
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView);
        if (array != null) {
            final String typefaceAssetPath = array.getString(R.styleable.TypefaceTextView_font);
            if (typefaceAssetPath != null) {
                setFont(typefaceAssetPath);
            }
            array.recycle();
        }
    }

    public void setFont(final String font) {
        if (font != null) {
            FontsStorage.applyFont(getContext(), font, this);
        }
    }
}
