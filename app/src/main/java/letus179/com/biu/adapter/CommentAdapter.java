package letus179.com.biu.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import letus179.com.biu.R;
import letus179.com.biu.bean.Comment;
import letus179.com.biu.bean.Reply;
import letus179.com.biu.view.NoScrollListView;

/**
 * Created by xfyin on 2017/10/18.
 */

public class CommentAdapter extends BaseAdapter {

    private int commentResourceId;

    private int replyResourceId;

    private Context context;

    private List<Comment> commentList;

    private Map<Integer, Boolean> isVisible;

    private Handler handler;

    public CommentAdapter(int commentResourceId, int replyResourceId, Context context, List<Comment> commentList, Handler handler) {
        this.commentResourceId = commentResourceId;
        this.replyResourceId = replyResourceId;
        this.context = context;
        this.commentList = commentList;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Comment getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comment comment = commentList.get(position);
        View view;
        CommentHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(commentResourceId, parent, false);
            holder = new CommentHolder();
            holder.dynamic_comment_img = (ImageView) view.findViewById(R.id.dynamic_comment_img);
            holder.dynamic_comment_zan_img = (ImageView) view.findViewById(R.id.dynamic_comment_zan_img);
            holder.dynamic_comment_dialog = (ImageView) view.findViewById(R.id.dynamic_comment_dialog);
            holder.dynamic_comment_name = (TextView) view.findViewById(R.id.dynamic_comment_name);
            holder.dynamic_comment_time = (TextView) view.findViewById(R.id.dynamic_comment_time);
            holder.dynamic_comment_content = (TextView) view.findViewById(R.id.dynamic_comment_content);
            holder.dynamic_comment_zan_num = (TextView) view.findViewById(R.id.dynamic_comment_zan_num);
            holder.dynamic_comment_reply_list = (NoScrollListView) view.findViewById(R.id.dynamic_comment_reply_list);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (CommentHolder) view.getTag();
        }

        holder.dynamic_comment_img.setImageResource(comment.getImageId());
        holder.dynamic_comment_name.setText(comment.getCommentNickname());
        holder.dynamic_comment_time.setText(comment.getCommentTime());
        holder.dynamic_comment_content.setText(comment.getCommentContent());
        holder.dynamic_comment_zan_num.setText(comment.getPraiseNum());


        final ReplyAdapter adapter = new ReplyAdapter(replyResourceId, comment.getReplyList(), context);
        holder.dynamic_comment_reply_list.setAdapter(adapter);
        TextViewClickListener tcl = new TextViewClickListener(position);
        holder.dynamic_comment_img.setOnClickListener(tcl);
        holder.dynamic_comment_dialog.setOnClickListener(tcl);
        holder.dynamic_comment_zan_img.setOnClickListener(tcl);
        holder.dynamic_comment_content.setOnClickListener(tcl);

        return view;
    }

    /**
     * 获取回复评论
     */
    public void getReplyComment(Reply reply, int position) {
        List<Reply> replyList = commentList.get(position).getReplyList();
        replyList.add(replyList.size(), reply);
    }

    private final class CommentHolder {
        // 评论人头像，赞，对话框（删除等）
        public ImageView dynamic_comment_img, dynamic_comment_zan_img, dynamic_comment_dialog;
        //评论人昵称,评论时间，评论内容， 点赞数
        public TextView dynamic_comment_name, dynamic_comment_time, dynamic_comment_content, dynamic_comment_zan_num;
        //评论回复列表
        public NoScrollListView dynamic_comment_reply_list;


    }

    /**
     * 事件点击监听器
     */
    private final class TextViewClickListener implements View.OnClickListener {

        private int position;

        public TextViewClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dynamic_comment_content:
                    handler.sendMessage(handler.obtainMessage(10, position));
                    break;
                case R.id.dynamic_comment_zan_img:
                    if (isVisible.get(commentList.get(position).getId())) {
                        isVisible.put(commentList.get(position).getId(), false);
                    } else {
                        isVisible.put(commentList.get(position).getId(), true);
                    }
                    notifyDataSetChanged();
                    break;
                case R.id.dynamic_comment_dialog:
                    // TODO: 2017/10/18 open dialog
                    //                    handler.sendMessage(handler.obtainMessage(11, position));
                    break;
            }
        }
    }
}
