package com.hensen.shixiongsdk.Widght;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

/**
 * @author 许英俊 2017/4/14
 */
public class ExtendGridView extends GridView {

    public ExtendGridView(Context context) {
        this(context,null);
    }

    public ExtendGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExtendGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
