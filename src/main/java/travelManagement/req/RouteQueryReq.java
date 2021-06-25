package travelManagement.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserQueryReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:19
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteQueryReq extends PageReq{
    private Long rid;

    private String rname;

    private String routeintroduce;

    private Long cid;

    private Long sid;

}
