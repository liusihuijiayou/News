package android.pims.com.beijingnews.view;
/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：不可以滚动
 * */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {

    /*
    * 通常在代码中实例化的时候用该方法
    * */
    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    /*
    * 在布局文件中使用该类的时候，实例化该类用该构造方法，这个方法不能少，少的话会崩溃
    * */

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*
    * 重写触摸事件，消耗掉
    * */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
