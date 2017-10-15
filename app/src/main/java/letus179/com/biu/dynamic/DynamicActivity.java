package letus179.com.biu.dynamic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.necer.ndialog.NDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import letus179.com.biu.MainActivity;
import letus179.com.biu.R;
import letus179.com.biu.adapter.HorizontalListViewAdapter;
import letus179.com.biu.bean.Contacts;
import letus179.com.biu.common.BasicActivity;
import letus179.com.biu.common.Constants;
import letus179.com.biu.view.HorizontalListView;

public class DynamicActivity extends BasicActivity implements View.OnClickListener {

    private static final String TAG = "DynamicActivity";

    // 取消，发布
    private TextView dynamic_action_cancel, dynamic_action_save;

    // 展示图片,@提醒谁看
    private LinearLayout dynamic_pictures_about;

    private View dynamic_remind;

    // 动态内容文字
    private MaterialEditText dynamic_content_text;

    // 选中的需要@的人
    private List<Contacts> checkedContactList = new ArrayList<>();

    // @谁，横向滚动条列表
    private HorizontalListView dynamic_remind_person;

    // 横向滚动条 适配器
    private HorizontalListViewAdapter hListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        // 初始化
        initViewAndClick();

        initHorizontalView();

        boolean no_pic = getIntent().getBooleanExtra("no_pic", false);
        if (no_pic) {
            dynamic_pictures_about.setVisibility(View.GONE);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("dynamic_edit", MODE_PRIVATE);
        dynamic_content_text.setText(sharedPreferences.getString("dynamic_content_text", ""));

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.dynamic_action_cancel:
                showCancelDialog();
                break;
            case R.id.dynamic_action_save:
                Log.d(TAG, "onClick: " + dynamic_content_text);
                if (TextUtils.isEmpty(dynamic_content_text.getText() + "")) {
                    Toast.makeText(DynamicActivity.this, "留下点什么吧~", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: 2017/10/14
                // 处理完后,返回动态页面
                intent = new Intent(DynamicActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                SharedPreferences.Editor editor = getSharedPreferences("dynamic_edit", MODE_PRIVATE).edit();
                editor.putString("dynamic_content_text", "");
                editor.apply();
                finish();
                dynamic_content_text.setText(null);
                break;
            case R.id.dynamic_remind:
                intent = new Intent(DynamicActivity.this, DynamicRemindActivity.class);
                intent.putExtra("title", "提醒谁看");
                if (checkedContactList != null && checkedContactList.size() >0) {
                    String json = new Gson().toJson(checkedContactList);
                    intent.putExtra("checked_contacts", json);
                }
                this.startActivityForResult(intent, Constants.DYNAMIC_REMIND);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.DYNAMIC_REMIND:
                    String contacts = data.getStringExtra("checked_contacts");
                    checkedContactList = new Gson().fromJson(contacts, new TypeToken<ArrayList<Contacts>>() {
                    }.getType());
                    initHorizontalView();
                    hListViewAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 即将要发布的动态文字部分
        if (dynamic_content_text.getText() != null) {
            SharedPreferences.Editor editor = getSharedPreferences("dynamic_edit", MODE_PRIVATE).edit();
            editor.putString("dynamic_content_text", dynamic_content_text.getText().toString());
            editor.apply();
        }
    }

    private void initViewAndClick() {
        dynamic_action_cancel = (TextView) findViewById(R.id.dynamic_action_cancel);
        dynamic_action_save = (TextView) findViewById(R.id.dynamic_action_save);
        dynamic_pictures_about = (LinearLayout) findViewById(R.id.dynamic_pictures_about);
        dynamic_remind = findViewById(R.id.dynamic_remind);
        dynamic_content_text = (MaterialEditText) findViewById(R.id.dynamic_content_text);

        dynamic_action_cancel.setOnClickListener(this);
        dynamic_action_save.setOnClickListener(this);
        dynamic_remind.setOnClickListener(this);


    }

    private void initHorizontalView() {
        dynamic_remind_person = (HorizontalListView) findViewById(R.id.dynamic_remind_person);
        dynamic_remind_person.bringToFront();
        hListViewAdapter = new HorizontalListViewAdapter(DynamicActivity.this, checkedContactList);
        dynamic_remind_person.setAdapter(hListViewAdapter);
        dynamic_remind_person.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkedContactList.remove(checkedContactList.get(position));
                hListViewAdapter.setSelectIndex(position);
                hListViewAdapter.notifyDataSetChanged();
            }
        });
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
                            overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                            SharedPreferences.Editor editor = getSharedPreferences("dynamic_edit", MODE_PRIVATE).edit();
                            editor.putString("dynamic_content_text", "111");
                            editor.apply();
                            dynamic_content_text.setText(null);
                            finish();
                        }
                    }
                }).create(NDialog.CONFIRM).show();
    }

}
