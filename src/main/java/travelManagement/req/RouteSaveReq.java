package travelManagement.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName RouteSaveReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:22
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteSaveReq {
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
}
