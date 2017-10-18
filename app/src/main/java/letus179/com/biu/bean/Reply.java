package letus179.com.biu.bean;

/**
 * Created by xfyin on 2017/10/18.
 */

public class Reply {

    //内容ID
    private int id;

    // 回复人头像
    private int imageId;

    //回复人昵称
    private String replyNickname;

    //被回复人账号
    private String commentAccount;

    //被回复人昵称
    private String commentNickname;

    //回复的内容
    private String replyContent;

    //回复点赞
    private String praiseNum;

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

    public String getReplyNickname() {
        return replyNickname;
    }

    public void setReplyNickname(String replyNickname) {
        this.replyNickname = replyNickname;
    }

    public String getCommentAccount() {
        return commentAccount;
    }

    public void setCommentAccount(String commentAccount) {
        this.commentAccount = commentAccount;
    }

    public String getCommentNickname() {
        return commentNickname;
    }

    public void setCommentNickname(String commentNickname) {
        this.commentNickname = commentNickname;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }
}
