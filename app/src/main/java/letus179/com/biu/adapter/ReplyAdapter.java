package letus179.com.biu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import letus179.com.biu.R;
import letus179.com.biu.bean.Reply;

/**
 * Created by xfyin on 2017/10/18.
 */

public class ReplyAdapter extends BaseAdapter {

    private int resourceId;
    private List<Reply> replyList;
    private SpannableString ss;
    private Context context;

    public ReplyAdapter(int resourceId, List<Reply> replyList, Context context) {
        this.resourceId = resourceId;
        this.replyList = replyList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return replyList.size();
    }

    @Override
    public Reply getItem(int position) {
        return replyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Reply reply = replyList.get(position);
        View view;
        RelayHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false);
            holder = new RelayHolder();
            holder.dynamic_reply_content = (TextView) view.findViewById(R.id.dynamic_reply_content);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (RelayHolder) view.getTag();

        }
        String replyNickname = reply.getReplyNickname();
        String commentNickname = reply.getCommentNickname();
        int replyLen = replyNickname.length();
        int commentLen = commentNickname.length();

        //用来标识在 Span 范围内的文本前后输入新的字符时是否把它们也应用这个效果
        //Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)
        //Spanned.SPAN_INCLUSIVE_EXCLUSIVE(前面包括，后面不包括)
        //Spanned.SPAN_EXCLUSIVE_INCLUSIVE(前面不包括，后面包括)
        //Spanned.SPAN_INCLUSIVE_INCLUSIVE(前后都包括)
        ss = new SpannableString(replyNickname + "回复" + commentNickname
                + "：" + reply.getReplyContent());
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 0,
                replyLen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), replyLen + 2,
                replyLen + commentLen + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //为回复的人昵称添加点击事件
        ss.setSpan(new TextSpanClick(true), 0,
                replyLen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //为评论的人的添加点击事件
        ss.setSpan(new TextSpanClick(false), replyLen + 2,
                replyLen + commentLen + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.dynamic_reply_content.setText(ss);
        //添加点击事件时，必须设置
        holder.dynamic_reply_content.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }

    private class RelayHolder {
        TextView dynamic_reply_content;
    }


    private final class TextSpanClick extends ClickableSpan {
        private boolean status;

        public TextSpanClick(boolean status) {
            this.status = status;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);//取消下划线
        }

        @Override
        public void onClick(View v) {
            // TODO: 2017/10/18 进入用户界面 
            String msgStr;
            if (status) {
                msgStr = "我是回复的人";
            } else {
                msgStr = "我是评论的人";
            }
            Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show();
        }
    }
}
