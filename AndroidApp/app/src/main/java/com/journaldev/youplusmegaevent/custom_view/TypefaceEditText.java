package com.journaldev.youplusmegaevent.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import com.journaldev.youplusmegaevent.FontsStorage;
import com.journaldev.youplusmegaevent.R;



public class TypefaceEditText extends EditText {

    public TypefaceEditText(final Context context) {
        super(context);
        init(context, null, 0);
    }

    public TypefaceEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TypefaceEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(final Context context, final AttributeSet attrs, final int defStyle) {
        if (!isInEditMode() && attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandableTextView, 0, 0);
            String customFont = a.getString(R.styleable.TypefaceTextView_font);
            if (!TextUtils.isEmpty(customFont)) {
                setFont(customFont);
            }
            a.recycle();
        }
    }

    public void setFont(final String font) {
        if (font != null) {
            if (!isInEditMode()) {
                FontsStorage.applyFont(getContext(), font, this);
            }
        }
    }
}

