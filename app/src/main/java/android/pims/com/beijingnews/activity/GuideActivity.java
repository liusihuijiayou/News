package android.pims.com.beijingnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.pims.com.beijingnews.R;
import android.pims.com.beijingnews.SplashActivity;
import android.pims.com.beijingnews.utils.CacheUtils;
import android.pims.com.beijingnews.utils.DensityUtil;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class GuideActivity extends Activity {
    //初始化
    private static final String TAG=GuideActivity.class.getSimpleName();
    private ViewPager viewPager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    private int leftmax;  //两点的间距
    private int widthdpi;


    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        btn_start_main=(Button)findViewById(R.id.btn_start_main);
        ll_point_group=(LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point=(ImageView) findViewById(R.id.iv_red_point);

        //准备数据
        int[] ids=new int[]{
          R.drawable.one,
          R.drawable.two,
          R.drawable.yree,
        };

        widthdpi=DensityUtil.dio2x(this,10);
        Log.e(TAG,widthdpi+"-----");


        imageViews =new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            ImageView imageView=new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中
            imageViews.add(imageView);

            //创建点
            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /*单位是像素
            适配：
            * 把单位当成dp转成对应的像素
            * */
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if (i!=0){
                //不包括第0个，所有点距离左边10个像素
                params.leftMargin=widthdpi;
            }

            point.setLayoutParams(params);
            //添加到线性布局
            ll_point_group.addView(point);



        }

        //设置Viewpager适配器
        viewPager.setAdapter(new MyPagerAdapter());

        //根据view生命周期，当视图执行到onLayouthe或者onDraw的时候，视图的宽高边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        //得到屏幕滑动的百分比
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置按钮点击事件
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1、保持曾经进入过主页面
                CacheUtils.putBoolean(GuideActivity.this,SplashActivity.START_MAIN,true);

                //2、跳转到主页面
                Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                //3、关闭引导页面
                finish();

            }
        });
    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
/*
当页面滚动了会回调这个方法
i:当前滑动页面的位置
v:页面滑动的百分比
i1:滑动的像数
*/
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            //两点间移动的距离=屏幕滑动百分比*间距
            //int leftmargin=(int)(v * leftmax);
            Log.e(TAG,"i=="+i+",v=="+v+",i1=="+i1);

            //两点间滑动距离对应的坐标=原来的起始位置+两点间移动的距离
            int leftmargin= (int) (i*leftmax+(v*leftmax));
            //params.leftMargin=两点间滑动距离对应的坐标

             RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
             params.leftMargin=leftmargin;
             iv_red_point.setLayoutParams(params);

        }
/*当页面被选中的时候，回调这个方法
*i:被选中页面的对应的位置
* */
        @Override
        public void onPageSelected(int i) {
            if (i==imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);

            }else {
                btn_start_main.setVisibility(View.GONE);

                //其他页面
            }

        }

        /*
        * 当ViewPager页面滑动状态发生变化时
        *
        * */

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }


    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            //执行不止一次  移除    我们需求 ：执行一次
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //间距=第一个点距离左边的距离-第0个点距离左边的距离
        leftmax=ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();

        }
    }

    class MyPagerAdapter extends PagerAdapter{

        /*返回数据的总个数*/

        @Override
        public int getCount() {
            return imageViews.size();
        }

        /*判断
        * View 当前被创建的视图
        * Object 上面instantiateitem返回的结果值
        * */

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
           // return view == imageViews.get(Integer.parseInt((String) o));  //得到独立视图
            return view==o;
        }

        /*
        * 作用：getView
        * container  ViewPager
        * position   要创建页面的位置
        * return   返回和创建当前页面左右关系的值
        *
        * */

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
           ImageView imageView =imageViews.get(position);
           //添加到容器中
            container.addView(imageView);
            //return position;
            return imageView;

          //  return super.instantiateItem(container, position);
        }

        /*销毁页面
        * container  容器ViewPager
        * position   要销毁的页面的位置
        * object     要销毁的页面
        * */

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
