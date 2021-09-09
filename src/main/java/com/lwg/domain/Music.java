package com.lwg.domain;

import java.io.Serializable;
import java.util.Date;

public class Music implements Serializable {
    private Integer musicid;

    private String musicname;

    private String musicphotourl;

    private Integer singerid;

    private Integer musichot;

    private String lyricurl;

    private Integer musictypeid;

    private String songurl;

    private Date time;

    private String musictime;

    private Singer singer;

    private Musictype musictype;

    private static final long serialVersionUID = 1L;

    public Integer getMusicid() {
        return musicid;
    }

    public void setMusicid(Integer musicid) {
        this.musicid = musicid;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname == null ? null : musicname.trim();
    }

    public String getMusicphotourl() {
        return musicphotourl;
    }

    public void setMusicphotourl(String musicphotourl) {
        this.musicphotourl = musicphotourl == null ? null : musicphotourl.trim();
    }

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public Integer getMusichot() {
        return musichot;
    }

    public void setMusichot(Integer musichot) {
        this.musichot = musichot;
    }

    public String getLyricurl() {
        return lyricurl;
    }

    public void setLyricurl(String lyricurl) {
        this.lyricurl = lyricurl == null ? null : lyricurl.trim();
    }

    public Integer getMusictypeid() {
        return musictypeid;
    }

    public void setMusictypeid(Integer musictypeid) {
        this.musictypeid = musictypeid;
    }

    public String getSongurl() {
        return songurl;
    }

    public void setSongurl(String songurl) {
        this.songurl = songurl == null ? null : songurl.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMusictime() {
        return musictime;
    }

    public void setMusictime(String musictime) {
        this.musictime = musictime == null ? null : musictime.trim();
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Musictype getMusictype() {
        return musictype;
    }

    public void setMusictype(Musictype musictype) {
        this.musictype = musictype;
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
        Music other = (Music) that;
        return (this.getMusicid() == null ? other.getMusicid() == null : this.getMusicid().equals(other.getMusicid()))
            && (this.getMusicname() == null ? other.getMusicname() == null : this.getMusicname().equals(other.getMusicname()))
            && (this.getMusicphotourl() == null ? other.getMusicphotourl() == null : this.getMusicphotourl().equals(other.getMusicphotourl()))
            && (this.getSingerid() == null ? other.getSingerid() == null : this.getSingerid().equals(other.getSingerid()))
            && (this.getMusichot() == null ? other.getMusichot() == null : this.getMusichot().equals(other.getMusichot()))
            && (this.getLyricurl() == null ? other.getLyricurl() == null : this.getLyricurl().equals(other.getLyricurl()))
            && (this.getMusictypeid() == null ? other.getMusictypeid() == null : this.getMusictypeid().equals(other.getMusictypeid()))
            && (this.getSongurl() == null ? other.getSongurl() == null : this.getSongurl().equals(other.getSongurl()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getMusictime() == null ? other.getMusictime() == null : this.getMusictime().equals(other.getMusictime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMusicid() == null) ? 0 : getMusicid().hashCode());
        result = prime * result + ((getMusicname() == null) ? 0 : getMusicname().hashCode());
        result = prime * result + ((getMusicphotourl() == null) ? 0 : getMusicphotourl().hashCode());
        result = prime * result + ((getSingerid() == null) ? 0 : getSingerid().hashCode());
        result = prime * result + ((getMusichot() == null) ? 0 : getMusichot().hashCode());
        result = prime * result + ((getLyricurl() == null) ? 0 : getLyricurl().hashCode());
        result = prime * result + ((getMusictypeid() == null) ? 0 : getMusictypeid().hashCode());
        result = prime * result + ((getSongurl() == null) ? 0 : getSongurl().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getMusictime() == null) ? 0 : getMusictime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", musicid=").append(musicid);
        sb.append(", musicname=").append(musicname);
        sb.append(", musicphotourl=").append(musicphotourl);
        sb.append(", singerid=").append(singerid);
        sb.append(", musichot=").append(musichot);
        sb.append(", lyricurl=").append(lyricurl);
        sb.append(", musictypeid=").append(musictypeid);
        sb.append(", songurl=").append(songurl);
        sb.append(", time=").append(time);
        sb.append(", musictime=").append(musictime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}