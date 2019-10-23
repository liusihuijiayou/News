package android.pims.com.beijingnews.adapter;
/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：内部类抽取数据  --适配器
 * */
import android.pims.com.beijingnews.base.BasePager;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ContentFragmentAdapter extends PagerAdapter {
    private final ArrayList<BasePager> basePagers;

    public ContentFragmentAdapter(ArrayList<BasePager> basePagers){
        this.basePagers=basePagers;
    }
    //页面的总数
    @Override
    public int getCount() {
        return basePagers.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //各个页面的实例
        BasePager basePager=basePagers.get(position);
        View rootView=basePager.rootView;  //各个子页面
        //调用各个页面的initData()
        //basePager.initData(); //初始化数据

        container.addView(rootView);
        return rootView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
