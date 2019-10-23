package android.pims.com.beijingnews.base;


/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：基类或者公共类
 * HomePager,NewsCenterPager,SmartServicePager,
 * GovaffairPager,SettingPager都继承BasePager
 * */

import android.content.Context;
import android.pims.com.beijingnews.R;
import android.pims.com.beijingnews.activity.MainActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePager {

//    上下文
    public  final  Context context;  //MainActivity

    /*视图，代表各个不同的页面*/
    public View rootView;
    /*
    * 显示标题
    * */
    public TextView tv_title;
    /*
    * 点击侧滑的
    * */
    public ImageButton ib_menu;
    /*加载各个子页面*/
    public FrameLayout fl_content;

    public BasePager(Context context){
        this.context=context;
        //构造方法一执行，视图就被初始化了
        rootView=initView();
    }

    /*用于初始化公共部分视图，并且初始化加载子视图的FrameLayout*/
    private View initView() {
        //基类的页面
        View view=View.inflate(context, R.layout.base_pager,null);
        tv_title=(TextView) view.findViewById(R.id.tv_title);
        ib_menu=(ImageButton) view.findViewById(R.id.ib_meau);
        fl_content=(FrameLayout) view.findViewById(R.id.fl_content);

        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1、点击后把左侧菜单关闭
                MainActivity mainActivity= (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();//关<-->开

            }
        });


        return view;
    }
    /*
    * 初始化数据；当孩子需要初始化数据；或者绑定数据；联网请求数据并且绑定的时候，
    * 重写该方法
    * */
    public void initData(){

    }

}
