package android.pims.com.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.pims.com.beijingnews.base.BasePager;
import android.view.Gravity;
import android.widget.TextView;

/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：政要指南页面
 * */
public class GovaffairPager extends BasePager {

    public GovaffairPager(Context context) {
        super(context);
    }
    public void initData(){
        super.initData();
        //1、设置标题
        tv_title.setText("政要指南页面");

        //2、联网请求网络数据，创建视图
        TextView textView=new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);

        //3、把子视图添加到basePager的FrameLayout中
        fl_content.addView(textView);

        //4、绑定数据
        textView.setText("政要指南页面内容");




        //3、绑定数据
    }
}
