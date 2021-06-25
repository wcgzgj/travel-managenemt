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
public class UserQueryResp {
    private Long uid;

    private String username;

    private String password;

    private String name;

    private Date birthday;

    private String birthdayStr;

    private String sex;

    private String telephone;

    private String email;

    private String status;

    private String code;

    private Boolean isadmin;
}
