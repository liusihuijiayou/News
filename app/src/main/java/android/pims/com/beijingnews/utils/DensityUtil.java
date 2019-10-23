package android.pims.com.beijingnews.utils;

import android.content.Context;

/*适配工具类
* 单位转换工具
* px、dip互相转换工具
* */
public class DensityUtil {
//   根据手机的分辨率从dip单位转化成px像素
    public static int dio2x(Context context,float dpVaule){
       final float scale=context.getResources().getDisplayMetrics().density;
        return (int) (dpVaule*scale+0.5f);
    }

 // 根据手机的分辨率从px（像素)的单位转化成dp


    public static int px2dip(Context context,float pxValue) {
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
}
