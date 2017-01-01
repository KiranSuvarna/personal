package com.journaldev.youplusmegaevent.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class SyncedScrollView extends ScrollView {

    private boolean scrollingEnd;

    private ScrollListener scrollListener = null;

    public SyncedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        View viewTop = getChildAt(0);
        int diffTop = (viewTop.getTop() - getScrollY());

        View viewBottom = getChildAt(getChildCount() - 1);
        int diffBottom = (viewBottom.getBottom() - (getHeight() + getScrollY()));

        scrollingEnd = (diffTop == 0) || (diffBottom == 0);

        if (scrollListener != null) {
            scrollListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public boolean isScrollingEnd() {
        return scrollingEnd;
    }
}
