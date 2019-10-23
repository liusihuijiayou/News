package android.pims.com.beijingnews.activity;

import android.app.Activity;
import android.pims.com.beijingnews.R;
import android.pims.com.beijingnews.fragment.ContentFragment;
import android.pims.com.beijingnews.fragment.LeftmeauFragment;
import android.pims.com.beijingnews.utils.DensityUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMEAU_TAG = "leftmeau_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //设置没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //重构快捷键  Alt+Shift+M
        initSlidingMenu();
        //初始化Fragment
        initFragmnet();

    }

    private void initSlidingMenu() {
        //1、设置主页面
        setContentView(R.layout.activity_main);
        //2、设置左侧菜单
        setBehindContentView(R.layout.activity_leftmeau);
        //3、设置右侧菜单
        SlidingMenu slidingMenu=getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.activity_rightmeau);  //设置右侧菜单

        /*
        * 4、设置显示模式：
        * 左侧菜单+主页
        * 左侧菜单+主页+右侧菜单
        * 主页+右侧菜单；
        * */
        slidingMenu.setMode(SlidingMenu.LEFT);

        /*
        *5、设置滑动模式
        * 滑动边缘 TOUCHMODE_MARGIN
        * 全屏滑动
        * 不可以滑动
        * */
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        //6、设置主页占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dio2x(MainActivity.this,200));
    }

    private void initFragmnet() {
        //1、得到FragmnetManger
        FragmentManager fm=getSupportFragmentManager();
        //2、开启事务
        FragmentTransaction ft=fm.beginTransaction();

        //3、替换
        ft.replace(R.id.fl_mian_content,new ContentFragment(), MAIN_CONTENT_TAG); //主页
        ft.replace(R.id.fl_leftmeau,new LeftmeauFragment(), LEFTMEAU_TAG); //左侧菜单

        //4、提交
        ft.commit();
    }

/*
* 得到左侧菜单Fragment
* */
    public LeftmeauFragment getLeftmenuFragment() {
        return  (LeftmeauFragment) getSupportFragmentManager().findFragmentByTag(LEFTMEAU_TAG);

    }
    /*
    * 得到正文的Fragment
    *
    * */

    public ContentFragment getContentFragment() {
        return  (ContentFragment) getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);
    }
}
