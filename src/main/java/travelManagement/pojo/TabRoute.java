package travelManagement.pojo;

public class TabRoute {
    private Long rid;

    private String rname;

    private Double price;

    private String routeintroduce;

    private String rflag;

    private String rdate;

    private String isthemetour;

    private Integer count;

    private Long cid;

    private String rimage;

    private Long sid;

    private String sourceid;

    private String isThemeTour;

    private String routeIntroduce;

    private String sourceId;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRouteintroduce() {
        return routeintroduce;
    }

    public void setRouteintroduce(String routeintroduce) {
        this.routeintroduce = routeintroduce;
    }

    public String getRflag() {
        return rflag;
    }

    public void setRflag(String rflag) {
        this.rflag = rflag;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getIsthemetour() {
        return isthemetour;
    }

    public void setIsthemetour(String isthemetour) {
        this.isthemetour = isthemetour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rid=").append(rid);
        sb.append(", rname=").append(rname);
        sb.append(", price=").append(price);
        sb.append(", routeintroduce=").append(routeintroduce);
        sb.append(", rflag=").append(rflag);
        sb.append(", rdate=").append(rdate);
        sb.append(", isthemetour=").append(isthemetour);
        sb.append(", count=").append(count);
        sb.append(", cid=").append(cid);
        sb.append(", rimage=").append(rimage);
        sb.append(", sid=").append(sid);
        sb.append(", sourceid=").append(sourceid);
        sb.append(", isThemeTour=").append(isThemeTour);
        sb.append(", routeIntroduce=").append(routeIntroduce);
        sb.append(", sourceId=").append(sourceId);
        sb.append("]");
        return sb.toString();
    }
}