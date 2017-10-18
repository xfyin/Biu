package letus179.com.biu.navigation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import letus179.com.biu.adapter.DynamicAdapter;
import letus179.com.biu.bean.Comment;
import letus179.com.biu.bean.Dynamic;
import letus179.com.biu.bean.Reply;
import letus179.com.biu.dynamic.DynamicActivity;
import letus179.com.biu.R;

public class DynamicFragment extends Fragment implements View.OnClickListener {

    private TextView dynamic_title_action;

    private List<Dynamic> dynamicList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dynamic_fragment, container, false);

        // 标题
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dynamic_title);
        ((TextView) linearLayout.findViewById(R.id.dynamic_title_name)).setText("你好，小姐姐");
        dynamic_title_action = (TextView) linearLayout.findViewById(R.id.dynamic_title_action);
        dynamic_title_action.setOnClickListener(this);
        dynamic_title_action.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(view.getContext(), DynamicActivity.class);
                intent.putExtra("no_pic", true);
                startActivity(intent);
                getActivity(). overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
                return false;
            }
        });


        initDyanmicList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dynamic_list);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(manager);

        DynamicAdapter adapter = new DynamicAdapter(dynamicList, R.layout.dynamic_fragmet_item);
        recyclerView.setAdapter(adapter);
        return view;
    }


    private void initDyanmicList() {
        dynamicList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Dynamic d = new Dynamic();
            d.setId(i);
            d.setNickName("你好，小姐姐"+i);
            d.setTime("23:" + i*10);
            d.setLocation("北京");
            d.setCommentNum(99);
            d.setContent("今天是周三，快到周四了");
            d.setLikeNum(88);
            d.setCommentList(getComments());
            d.setLikeImageId(new int[]{R.drawable.glod2});
            d.setContentImgIds(new int[]{R.drawable.home2});
            d.setReminds(new String[]{"小王", "小明", "小明王"});
            dynamicList.add(d);
        }
    }

    private List<Comment> getComments() {
        List<Comment> cs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Comment com = new Comment();
            com.setCommentTime("25:" + 5*i);
            com.setId(i);
            com.setCommentContent("大家好才是真的好");
            com.setCommentNickname("我是你大爷");
            com.setPraiseNum("444");
            com.setReplyList(getReplys());
            cs.add(com);
        }
        return cs;
    }

    private List<Reply> getReplys() {
        List<Reply> rs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Reply re = new Reply();
            re.setId(i);
            re.setCommentNickname("我是你爸爸");
            re.setReplyContent("快到12点了");
            re.setPraiseNum("22");
            rs.add(re);
        }
        return rs;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dynamic_title_action:
                showDynamicActionDialog();
                break;
            default:
                break;
        }
    }

    private void initViewAndClick() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getActivity(), "requestCode:" + requestCode, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示 发布动态 的动作
     */
    private void showDynamicActionDialog() {
        Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_for_title, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.show();
    }
}
