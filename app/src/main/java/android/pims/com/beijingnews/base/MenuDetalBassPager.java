
package android.pims.com.beijingnews.base;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：菜单详情页面的基类
 * */
public abstract class MenuDetalBassPager {
    /*
* 上下文
* */
    public final Context context;

    /*
    * 代表各个详情页面的视图
    * */
    public View rootView;

    public MenuDetalBassPager(Context context){
    this.context=context;
    rootView=initView();

}
    /*
    * 抽象方法，强制孩子实现该方法，每个页面实现不同的效果
    * */
    public abstract View initView();

    /*
    * 子页面需要绑定数据，联网请求数据的时候，重写该方法
    * */
    public void initData(){

    }

}
