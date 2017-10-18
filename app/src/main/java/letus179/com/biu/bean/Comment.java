package letus179.com.biu.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xfyin on 2017/10/18.
 */

public class Comment {

    //评论记录ID
    private int id;

    // 评论人头像
    private int imageId;

    //评论人昵称
    private String commentNickname;

    //评论时间
    private String commentTime;

    //评论内容
    private String commentContent;

    //评论点赞
    private String praiseNum;

    // 该评论下的回复列表
    private List<Reply> replyList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCommentNickname() {
        return commentNickname;
    }

    public void setCommentNickname(String commentNickname) {
        this.commentNickname = commentNickname;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
