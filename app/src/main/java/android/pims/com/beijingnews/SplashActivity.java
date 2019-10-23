package android.pims.com.beijingnews;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.pims.com.beijingnews.activity.GuideActivity;
import android.pims.com.beijingnews.activity.MainActivity;
import android.pims.com.beijingnews.utils.CacheUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SplashActivity extends Activity {

    //实例化
    private RelativeLayout rl_splahs_root;
/*静态常量*/
    public static final String START_MAIN = "start_main";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splahs_root=(RelativeLayout) findViewById(R.id.rl_splahs_root);

        //渐变动画，缩放动画，旋转动画
        AlphaAnimation aa =new AlphaAnimation(0,1);  //动画
        //aa.setDuration(500);  //持续播放5秒
        aa.setFillAfter(true);  //状态

        //缩放
        ScaleAnimation sa =new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        //sa.setDuration(500);  //持续播放5秒
        sa.setFillAfter(true);  //状态

        //旋转  0--360°度    相对自身0.5f
        RotateAnimation ra=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
       // ra.setDuration(500);
        ra.setFillAfter(true);

        //同时播放
        AnimationSet set=new AnimationSet(false);
        //添加三个动画没有先后顺序
        set.addAnimation(ra);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.setDuration(2000);


        rl_splahs_root.startAnimation(set);

        set.setAnimationListener(new MyAnimationListener());




    }
    class MyAnimationListener implements Animation.AnimationListener{



        /*当动画开始播放时回调这个方法
         * */
        @Override
        public void onAnimationStart(Animation animation) {



        }

        /*播放结束*/

        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否进入主页面
            boolean isStartMain= CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
            Intent intent;
            if (isStartMain){
                //如果进，直接进入主页面
                intent=new Intent(SplashActivity.this,GuideActivity.class);

        }else {
            //如果没进，进入引导页面
            intent=new Intent(SplashActivity.this,MainActivity.class);

        }
            startActivity(intent);
            //关闭Splash页面
            finish();

        }

        /*重新播放*/

        @Override
        public void onAnimationRepeat(Animation animation) {
           // Toast.makeText(SplashActivity.this,"动画播放完成",Toast.LENGTH_SHORT).show();

        }
    }
}
