package letus179.com.biu.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import letus179.com.biu.R;
import letus179.com.biu.adapter.ContactsAdapter;
import letus179.com.biu.bean.Contacts;
import letus179.com.biu.common.BasicActivity;
import letus179.com.biu.common.MyApplication;
import letus179.com.biu.view.ContactsNavigation;

public class DynamicRemindActivity extends BasicActivity implements OnClickListener, ContactsNavigation.onContactsChangeListener, AbsListView.OnScrollListener {

    private Handler handler;

    // 联系人列表
    private List<Contacts> contactsList;

    // 点击联系列表右侧的字母会在页面中央显示，选中的人数计算（1/6）
    private TextView dynamic_contacts_center_word, dynamic_contacts_choose_num;

    // 联系人列表View
    private ListView dynamic_contacts_list;

    // 联系列表右侧的纵向字母导航
    private ContactsNavigation dynamic_contacts_nav;

    // 联系人列表右上角 选中的人 确定按钮
    private LinearLayout dynamic_contacts_choose;

    // 选中的需要@的人
    private List<Contacts> checkedContactList = new ArrayList<>();

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_remind);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicRemindActivity.this, DynamicActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        String contacts = getIntent().getStringExtra("checked_contacts");
        if (!TextUtils.isEmpty(contacts)) {
            checkedContactList = new Gson().fromJson(contacts, new TypeToken<ArrayList<Contacts>>() {
            }.getType());
        }

        //初始化数据
        initData();

        // 初始化View
        initViewAndClick();

        //设置列表点击滑动监听
        handler = new Handler();
        dynamic_contacts_nav.setOnContactsChangeListener(this);

    }

    /**
     * 初始化View
     */
    private void initViewAndClick() {
        dynamic_contacts_center_word = (TextView) findViewById(R.id.dynamic_contacts_center_word);
        dynamic_contacts_nav = (ContactsNavigation) findViewById(R.id.dynamic_contacts_nav);
        dynamic_contacts_list = (ListView) findViewById(R.id.dynamic_contacts_list);
        dynamic_contacts_choose_num = (TextView) findViewById(R.id.dynamic_contacts_choose_num);
        dynamic_contacts_choose = (LinearLayout) findViewById(R.id.dynamic_contacts_choose);
        dynamic_contacts_choose.setOnClickListener(this);

        // 从写动态返回联系人列表
        dynamic_contacts_choose_num.setText("(" + checkedContactList.size() + "/6)");
        if (checkedContactList != null && checkedContactList.size() > 0) {
            for (Contacts contacts : checkedContactList) {
                for (Contacts contacts1 : contactsList) {
                    if (contacts1.getName().equals(contacts.getName()) && contacts1.getImageId() == contacts.getImageId()) {
                        contacts1.setChecked(true);
                    }
                }
            }
        }

        final ContactsAdapter adapter = new ContactsAdapter(this, contactsList);
        dynamic_contacts_list.setAdapter(adapter);
        dynamic_contacts_list.setOnScrollListener(this);
        dynamic_contacts_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacts contacts = contactsList.get(position);
                if (contacts.isChecked()) {
                    for (Contacts contacts1 : checkedContactList) {
                        if (contacts1.getName().equals(contacts.getName()) && contacts1.getImageId() == contacts.getImageId()) {
                            checkedContactList.remove(contacts1);
                            contacts.setChecked(false);
                            break;
                        }
                    }
                } else {
                    if (checkedContactList.size() > 5) {
                        // 解决多次点击导致Toast不断弹出的问题
                        if (toast != null) {
                            toast.setText("最多只能@6个人哦~");
                            toast.setDuration(Toast.LENGTH_SHORT);
                        } else {
                            toast = Toast.makeText(MyApplication.getContext(), "最多只能@6个人哦~", Toast.LENGTH_SHORT);
                        }
                        toast.show();
                        return;
                    }
                    contacts.setChecked(true);
                    checkedContactList.add(contacts);
                }
                adapter.notifyDataSetChanged();
                dynamic_contacts_choose_num.setText("(" + checkedContactList.size() + "/6)");
            }
        });
    }

    /**
     * 初始化联系人列表信息
     */
    private void initData() {
        // TODO: 2017/10/14
        contactsList = new ArrayList<>();
        contactsList.add(new Contacts("Dave", R.drawable.take_phone));
        contactsList.add(new Contacts("张晓飞", R.drawable.take_phone));
        contactsList.add(new Contacts("杨光福", R.drawable.take_phone));
        contactsList.add(new Contacts("阿钟", R.drawable.take_phone));
        contactsList.add(new Contacts("胡继群", R.drawable.take_phone));
        contactsList.add(new Contacts("徐歌阳", R.drawable.take_phone));
        contactsList.add(new Contacts("钟泽兴", R.drawable.take_phone));
        contactsList.add(new Contacts("宋某人", R.drawable.take_phone));
        contactsList.add(new Contacts("刘某人", R.drawable.take_phone));
        contactsList.add(new Contacts("Tony", R.drawable.take_phone));
        contactsList.add(new Contacts("老刘", R.drawable.take_phone));
        contactsList.add(new Contacts("隔壁老王", R.drawable.take_phone));
        contactsList.add(new Contacts("安传鑫", R.drawable.take_phone));
        contactsList.add(new Contacts("温松", R.drawable.take_phone));
        contactsList.add(new Contacts("成龙", R.drawable.take_phone));
        contactsList.add(new Contacts("那英", R.drawable.take_phone));
        contactsList.add(new Contacts("刘甫", R.drawable.take_phone));
        contactsList.add(new Contacts("沙宝亮", R.drawable.take_phone));
        contactsList.add(new Contacts("张猛", R.drawable.take_phone));
        contactsList.add(new Contacts("张大爷", R.drawable.take_phone));
        contactsList.add(new Contacts("张哥", R.drawable.take_phone));
        contactsList.add(new Contacts("张娃子", R.drawable.take_phone));
        contactsList.add(new Contacts("樟脑丸", R.drawable.take_phone));
        contactsList.add(new Contacts("吴亮", R.drawable.take_phone));
        contactsList.add(new Contacts("Tom", R.drawable.take_phone));
        contactsList.add(new Contacts("阿三", R.drawable.take_phone));
        contactsList.add(new Contacts("肖奈", R.drawable.take_phone));
        contactsList.add(new Contacts("贝微微", R.drawable.take_phone));
        contactsList.add(new Contacts("赵二喜", R.drawable.take_phone));
        contactsList.add(new Contacts("曹光", R.drawable.take_phone));
        contactsList.add(new Contacts("姜宇航", R.drawable.take_phone));

        //对集合排序
        Collections.sort(contactsList, new Comparator<Contacts>() {
            @Override
            public int compare(Contacts lhs, Contacts rhs) {
                //根据拼音进行排序
                return lhs.getPinYin().compareTo(rhs.getPinYin());
            }
        });
    }

    //手指按下字母改变监听回调
    @Override
    public void contactsChange(String words) {
        updateWord(words);
        updateListView(words);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当滑动列表的时候，更新右侧字母列表的选中状态
        dynamic_contacts_nav.setTouchIndex(contactsList.get(firstVisibleItem).getHeaderWord());
    }

    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        for (int i = 0; i < contactsList.size(); i++) {
            String headerWord = contactsList.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                dynamic_contacts_list.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }

    /**
     * 更新中央的字母提示
     *
     * @param words 首字母
     */
    private void updateWord(String words) {
        dynamic_contacts_center_word.setText(words);
        dynamic_contacts_center_word.setVisibility(View.VISIBLE);
        //清空之前的所有消息
        handler.removeCallbacksAndMessages(null);
        //1s后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dynamic_contacts_center_word.setVisibility(View.GONE);
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dynamic_contacts_choose:

                String num = dynamic_contacts_choose_num.getText().toString();
                if ("0".equals(num.substring(1, 2))) {
                    Toast.makeText(DynamicRemindActivity.this, "至少选择一个需要@的人", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                String json = new Gson().toJson(checkedContactList);
                intent.putExtra("checked_contacts", json);
                setResult(RESULT_OK, intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                break;
            default:
                break;
        }
    }

}
