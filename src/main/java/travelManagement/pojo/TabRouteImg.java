package travelManagement.pojo;

public class TabRouteImg {
    private Long rgid;

    private Long rid;

    private String bigpic;

    private String smallpic;

    public Long getRgid() {
        return rgid;
    }

    public void setRgid(Long rgid) {
        this.rgid = rgid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getBigpic() {
        return bigpic;
    }

    public void setBigpic(String bigpic) {
        this.bigpic = bigpic;
    }

    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallpic) {
        this.smallpic = smallpic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rgid=").append(rgid);
        sb.append(", rid=").append(rid);
        sb.append(", bigpic=").append(bigpic);
        sb.append(", smallpic=").append(smallpic);
        sb.append("]");
        return sb.toString();
    }
}