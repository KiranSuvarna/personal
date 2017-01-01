package com.journaldev.youplusmegaevent.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.journaldev.youplusmegaevent.FontsStorage;
import com.journaldev.youplusmegaevent.R;

import java.util.HashMap;
import java.util.Map;


public class SquareImageView  extends ImageView {

    private Side mSide = Side.WIDTH;

    public SquareImageView(Context context) {
        super(context);

        init(context, null, 0);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Retrieve styles attributes
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SquareImageView, defStyle, 0);
        try {
            mSide = Side.getByCode(a.getInt(R.styleable.SquareImageView_stretchMode, Side.MIN.mCode));
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int side = mSide.getSide(measuredWidth, measuredHeight);
        setMeasuredDimension(side, side);
    }

    public enum Side {
        MIN(0) {
            @Override
            public int getSide(int width, int height) {
                if (width > height) {
                    return height;
                } else {
                    return width;
                }
            }
        },
        WIDTH(1) {
            @Override
            public int getSide(int width, int height) {
                return width;
            }
        },
        HEIGHT(2) {
            @Override
            public int getSide(int width, int height) {
                return height;
            }
        };

        private static final Map<Integer, Side> CODE_TO_SIDE = new HashMap<>();

        private final int mCode;

        static {
            for (Side style : Side.values()) {
                CODE_TO_SIDE.put(style.mCode, style);
            }
        }

        Side(int code) {
            mCode = code;
        }

        public static Side getByCode(int code) {
            return CODE_TO_SIDE.get(code);
        }

        public abstract int getSide(int width, int height);
    }
}
