package letus179.com.biu.bean;

import java.util.List;

/**
 * Created by xfyin on 2017/10/18.
 */

public class Dynamic {

    private int id;

    private int imgId;

    private String nickName;

    private String time;

    private String location;

    private String content;

    private String[] reminds;

    private int commentNum;

    private int[] contentImgIds;

    private int likeNum;

    private int[] likeImageId;

    private List<Comment> commentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int[] getContentImgIds() {
        return contentImgIds;
    }

    public void setContentImgIds(int[] contentImgIds) {
        this.contentImgIds = contentImgIds;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int[] getLikeImageId() {
        return likeImageId;
    }

    public void setLikeImageId(int[] likeImageId) {
        this.likeImageId = likeImageId;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String[] getReminds() {
        return reminds;
    }

    public void setReminds(String[] reminds) {
        this.reminds = reminds;
    }
}
