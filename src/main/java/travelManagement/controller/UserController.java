package travelManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelManagement.mapper.TabUserMapper;
import travelManagement.pojo.TabUser;
import travelManagement.req.UserQueryReq;
import travelManagement.resp.CommonResp;
import travelManagement.resp.PageResp;
import travelManagement.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG= LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private TabUserMapper tabUserMapper;

    @GetMapping("/list")
    public CommonResp list(UserQueryReq req) {
        LOG.info("传入的查询参数为:{}",req);
        PageResp pageResp = userService.list(req);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(pageResp);

        return commonResp;
    }
}
