package com.weacadt.log.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.weacadt.log.R;
import com.weacadt.log.data.TodoItem;
import com.weacadt.log.fragment.CalendarFragment;
import com.weacadt.log.fragment.DiaryFragment;
import com.weacadt.log.fragment.TodoFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //抽屉
    private DrawerLayout mDrawerLayout;

    //Fragment
    private TodoFragment mTodoFragment;
    private DiaryFragment mDiaryFragment;
    private CalendarFragment mCalendarFragment;

    //ViewPager
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter fpAdapter;

    //底部导航栏
    private BottomNavigationView mBottomNavigationView;

    //悬浮按钮
    private FloatingActionButton fab;

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
        //设置 ActionBar 的默认标题（“待办事项”）
        toolbar.setTitle(R.string.ab_today);

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

        ViewGroup.LayoutParams params = mNavView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels * 3 / 4;
        mNavView.setLayoutParams(params);

    }
    private void initMiddleView() {
        //Fragment 构造
        mTodoFragment = new TodoFragment();
        mDiaryFragment = new DiaryFragment();
        mCalendarFragment = new CalendarFragment();

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

        //悬浮按钮
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        //底部导航栏
        mBottomNavigationView = findViewById(R.id.view_bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnBottomNavItemSelectedListener);
    }


    /**
     * 创建 Toolbar 菜单
     * @param menu menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);   //加载 toolbar_menu，即 toolbar 右边菜单
        return true;
    }

    /**
     * Toolbar 菜单选择监听器
     * @param item 所选的菜单项
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: //点击抽屉按钮打开抽屉
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.about:
                AboutActivity.actionStart(MainActivity.this);
                break;
            default:
        }
        return true;
    }

    /**
     * ViewPager 视图切换监听器
     */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            switch (i){
                case 0:
                    mBottomNavigationView.setSelectedItemId(R.id.bottom_nav_todo);
                    if (!fab.isShown()) {
                        Toast.makeText(MainActivity.this, "fab is not shown", Toast.LENGTH_LONG).show();
                        fab.show();
                    }

                    getSupportActionBar().setTitle(R.string.ab_today);
                    break;
                case 1:
                    mBottomNavigationView.setSelectedItemId(R.id.bottom_nav_diary);
                    if (!fab.isShown()){
                        fab.show();
                    }
                    getSupportActionBar().setTitle(R.string.ab_diary);
                    break;
                case 2:
                    mBottomNavigationView.setSelectedItemId(R.id.bottom_nav_calendar);
                    fab.hide();
                    getSupportActionBar().setTitle(R.string.ab_calendar);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    /**
     * 侧滑栏选择监听器
     */
    private NavigationView.OnNavigationItemSelectedListener mNavItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.setting:
                    SettingActivity.actionStart(MainActivity.this);
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

    /**
     * 底部导航选择监听器
     */
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

    /**
     * 点击监听器
     * @param view view
     */
    @Override
    public void onClick(View view) {
        int viewPager = mViewPager.getCurrentItem();
        switch (view.getId()){
            case R.id.fab:
                switch(viewPager) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                        startActivityForResult(intent, 0);
                        break;
                    case 1:
                        AddDiaryActivity.actionStart(MainActivity.this);
                        break;
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                Bundle bundle = data.getExtras();
                String todo = bundle.getString("todo");
                if (todo.length() != 0) {
                    mTodoFragment.addItem(new TodoItem(todo));
                }
        }

    }
}


