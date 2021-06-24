package travelManagement.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName UserSaveReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:22
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSaveReq {
    private Long uid;

    private String username;

    private String password;

    private String name;

    private Date birthday;

    private String sex;

    private String telephone;

    private String email;

    private String status;

    private String code;

    private Boolean isadmin;
}
