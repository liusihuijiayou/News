package android.pims.com.beijingnews.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.pims.com.beijingnews.base.MenuDetalBassPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.text.BreakIterator;

/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：新闻专题详情页面
 * */
public class TopicMenuDetailPager extends MenuDetalBassPager {

    public TextView textView;



    public TopicMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView=new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        //新闻专题页面内数据初始化
        //textView.setText("新闻专题页面内容");
    }
}
