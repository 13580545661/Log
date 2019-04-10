package com.weacadt.log.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.weacadt.log.R;

public class AddTodoActivity extends Activity implements View.OnClickListener {

    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    private Button button;
    private EditText editText;

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


        init();
    }

    @Override
    public void finish() {
        super.finish();
        //finish时调用退出动画
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddTodoActivity.class);
        context.startActivity(intent);
    }

    private void init() {
        button = findViewById(R.id.btn_add_todo);
        editText = findViewById(R.id.et_todo);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_todo:
                Intent intent = new Intent(AddTodoActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("todo", editText.getText().toString());
                intent.putExtras(bundle);
                setResult(2, intent);
                finish();
        }
    }
}
