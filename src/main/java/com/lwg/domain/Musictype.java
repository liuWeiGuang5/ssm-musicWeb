package com.lwg.domain;

import java.io.Serializable;

public class Musictype implements Serializable {
    private Integer musictypeid;

    private String musictypename;

    private static final long serialVersionUID = 1L;

    public Integer getMusictypeid() {
        return musictypeid;
    }

    public void setMusictypeid(Integer musictypeid) {
        this.musictypeid = musictypeid;
    }

    public String getMusictypename() {
        return musictypename;
    }

    public void setMusictypename(String musictypename) {
        this.musictypename = musictypename == null ? null : musictypename.trim();
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
        Musictype other = (Musictype) that;
        return (this.getMusictypeid() == null ? other.getMusictypeid() == null : this.getMusictypeid().equals(other.getMusictypeid()))
            && (this.getMusictypename() == null ? other.getMusictypename() == null : this.getMusictypename().equals(other.getMusictypename()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMusictypeid() == null) ? 0 : getMusictypeid().hashCode());
        result = prime * result + ((getMusictypename() == null) ? 0 : getMusictypename().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", musictypeid=").append(musictypeid);
        sb.append(", musictypename=").append(musictypename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}