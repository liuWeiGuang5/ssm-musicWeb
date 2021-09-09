package com.lwg.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Integer messageid;

    private String messagecomment;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date messagetime;

    private Integer videoid;

    private Integer uid;

    private User user;

    private Video video;

    private static final long serialVersionUID = 1L;

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getMessagecomment() {
        return messagecomment;
    }

    public void setMessagecomment(String messagecomment) {
        this.messagecomment = messagecomment == null ? null : messagecomment.trim();
    }

    public Date getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(Date messagetime) {
        this.messagetime = messagetime;
    }

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Message other = (Message) that;
        return (this.getMessageid() == null ? other.getMessageid() == null : this.getMessageid().equals(other.getMessageid()))
            && (this.getMessagecomment() == null ? other.getMessagecomment() == null : this.getMessagecomment().equals(other.getMessagecomment()))
            && (this.getMessagetime() == null ? other.getMessagetime() == null : this.getMessagetime().equals(other.getMessagetime()))
            && (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMessageid() == null) ? 0 : getMessageid().hashCode());
        result = prime * result + ((getMessagecomment() == null) ? 0 : getMessagecomment().hashCode());
        result = prime * result + ((getMessagetime() == null) ? 0 : getMessagetime().hashCode());
        result = prime * result + ((getVideoid() == null) ? 0 : getVideoid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageid=").append(messageid);
        sb.append(", messagecomment=").append(messagecomment);
        sb.append(", messagetime=").append(messagetime);
        sb.append(", videoid=").append(videoid);
        sb.append(", uid=").append(uid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}