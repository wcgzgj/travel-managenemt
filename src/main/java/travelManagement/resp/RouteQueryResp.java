package travelManagement.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName UserQueryResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:29
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteQueryResp {
    private Long rid;

    private String rname;

    private Double price;

    private String routeintroduce;

    private String rflag;

    private String rdate;

    private String isthemetour;

    private Integer count;

    private Long cid;

    private String cname;

    private String rimage;

    private Long sid;

    private String sname;

    private String sourceid;
}
