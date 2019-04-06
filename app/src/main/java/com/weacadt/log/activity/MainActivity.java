package com.weacadt.log.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.weacadt.log.R;
import com.weacadt.log.fragment.CalendarFragment;
import com.weacadt.log.fragment.DiaryFragment;
import com.weacadt.log.fragment.TodoFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    //抽屉
    private DrawerLayout mDrawerLayout;

    //ViewPager
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter fpAdapter;

    //底部导航栏
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initTopView();
        initMiddleView();
        initBottomView();

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

    /*  初始化 ToolBar，抽屉，侧滑栏  */
    private void initTopView() {
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

        //抽屉布局及侧滑栏
        mDrawerLayout = findViewById(R.id.layout_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //侧滑栏
        NavigationView mNavView = findViewById(R.id.nav_view);
        //mNavView.setCheckedItem(R.id.home); //侧滑栏默认选中的选项
        mNavView.setNavigationItemSelectedListener(mNavItemSelectedListener);    //侧滑栏的点击事件
    }
    private void initMiddleView() {
        //Fragment 声明及构造
        TodoFragment mTodoFragment = new TodoFragment();
        DiaryFragment mDiaryFragment = new DiaryFragment();
        CalendarFragment mCalendarFragment = new CalendarFragment();

        //ViewPager 构造
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.addOnPageChangeListener(pageChangeListener);

        //Fragment List
        fragmentList = new ArrayList<>();
        fragmentList.add(mTodoFragment);
        fragmentList.add(mDiaryFragment);
        fragmentList.add(mCalendarFragment);
    }

    private void initBottomView() {
        //底部导航栏
        mBottomNavigationView = findViewById(R.id.view_bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnBottomNavItemSelectedListener);
    }
    /*  ToolBar 右边菜单创建    */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);   //加载 toolbar_menu
        return true;
    }

    /*  ToolBar 右边菜单点击事件    */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: //点击抽屉按钮打开抽屉
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.none:
                Toast.makeText(this, "Click None", Toast.LENGTH_LONG).show();
                break;
            case R.id.none2:
                Toast.makeText(this, "Click None2", Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }

    /*  ViewPager 点击事件   */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            switch (i){
                case 0:
                    mBottomNavigationView.setSelectedItemId(R.id.bottom_nav_todo);
                    break;
                case 1:
                    mBottomNavigationView.setSelectedItemId(R.id.bottom_nav_diary);
                    break;
                case 2:
                    mBottomNavigationView.setSelectedItemId(R.id.bottom_nav_calendar);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    /*  抽屉点击事件    */
    private NavigationView.OnNavigationItemSelectedListener mNavItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.home:
                    Toast.makeText(MainActivity.this, "Click Home", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.setting:
                    Toast.makeText(MainActivity.this, "Click Setting", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.about:
                    AboutActivity.actionStart(MainActivity.this);
                    mDrawerLayout.closeDrawers();
                    return true;
            }
            return false;
        }
    };

    /*  底部导航栏点击事件*/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnBottomNavItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_nav_todo:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.bottom_nav_diary:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.bottom_nav_calendar:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

}


