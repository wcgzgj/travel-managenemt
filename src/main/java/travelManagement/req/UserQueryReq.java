package travelManagement.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class UserQueryReq extends PageReq{
    private Long uid;

    private String name;

    private String email;

}
