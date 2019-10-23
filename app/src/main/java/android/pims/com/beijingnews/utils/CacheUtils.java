package android.pims.com.beijingnews.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.pims.com.beijingnews.SplashActivity;
import android.pims.com.beijingnews.activity.GuideActivity;

public class CacheUtils {
    /*得到缓存值*/


    public static boolean getBoolean(Context context, String key) {
        /*保存参数*/
        SharedPreferences sp =context.getSharedPreferences("liusihui",Context.MODE_PRIVATE);


        return sp.getBoolean(key,false);
    }

    /*
    *保存软件参数
    * */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp =context.getSharedPreferences("liusihui",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();

    }

    /*
    * 缓存文本数据
    * */
    public static void putString(Context context, String key,String value) {
        SharedPreferences sp =context.getSharedPreferences("liusihui",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();

    }

    /*
    * 获取缓存的文本信息
    * */
    public static String getString(Context context, String key) {
        SharedPreferences sp =context.getSharedPreferences("liusihui",Context.MODE_PRIVATE);

        return sp.getString(key,"");
    }
}
