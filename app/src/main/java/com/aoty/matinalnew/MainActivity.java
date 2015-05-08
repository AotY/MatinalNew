package com.aoty.matinalnew;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aoty.matinalnew.adapter.MyPagerAdapter;
import com.aoty.matinalnew.view.astuetz.PagerSlidingTabStrip;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * PagerSlidingTabStrip的实例
     */
    private PagerSlidingTabStrip tabs;

    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;
//    private SlidingMenu mSlidmenu;

    private ViewPager pager;

    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    private void initViews() {
        //初始化侧滑菜单
//        initSlidingMenu();
        dm = getResources().getDisplayMetrics();
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_strip);
        findViewById(R.id.id_title_img).setOnClickListener(this);
//        findViewById(R.id.menu_user_img_photo).setOnClickListener(this);
//        findViewById(R.id.slid_menu_about_me).setOnClickListener(this);
//        findViewById(R.id.slid_menu_feedback).setOnClickListener(this);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myPagerAdapter);
        tabs.setViewPager(pager);
        setTabsValue();
    }

    /*
      初始化侧滑菜单
       */
//    private void initSlidingMenu() {
//        mSlidmenu = new SlidingMenu(this);
//        mSlidmenu.setMode(SlidingMenu.LEFT);
//        //设置为边缘模式， 这样就不会和viewpager的事件冲突了
//        mSlidmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
////        mSlidmenu.setShadowWidthRes(R.dimen.shadow_width);
////        mSlidmenu.setShadowDrawable(R.drawable.shadow);
//        mSlidmenu.setBehindOffset((int) (MyApplication.SCREEN_WIDTH - getWindowManager()
//                .getDefaultDisplay().getWidth() / 2.7));
//        mSlidmenu.setFadeDegree(0.35f);
//        mSlidmenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
//        mSlidmenu.setMenu(R.layout.slid_menu);
//    }


    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#17b592"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setSelectedTextColor(Color.parseColor("#17b592"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_refresh) {
////            ToastUtils.showShort("action_refresh");
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
//        int id = v.getId();
//        if (id == R.id.id_title_img) {
//        } else if (id == R.id.menu_user_img_photo) { //个人中心页面
//            Intent intent = new Intent(this, UserCenterActivity.class);
//            startActivity(intent);
//        } else if (id == R.id.slid_menu_about_me) { //关于我页面
//            Intent intent = new Intent(this, AboutMeActivity.class);
//            startActivity(intent);
//        } else if (id == R.id.slid_menu_feedback) { //意见反馈页面
//            Intent intent = new Intent(this, FeedBackrActivity.class);
//            startActivity(intent);
//        }
//        mSlidmenu.toggle();
    }


}
