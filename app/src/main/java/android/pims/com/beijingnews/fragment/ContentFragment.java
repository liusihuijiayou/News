package android.pims.com.beijingnews.fragment;
/*
* 作者：刘思慧  on 2019/10/07
* 微信号：lsh666888
* QQ号：1252353927
* 作用：正文的Fragment
* */



import android.graphics.Color;
import android.pims.com.beijingnews.R;
import android.pims.com.beijingnews.activity.MainActivity;
import android.pims.com.beijingnews.adapter.ContentFragmentAdapter;
import android.pims.com.beijingnews.base.BaseFragment;
import android.pims.com.beijingnews.base.BasePager;
import android.pims.com.beijingnews.pager.GovaffairPager;
import android.pims.com.beijingnews.pager.HomePager;
import android.pims.com.beijingnews.pager.NewsCenterPager;
import android.pims.com.beijingnews.pager.SettingPager;
import android.pims.com.beijingnews.pager.SmartServerPager;
import android.pims.com.beijingnews.view.NoScrollViewPager;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


public class ContentFragment extends BaseFragment {
    //2、初始化控件
    @ViewInject(R.id.viewpager)
    private NoScrollViewPager  viewpager;
    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    /*
     * 装5个页面的集合
     * */
    private ArrayList<BasePager> basePagers;


    @Override
    public View initView() {
        //正文Fragment视图被初始化了
        View view = View.inflate(context, R.layout.content_fragmnet, null);

        // viewpager=(ViewPager) view.findViewById(R.id.viewpager);
        //  rg_main=(RadioGroup) view.findViewById(R.id.rg_main);

        //xUtils3初始化
        //1、把视图注入到框架中。让ContentFragment类与view关联起来
        x.view().inject(ContentFragment.this, view);

        return view;
    }

    public void initData() {
        //正文Fragment数据被初始化了
        super.initData();

        //初始化五个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context)); //主页面
        basePagers.add(new NewsCenterPager(context)); //新闻中心页面
        basePagers.add(new GovaffairPager(context)); //政要指南页面
        basePagers.add(new SmartServerPager(context)); //智慧服务页面
        basePagers.add(new SettingPager(context)); //设置中心页面



        //设置ViewPager的适配器
        viewpager.setAdapter(new ContentFragmentAdapter(basePagers));

        //设置RadioGroup的选中状态改变监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //监听某个页面被选中，初始对应的页面的数据
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        //默认选中首页
        rg_main.check(R.id.rb_home);

        basePagers.get(0).initData();
        //设置SlidingMenu不可以滑动
        isEnableSidingMenu(SlidingMenu.TOUCHMODE_NONE);

    }

    /*
    * 得到新闻中心
    * */
    public NewsCenterPager getNewsCenterPager() {
          return (NewsCenterPager)basePagers.get(1);
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        /*
        * i:滚动位置
        * v:页面滚动百分比
        * i1:滑动的像素
        * */

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        /*某个页面被选中回调的位置
        * */
        @Override
        public void onPageSelected(int i) {
            //调用被选中页面的initData页面
            basePagers.get(i).initData();

        }

        /*
        * 状态变化
        * */
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_home: //主页面
                    viewpager.setCurrentItem(0);
                    isEnableSidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter: //新闻中心
                    viewpager.setCurrentItem(1,false);
                    isEnableSidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice: //智慧服务的Button的id
                    viewpager.setCurrentItem(2,false);
                    isEnableSidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaggair:
                    viewpager.setCurrentItem(3);
                    isEnableSidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting:
                    viewpager.setCurrentItem(4,false);
                    isEnableSidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;

            }

        }
    }
/*
* 根据传入的参数设置是否让SlidingMeau可以滑动
* */
    private void isEnableSidingMenu(int t) {
        MainActivity mainActivity=(MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(t);  //可以滑动
    }


 /*   class ContentFragmentAdapter extends PagerAdapter{

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
*/
}
