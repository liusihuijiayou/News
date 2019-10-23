package android.pims.com.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.pims.com.beijingnews.activity.MainActivity;
import android.pims.com.beijingnews.base.BasePager;
import android.pims.com.beijingnews.base.MenuDetalBassPager;
import android.pims.com.beijingnews.domain.NewsCenterPagerBean2;
//import android.pims.com.beijingnews.domain.NewsCenterPagerBear;
import android.pims.com.beijingnews.fragment.LeftmeauFragment;
import android.pims.com.beijingnews.menudetailpager.InterscMenuDetailPager;
import android.pims.com.beijingnews.menudetailpager.NewsMenuDetailPager;
import android.pims.com.beijingnews.menudetailpager.PhotosMenuDetailPager;
import android.pims.com.beijingnews.menudetailpager.TopicMenuDetailPager;
import android.pims.com.beijingnews.menudetailpager.VoteMenuDetailPager;
import android.pims.com.beijingnews.utils.CacheUtils;
import android.pims.com.beijingnews.utils.Constants;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者：刘思慧  on 2019/10/07
 * 微信号：lsh666888
 * QQ号：1252353927
 * 作用：新闻中心页面
 * */
public class NewsCenterPager extends BasePager {
    /*
    * 左侧菜单对应的数据集合
    * */
    private List<NewsCenterPagerBean2.DetailPagerData> data;
    /*
    * 详情页面对应的集合
    * */
    private ArrayList<MenuDetalBassPager> detalBassPagers;
    public NewsCenterPager(Context context) {
        super(context);
    }
    public void initData(){
        super.initData();
        ib_menu.setVisibility(View.VISIBLE);  //左侧菜单设置是否显示

        //1、设置标题
        tv_title.setText("新闻中心");

        //2、联网请求网络数据，创建视图
        TextView textView=new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);

        //3、把子视图添加到basePager的FrameLayout中
        fl_content.addView(textView);

        //4、绑定数据
        textView.setText("新闻中心内容");

        //获取缓存数据
        String saveJson=CacheUtils.getString(context,Constants.NEWSCETER_PAGER_URL);
        if (!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }

        //联网请求数据
        getDataFromNet();





