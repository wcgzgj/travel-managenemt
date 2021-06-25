package travelManagement.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CategoryQueryResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/25 下午8:09
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryQueryResp {
    private Long cid;

    private String cname;
}
