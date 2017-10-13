package letus179.com.biu.navigation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import letus179.com.biu.DynamicActivity;
import letus179.com.biu.R;

public class DynamicFragment extends Fragment implements View.OnClickListener {

    private TextView dynamic_title_action;

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
                return false;
            }
        });


        // 正文（listView）
        ((TextView) view.findViewById(R.id.fm_text)).setText(FootTabDb.getTabsTxt()[0]);
        return view;
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