        //3、绑定数据
    }

    /*
    * 使用xUtils联网请求数据
    * */
    private void getDataFromNet() {
        RequestParams params=new RequestParams(Constants.NEWSCETER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {   //CommonCallback公共回调
            @Override
            public void onSuccess(String result) {
                //使用xUtils联网请求成功
                /*
                * 缓存数据
                * */
                CacheUtils.putString(context,Constants.NEWSCETER_PAGER_URL,result);


                processData(result);   //解析json
                //设置适配器


            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //使用xUtils联网请求失败
            }

            @Override
            public void onCancelled(CancelledException cex) {

                //使用xUtils联网取消
            }

            @Override
            public void onFinished() {
                //使用xUtils联网完成
            }
        });
    }
    /*
    * 解析json数据和显示数据
    * */
    private void processData(String json) {
        //NewsCenterPagerBear bear=parsedJson(json);
        NewsCenterPagerBean2 bean=parsedJson2(json);
       // String title=bear.getData().get(0).getChildren().get(1).getTitle();
        //System.out.println("使用Gson解析json数据成功"+title);
        String title2 = bean.getData().get(0).getChildren().get(1).getTitle();
        //给左侧菜单传递数据
       data = bean.getData();
        MainActivity mainActivity=(MainActivity) context;
       //得到左侧菜单
        LeftmeauFragment leftmenuFragment=mainActivity.getLeftmenuFragment();

        //添加新闻详情页面
        detalBassPagers=new ArrayList<>();
        detalBassPagers.add(new NewsMenuDetailPager(context,data.get(0))); //新闻详情页面
        detalBassPagers.add(new TopicMenuDetailPager(context));//专题详情页面
        detalBassPagers.add(new PhotosMenuDetailPager(context));//图组详情页面
        detalBassPagers.add(new InterscMenuDetailPager(context));//互动详情页面
        detalBassPagers.add(new VoteMenuDetailPager(context));//投票详情页面
        //把数据传递给左侧菜单
        leftmenuFragment.setData(data);




    }
    /*
    * 使用Android系统自带的API手动解析
    * */
    private NewsCenterPagerBean2 parsedJson2(String json) {
        NewsCenterPagerBean2 bean2 = new NewsCenterPagerBean2();
        try {
            JSONObject object = new JSONObject(json);


            int retcode = object.optInt("retcode");
            bean2.setRetcode(retcode);//retcode字段解析成功

            JSONArray data = object.optJSONArray("data");
            if (data != null && data.length() > 0) {

                List<NewsCenterPagerBean2.DetailPagerData> detailPagerDatas = new ArrayList<>();
                //设置列表数据
                bean2.setData(detailPagerDatas);
                //for循环，解析每条数据
                for (int i = 0; i < data.length(); i++) {

                    JSONObject jsonObject = (JSONObject) data.get(i);

                    NewsCenterPagerBean2.DetailPagerData detailPagerData = new NewsCenterPagerBean2.DetailPagerData();
                    //添加到集合中
                    detailPagerDatas.add(detailPagerData);

                    int id = jsonObject.optInt("id");
                    detailPagerData.setId(id);
                    int type = jsonObject.optInt("type");
                    detailPagerData.setType(type);
                    String title = jsonObject.optString("title");
                    detailPagerData.setTitle(title);
                    String url = jsonObject.optString("url");
                    detailPagerData.setUrl(url);
                    String url1 = jsonObject.optString("url1");
                    detailPagerData.setUrl1(url1);
                    String dayurl = jsonObject.optString("dayurl");
                    detailPagerData.setDayurl(dayurl);
                    String excurl = jsonObject.optString("excurl");
                    detailPagerData.setExcurl(excurl);
                    String weekurl = jsonObject.optString("weekurl");
                    detailPagerData.setWeekurl(weekurl);


                    JSONArray children = jsonObject.optJSONArray("children");
                    if (children != null && children.length() > 0) {

                        List<NewsCenterPagerBean2.DetailPagerData.ChildrenData> childrenDatas  = new ArrayList<>();

                        //设置集合-ChildrenData
                        detailPagerData.setChildren(childrenDatas);

                        for (int j = 0; j < children.length(); j++) {
                            JSONObject childrenitem = (JSONObject) children.get(j);

                            NewsCenterPagerBean2.DetailPagerData.ChildrenData childrenData = new NewsCenterPagerBean2.DetailPagerData.ChildrenData();
                            //添加到集合中
                            childrenDatas.add(childrenData);


                            int childId = childrenitem.optInt("id");
                            childrenData.setId(childId);
                            String childTitle = childrenitem.optString("title");
                            childrenData.setTitle(childTitle);
                            String childUrl = childrenitem.optString("url");
                            childrenData.setUrl(childUrl);
                            int childType = childrenitem.optInt("type");
                            childrenData.setType(childType);

                        }

                    }


                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return bean2;
    }
    /*
    * 使用第三方Gson解析json
    * 1、使用系统的API解析
    * 2、使用第三方框架解析
    * */
    private NewsCenterPagerBean2 parsedJson(String json){
       // Gson gson=new Gson();
       // NewsCenterPagerBear bear=gson.fromJson(json,NewsCenterPagerBear.class);
        return new Gson().fromJson(json,NewsCenterPagerBean2.class);
    }

    /*
    * 根据位置切换详情页面
    * */
    public void switchPager(int i) {
        //1、设置标题
        tv_title.setText(data.get(i).getTitle());
        //2、移除之前的内容
        fl_content.removeAllViews(); //移除之前的视图
        //3、添加新内容
        MenuDetalBassPager detalBassPager = detalBassPagers.get(i);
        View rootView=detalBassPager.rootView;
        detalBassPager.initData();//初始化数据

        fl_content.addView(rootView);
    }
}
