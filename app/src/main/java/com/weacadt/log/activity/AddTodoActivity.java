package com.weacadt.log.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import com.weacadt.log.R;

public class AddTodoActivity extends Activity {

    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[] {android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();
        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[] {android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();

        //设置布局在底部
        getWindow().setGravity(Gravity.BOTTOM);
        //设置布局填充满宽度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void finish() {
        super.finish();
        //finish时调用退出动画
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }
}
