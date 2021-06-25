package travelManagement.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CategoryQueryReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/25 下午8:08
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryQueryReq extends PageReq {
    private Long cid;

    private String cname;
}
