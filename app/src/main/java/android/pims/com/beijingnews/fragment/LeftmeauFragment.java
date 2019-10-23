package android.pims.com.beijingnews.fragment;
/*
* 作者：刘思慧  on 2019/10/07
* 微信号：lsh666888
* QQ号：1252353927
* 作用：左侧菜单的Fragment
* */


import android.graphics.Color;
import android.pims.com.beijingnews.R;
import android.pims.com.beijingnews.activity.MainActivity;
import android.pims.com.beijingnews.base.BaseFragment;
import android.pims.com.beijingnews.base.BasePager;
import android.pims.com.beijingnews.domain.NewsCenterPagerBean2;
//import android.pims.com.beijingnews.domain.NewsCenterPagerBear;
import android.pims.com.beijingnews.pager.NewsCenterPager;
import android.pims.com.beijingnews.utils.DensityUtil;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class LeftmeauFragment extends BaseFragment {


    private List<NewsCenterPagerBean2.DetailPagerData> data;
    private LeftmenuFragmentAdapter adapter;
    private ListView listView;
    private int prePosition; //记录点击位置，listview点击变红
    @Override
    public View initView() {
        //左侧菜单被初始化了
        listView =new ListView(context);
        //距离顶部40
        listView.setPadding(0,DensityUtil.dio2x(context,40),0,0);
        //设置分割线高度为0
        listView.setDividerHeight(0);
        //设置透明
        listView.setCacheColorHint(Color.TRANSPARENT);
        //设置按下listView的item不变色
        listView.setSelector(android.R.color.transparent);

        //设置item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //1、记录点击的位置
                prePosition=i;
                adapter.notifyDataSetChanged();// getCount--->getView

                //2、把左侧菜单关闭
                MainActivity mainActivity= (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();//关<-->开

                //3、切换到对应的详情页面，新闻详情页面、专题详情页面。。。 (设置默认页面)
                 switchPager(prePosition);


            }
        });

        return listView;
    }
    /*
    * 根据位置切换不同详情页面
    * */
    private void switchPager(int i){
           MainActivity mainActivity=(MainActivity) context;
           ContentFragment contentFragment= mainActivity.getContentFragment();
           NewsCenterPager newsCenterPager=contentFragment.getNewsCenterPager();
           newsCenterPager.switchPager(i);
        }

    public void initData(){
        super.initData();

    }

    /*
    * 接受数据
    * */
    public void setData(List<NewsCenterPagerBean2.DetailPagerData> data) {
     this.data=data;
        for (int i = 0; i <data.size() ; i++) {

        }


        //设置适配器
        adapter=new LeftmenuFragmentAdapter();
        listView.setAdapter(adapter);
    }

    class LeftmenuFragmentAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //设置左侧菜单文本格式
            TextView textView=(TextView) View.inflate(context,R.layout.item_leftmenu,null);
            textView.setText(data.get(i).getTitle());
            if (i==prePosition){
                //设置红色
                textView.setEnabled(true);
            }else {
                //白色
                textView.setEnabled(false);
            }

            return textView;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


    }
}
