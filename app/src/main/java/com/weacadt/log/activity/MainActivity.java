package com.weacadt.log.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.weacadt.log.R;
import com.weacadt.log.fragment.CalendarFragment;
import com.weacadt.log.fragment.DiaryFragment;
import com.weacadt.log.fragment.TodoFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    //声明抽屉对象
    private DrawerLayout mDrawerLayout;

    //声明 ViewPager 相关
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter fpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        //设置 ViewPager Adapter
        mViewPager.setAdapter(fpAdapter);
    }

    private void initData() {
        fpAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

    }
    private void initView() {
        //ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar_main);

        //设置 ActionBar 为 ToolBar
        setSupportActionBar(toolbar);

        //获取 ActionBar 实例
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  //抽屉按钮
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_menu); //抽屉按钮的图标（不设置默认为返回图标）
        }

        //抽屉布局及侧滑栏 声明
        mDrawerLayout = findViewById(R.id.layout_drawer);
        NavigationView mNavView = findViewById(R.id.nav_view);

        //侧滑栏
        mNavView.setCheckedItem(R.id.home); //侧滑栏默认选中的选项
        mNavView.setNavigationItemSelectedListener(navItemSelectedListener);    //侧滑栏的点击事件

        //Fragment 声明及构造
        TodoFragment mTodoFragment = new TodoFragment();
        DiaryFragment mDiaryFragment = new DiaryFragment();
        CalendarFragment mCalendarFragment = new CalendarFragment();

        fragmentList = new ArrayList<Fragment>();

        //ViewPager 构造
        mViewPager = findViewById(R.id.view_pager);

        fragmentList.add(mTodoFragment);
        fragmentList.add(mDiaryFragment);
        fragmentList.add(mCalendarFragment);

        mViewPager.addOnPageChangeListener(pageChangeListener);
    }

    /*  ToolBar 右边菜单创建    */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);   //加载 toolbar_menu
        return true;
    }

    /*  ToolBar 右边菜单选择    */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: //点击抽屉按钮打开抽屉
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.none:
                AboutActivity.actionStart(MainActivity.this);
                break;
            case R.id.none2:
                Toast.makeText(this, "Click None2", Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }

    /*  ViewPager   */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            switch (i){
                case 0:
                    //mNavView.setSelectedItemId(R.id.bottom_nav_todo);
                    break;
                case 1:
                    //mNavView.setSelectedItemId(R.id.bottom_nav_diary);
                    break;
                case 2:
                    //mNavView.setSelectedItemId(R.id.bottom_nav_calendar);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    /*  抽屉选项    */
    private NavigationView.OnNavigationItemSelectedListener navItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.home:
                    Toast.makeText(MainActivity.this, "你点了主页", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.setting:
                    Toast.makeText(MainActivity.this, "你点了设置", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.about:
                    Toast.makeText(MainActivity.this, "你点了关于", Toast.LENGTH_SHORT).show();
                    AboutActivity.actionStart(MainActivity.this);
                    break;
            }
            return true;
        }
    };
}


