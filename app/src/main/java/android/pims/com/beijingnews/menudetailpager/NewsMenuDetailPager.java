package android.pims.com.beijingnews.menudetailpager;
/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：新闻菜单详情页面
 * */

import android.content.Context;
import android.pims.com.beijingnews.R;
import android.pims.com.beijingnews.activity.MainActivity;
import android.pims.com.beijingnews.base.MenuDetalBassPager;
import android.pims.com.beijingnews.domain.NewsCenterPagerBean2;
//import android.pims.com.beijingnews.domain.NewsCenterPagerBear;
import android.pims.com.beijingnews.menudetailpager.tabledetailpager.TableDetalPager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.pims.com.beijingnews.R.layout.newsmenu_detail_pagerr;
import static android.pims.com.beijingnews.R.layout.titlebar;

public class NewsMenuDetailPager extends MenuDetalBassPager {

    @ViewInject(R.id.tabPageIndicator)
    private TabPageIndicator tabPageIndicator;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    @ViewInject(R.id.ib_tab_next)
    private ImageButton ib_tab_next;

    /*
    * 页签页面的数据的集合--数据
    * */
    private List<NewsCenterPagerBean2.DetailPagerData.ChildrenData> children;

    /*
    * 装12个TableDetalPager  页签页面的集合--页面
    * */
    private ArrayList<TableDetalPager> tableDetalPagers;


    public NewsMenuDetailPager(Context context,NewsCenterPagerBean2.DetailPagerData detailPagerData) {
        super(context);
        children=detailPagerData.getChildren();
    }

    @Override
    public View initView() {

        View view =View.inflate(context, newsmenu_detail_pagerr,null);
        //使用xUtil初始化
        x.view().inject(NewsMenuDetailPager.this,view);

        //设置按钮点击事件
        ib_tab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

            }
        });

        return view;
    }

    @Override
    public void initData() {
      super.initData();
        //新闻详情页面内数据初始化
        //准备新闻详情页面数据
        tableDetalPagers=new ArrayList<>();
        for (int i = 0; i <children.size() ; i++) {
            tableDetalPagers.add(new TableDetalPager(context,children.get(i)));

        }
        //设置ViewPager适配器
        viewPager.setAdapter(new MyNewMenuDetailPagerAdapter());

       /* ViewPager和TabPageIndicator关联
       * （注意以后监听页面的变化，TabPageIndicator监听页面的变化）
       * */
       tabPageIndicator.setViewPager(viewPager);

       tabPageIndicator.setOnPageChangeListener(new MyOnPageChangeListener());


    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if (i==0){
                //sildingmenu可以全屏滑动
                isEnableSidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
            }else{
                //sildingmenu不可以全屏滑动
                isEnableSidingMenu(SlidingMenu.TOUCHMODE_NONE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    /*
     * 根据传入的参数设置是否让SlidingMeau可以滑动
     * */
    private void isEnableSidingMenu(int t) {
        MainActivity mainActivity=(MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(t);  //可以滑动
    }


    class MyNewMenuDetailPagerAdapter extends PagerAdapter{

        /*
        * 显示标题
        * */
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return children.get(position).getTitle();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            TableDetalPager tableDetalPager=tableDetalPagers.get(position);
            View rootView=tableDetalPager.rootView;
            tableDetalPager.initData();//初始化数据
            container.addView(rootView);
            return rootView;
        }

        @Override
        public int getCount() {
            return tableDetalPagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }
    }
}
