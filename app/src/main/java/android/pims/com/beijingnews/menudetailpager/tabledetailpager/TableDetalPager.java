package android.pims.com.beijingnews.menudetailpager.tabledetailpager;

import android.content.Context;
import android.graphics.Color;
import android.pims.com.beijingnews.R;
import android.pims.com.beijingnews.base.MenuDetalBassPager;
import android.pims.com.beijingnews.domain.NewsCenterPagerBean2;
import android.pims.com.beijingnews.domain.TableDetalPagerBean;
import android.pims.com.beijingnews.utils.CacheUtils;
import android.pims.com.beijingnews.utils.Constants;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：页签详情页面
 * */
public class TableDetalPager extends MenuDetalBassPager {
    private final NewsCenterPagerBean2.DetailPagerData.ChildrenData childrenData;
  //  private TextView textView;
    private String url;
    public TableDetalPager(Context context, NewsCenterPagerBean2.DetailPagerData.ChildrenData childrenData) {
        super(context);
        this.childrenData=childrenData;
    }

    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.tabletail_pager,null);



        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url=Constants.BASE_URL+childrenData.getUrl();
        //把之前缓存的数据取出
        String saveJson=CacheUtils.getString(context,url);
        if (!TextUtils.isEmpty(saveJson)){
            //解析和处理显示数据
            processData(saveJson);
        }

        //联网请求数据
        getDataFromNet();

    }



    private void getDataFromNet() {
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //缓存数据
                CacheUtils.putString(context,url,result);
                //解析和处理显示数据
                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void processData(String json) {
        TableDetalPagerBean bean=parsedJson(json);
        //bean.getData().getNews().get(0).getTitle();
    }

    private TableDetalPagerBean parsedJson(String json){
        return new Gson().fromJson(json,TableDetalPagerBean.class);
    }

}
