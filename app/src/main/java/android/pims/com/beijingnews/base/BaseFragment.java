package android.pims.com.beijingnews.base;
/*
* 作者：刘思慧  on 2019/10/07
* 微信号：lsh666888
* QQ号：1252353927
* 作用：Fragment的基类  /基本的Fragment
* ContentFragment和LeftmeauFragment继承它
* */


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    public Activity context;


    /*当Fragmenr被创建的时候回调这个方法
    * */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    /*当视图View被创建的时候回调这个方法
    * */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //让孩子实现自己的视图，达到自己特有的效果
    public abstract View initView();

    /*当Activity被创建之后回调这个方法
     * */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();  //调用数据
    }

    /*
     * 1、如果自页面没有数据，联网请求数据，并且绑定到initView初始化的视图上
     * 2、绑定到initView初始化的视图上
     * */
    public void initData() {

    }
}

