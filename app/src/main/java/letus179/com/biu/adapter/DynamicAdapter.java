package letus179.com.biu.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import letus179.com.biu.R;
import letus179.com.biu.bean.Comment;
import letus179.com.biu.bean.Dynamic;
import letus179.com.biu.utils.ToastUtils;

/**
 * Created by xfyin on 2017/10/18.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.DynamicHolder> {

    private List<Dynamic> dynamicList;

    private int resourceId;

    private Context context;

    public DynamicAdapter(List<Dynamic> dynamicList, int resourceId) {
        this.dynamicList = dynamicList;
        this.resourceId = resourceId;
    }

    @Override
    public DynamicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(resourceId, parent, false);
        return new DynamicHolder(view);
    }

    @Override
    public void onBindViewHolder(DynamicHolder holder, int position) {
        Dynamic dynamic = dynamicList.get(position);
        holder.dynamic_my_img.setImageResource(dynamic.getImgId());
        holder.dynamic_my_pics.setImageResource(dynamic.getContentImgIds()[0]);
        holder.dynamic_my_name.setText(dynamic.getNickName());
        holder.dynamic_my_time.setText(dynamic.getTime());
        holder.dynamic_my_location.setText(dynamic.getLocation());
        holder.dynamic_my_content.setText(dynamic.getContent());
        holder.dynamic_my_praise_num.setText(dynamic.getLikeNum() + "");

        List<Comment> commentList = dynamic.getCommentList();
        StringBuilder sb = new StringBuilder();
        for (Comment comment : commentList) {
            sb.append(comment.getCommentNickname()).append(":").append(comment.getCommentContent()).append("\n");
        }
        holder.dynamic_my_comment_three.setText(sb.toString());

        DynamicClickListener listener = new DynamicClickListener(position);
        holder.dynamic_my_content_all.setOnClickListener(listener);
        holder.dynamic_my_follow.setOnClickListener(listener);
        holder.dynamic_my_transmit.setOnClickListener(listener);


        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.dynamic_my_reminds.setLayoutManager(manager);
        List<String> remindList = new ArrayList<>();
        String[] reminds = dynamic.getReminds();
        for (String remind : reminds) {
            remindList.add(remind);
        }
        final RemindAdapter remindAdapter = new RemindAdapter(remindList, R.layout.dynamic_fragment_remind_item);
        holder.dynamic_my_reminds.setAdapter(remindAdapter);


    }

    @Override
    public int getItemCount() {
        return dynamicList.size();
    }


    static class DynamicHolder extends RecyclerView.ViewHolder {

        // 用户头像，动态图片
        ImageView dynamic_my_img, dynamic_my_pics;

        // 用户昵称,动态发布时间，关注，所在位置,动态内容，评论数，点赞数，转发,热门评论
        TextView dynamic_my_name, dynamic_my_time, dynamic_my_follow, dynamic_my_location,
                dynamic_my_content, dynamic_my_comment_num, dynamic_my_praise_num, dynamic_my_transmit, dynamic_my_comment_three;

        // 进入动态详情
        LinearLayout dynamic_my_content_all;

        RecyclerView dynamic_my_reminds;

        public DynamicHolder(View view) {
            super(view);
            dynamic_my_img = (ImageView) view.findViewById(R.id.dynamic_my_img);
            dynamic_my_pics = (ImageView) view.findViewById(R.id.dynamic_my_pics);
            dynamic_my_name = (TextView) view.findViewById(R.id.dynamic_my_name);
            dynamic_my_time = (TextView) view.findViewById(R.id.dynamic_my_time);
            dynamic_my_follow = (TextView) view.findViewById(R.id.dynamic_my_follow);
            dynamic_my_location = (TextView) view.findViewById(R.id.dynamic_my_location);
            dynamic_my_content = (TextView) view.findViewById(R.id.dynamic_my_content);
            dynamic_my_comment_num = (TextView) view.findViewById(R.id.dynamic_my_comment_num);
            dynamic_my_praise_num = (TextView) view.findViewById(R.id.dynamic_my_praise_num);
            dynamic_my_transmit = (TextView) view.findViewById(R.id.dynamic_my_transmit);
            dynamic_my_comment_three = (TextView) view.findViewById(R.id.dynamic_my_comment_three);
            dynamic_my_content_all = (LinearLayout) view.findViewById(R.id.dynamic_my_content_all);
            dynamic_my_reminds = (RecyclerView) view.findViewById(R.id.dynamic_my_reminds);
        }
    }

    private class DynamicClickListener implements View.OnClickListener {

        private int position;

        public DynamicClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dynamic_my_follow:
                    ToastUtils.show(v.getContext(), "我要关注他。");
                    break;
                case R.id.dynamic_my_content_all:
                    ToastUtils.show(v.getContext(), "进入动态详情页面");
                    break;
                case R.id.dynamic_my_transmit:
                    ToastUtils.show(v.getContext(), "我要转发这条动态");
                    break;
                default:
                    break;
            }
        }
    }
}
