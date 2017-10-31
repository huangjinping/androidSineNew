package com.sineverything.news.bean.main;

import com.sineverything.news.bean.BaseBean.BaseBean;

import java.util.List;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class Comments extends BaseBean {


    /**
     * userPhoto : http://47.88.169.219:8081/null
     * replys : []
     * commentId : 15
     * replysNum : 0
     * userNickName : 13611290917@phone
     * time : 2017-09-25 15:47:00
     * userId : 97
     * content : 匿名
     */

    private String userPhoto;
    private String commentId;
    private int replysNum;
    private String userNickName;
    private String time;
    private String userId;
    private String content;
    private String cmsId;

    private List<Comments> replys;

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getReplysNum() {
        return replysNum;
    }

    public void setReplysNum(int replysNum) {
        this.replysNum = replysNum;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comments> getReplys() {
        return replys;
    }

    public void setReplys(List<Comments> replys) {
        this.replys = replys;
    }

    public String getCmsId() {
        return cmsId;
    }

    public void setCmsId(String cmsId) {
        this.cmsId = cmsId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "userPhoto='" + userPhoto + '\'' +
                ", commentId='" + commentId + '\'' +
                ", replysNum=" + replysNum +
                ", userNickName='" + userNickName + '\'' +
                ", time='" + time + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", replys=" + replys +
                '}';
    }
}
