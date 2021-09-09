package com.lwg.domain;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {
    private Integer videoid;

    private String videoname;

    private String videoauthor;

    private String videophoto;

    private String videourl;

    private Date videotime;

    private static final long serialVersionUID = 1L;

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname == null ? null : videoname.trim();
    }

    public String getVideoauthor() {
        return videoauthor;
    }

    public void setVideoauthor(String videoauthor) {
        this.videoauthor = videoauthor == null ? null : videoauthor.trim();
    }

    public String getVideophoto() {
        return videophoto;
    }

    public void setVideophoto(String videophoto) {
        this.videophoto = videophoto == null ? null : videophoto.trim();
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl == null ? null : videourl.trim();
    }

    public Date getVideotime() {
        return videotime;
    }

    public void setVideotime(Date videotime) {
        this.videotime = videotime;
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
        Video other = (Video) that;
        return (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getVideoname() == null ? other.getVideoname() == null : this.getVideoname().equals(other.getVideoname()))
            && (this.getVideoauthor() == null ? other.getVideoauthor() == null : this.getVideoauthor().equals(other.getVideoauthor()))
            && (this.getVideophoto() == null ? other.getVideophoto() == null : this.getVideophoto().equals(other.getVideophoto()))
            && (this.getVideourl() == null ? other.getVideourl() == null : this.getVideourl().equals(other.getVideourl()))
            && (this.getVideotime() == null ? other.getVideotime() == null : this.getVideotime().equals(other.getVideotime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVideoid() == null) ? 0 : getVideoid().hashCode());
        result = prime * result + ((getVideoname() == null) ? 0 : getVideoname().hashCode());
        result = prime * result + ((getVideoauthor() == null) ? 0 : getVideoauthor().hashCode());
        result = prime * result + ((getVideophoto() == null) ? 0 : getVideophoto().hashCode());
        result = prime * result + ((getVideourl() == null) ? 0 : getVideourl().hashCode());
        result = prime * result + ((getVideotime() == null) ? 0 : getVideotime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", videoid=").append(videoid);
        sb.append(", videoname=").append(videoname);
        sb.append(", videoauthor=").append(videoauthor);
        sb.append(", videophoto=").append(videophoto);
        sb.append(", videourl=").append(videourl);
        sb.append(", videotime=").append(videotime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}