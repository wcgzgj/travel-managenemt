package travelManagement.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CategorySaveReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/25 下午8:09
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellerSaveReq {
    private Long sid;

    private String sname;

    private String consphone;

    private String address;
}
