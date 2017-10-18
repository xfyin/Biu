package letus179.com.biu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 重写一个不可以滑动的listview
 *
 * Created by xfyin on 2017/10/18.
 */

public class NoScrollListView extends ListView {

    public NoScrollListView(Context context) {
        this(context, null);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, measureSpec);

    }
}
