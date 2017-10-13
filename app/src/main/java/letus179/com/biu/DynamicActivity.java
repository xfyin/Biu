package letus179.com.biu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.necer.ndialog.NDialog;

import letus179.com.biu.common.BasicActivity;

public class DynamicActivity extends BasicActivity implements View.OnClickListener {

    // 取消，发布
    private TextView dynamic_action_cancel, dynamic_action_save;

    // 展示图片
    private LinearLayout dynamic_pictures_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        // 初始化
        initViewAndClick();

        boolean no_pic = getIntent().getBooleanExtra("no_pic", false);
        if (no_pic) {
            dynamic_pictures_about.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dynamic_action_cancel:
                showCancelDialog();
                break;
            case R.id.dynamic_action_save:
                // TODO: 2017/10/14
                // 处理完后,返回动态页面
                Intent intent = new Intent(DynamicActivity.this, MainActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }
    }

    private void initViewAndClick() {
        dynamic_action_cancel = (TextView) findViewById(R.id.dynamic_action_cancel);
        dynamic_action_save = (TextView) findViewById(R.id.dynamic_action_save);
        dynamic_pictures_about = (LinearLayout) findViewById(R.id.dynamic_pictures_about);

        dynamic_action_cancel.setOnClickListener(this);
        dynamic_action_save.setOnClickListener(this);
    }

    /**
     * 点击“取消”按钮，弹出确认对话框
     */
    private void showCancelDialog() {
        new NDialog(this)
                .setMessageCenter(false)
                .setMessage("是否放弃编辑？")
                .setMessageSize(14)
                .setMessageCenter(true)
                .setPositiveTextColor(Color.parseColor("#20B2AA"))
                .setNegativeTextColor(Color.parseColor("#20B2AA"))
                .setPositiveButtonText("确定")
                .setNegativeButtonText("取消")
                .setButtonCenter(true)
                .setButtonSize(14)
                .setCancleable(true)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        if (which == 1) {
                            Intent intent = new Intent(DynamicActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }).create(NDialog.CONFIRM).show();
    }
}
