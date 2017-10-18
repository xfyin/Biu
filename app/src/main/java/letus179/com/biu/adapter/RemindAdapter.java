package letus179.com.biu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import letus179.com.biu.R;
import letus179.com.biu.utils.ToastUtils;

/**
 * Created by xfyin on 2017/10/19.
 * <p>
 * 需要@人列表
 */

public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.RemindHolder> {

    private List<String> remindList;

    private int resourceId;

    public RemindAdapter(List<String> remindList, int resourceId) {
        this.remindList = remindList;
        this.resourceId = resourceId;
    }

    @Override
    public RemindHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
        final RemindHolder holder = new RemindHolder(view);
        holder.remindView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String remind = remindList.get(position);
                ToastUtils.show(v.getContext(), "查看@的好友：" + remind);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RemindHolder holder, int position) {
        String remind = remindList.get(position);
        holder.dynamic_remind_item.setText(remind);
    }

    @Override
    public int getItemCount() {
        return remindList.size();
    }

    static class RemindHolder extends RecyclerView.ViewHolder {

        View remindView;
        private TextView dynamic_remind_item;

        public RemindHolder(View itemView) {
            super(itemView);
            remindView = itemView;
            dynamic_remind_item = (TextView) itemView.findViewById(R.id.dynamic_remind_item);
        }
    }
}
