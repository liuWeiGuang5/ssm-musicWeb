package com.lwg.domain;

import java.io.Serializable;

public class Singertype implements Serializable {
    private Integer typeid;

    private String typename;

    private String photourl;

    private static final long serialVersionUID = 1L;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl == null ? null : photourl.trim();
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
        Singertype other = (Singertype) that;
        return (this.getTypeid() == null ? other.getTypeid() == null : this.getTypeid().equals(other.getTypeid()))
            && (this.getTypename() == null ? other.getTypename() == null : this.getTypename().equals(other.getTypename()))
            && (this.getPhotourl() == null ? other.getPhotourl() == null : this.getPhotourl().equals(other.getPhotourl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTypeid() == null) ? 0 : getTypeid().hashCode());
        result = prime * result + ((getTypename() == null) ? 0 : getTypename().hashCode());
        result = prime * result + ((getPhotourl() == null) ? 0 : getPhotourl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", typeid=").append(typeid);
        sb.append(", typename=").append(typename);
        sb.append(", photourl=").append(photourl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}