package com.lwg.domain;

import java.io.Serializable;

public class Singer implements Serializable {
    private Integer singerid;

    private String singername;

    private String singerphotourl;

    private Integer singerhot;

    private Integer typeid;

    private String address;

    private Singertype singertype;

    private static final long serialVersionUID = 1L;

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername == null ? null : singername.trim();
    }

    public String getSingerphotourl() {
        return singerphotourl;
    }

    public void setSingerphotourl(String singerphotourl) {
        this.singerphotourl = singerphotourl == null ? null : singerphotourl.trim();
    }

    public Integer getSingerhot() {
        return singerhot;
    }

    public void setSingerhot(Integer singerhot) {
        this.singerhot = singerhot;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Singertype getSingertype() {
        return singertype;
    }

    public void setSingertype(Singertype singertype) {
        this.singertype = singertype;
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
        Singer other = (Singer) that;
        return (this.getSingerid() == null ? other.getSingerid() == null : this.getSingerid().equals(other.getSingerid()))
            && (this.getSingername() == null ? other.getSingername() == null : this.getSingername().equals(other.getSingername()))
            && (this.getSingerphotourl() == null ? other.getSingerphotourl() == null : this.getSingerphotourl().equals(other.getSingerphotourl()))
            && (this.getSingerhot() == null ? other.getSingerhot() == null : this.getSingerhot().equals(other.getSingerhot()))
            && (this.getTypeid() == null ? other.getTypeid() == null : this.getTypeid().equals(other.getTypeid()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSingerid() == null) ? 0 : getSingerid().hashCode());
        result = prime * result + ((getSingername() == null) ? 0 : getSingername().hashCode());
        result = prime * result + ((getSingerphotourl() == null) ? 0 : getSingerphotourl().hashCode());
        result = prime * result + ((getSingerhot() == null) ? 0 : getSingerhot().hashCode());
        result = prime * result + ((getTypeid() == null) ? 0 : getTypeid().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        return result;
    }

    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", singerid=").append(singerid);
        sb.append(", singername=").append(singername);
        sb.append(", singerphotourl=").append(singerphotourl);
        sb.append(", singerhot=").append(singerhot);
        sb.append(", typeid=").append(typeid);
        sb.append(", address=").append(address);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }*/

    @Override
    public String toString() {
        return "Singer{" +
                "singerid=" + singerid +
                ", singername='" + singername + '\'' +
                ", singerphotourl='" + singerphotourl + '\'' +
                ", singerhot=" + singerhot +
                ", typeid=" + typeid +
                ", address='" + address + '\'' +
                ", singertype=" + singertype +
                '}';
    }
}