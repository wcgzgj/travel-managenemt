package travelManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelManagement.req.RouteQueryReq;
import travelManagement.resp.CommonResp;
import travelManagement.resp.PageResp;
import travelManagement.resp.RouteQueryResp;
import travelManagement.service.RouteService;

import javax.annotation.Resource;

/**
 * @ClassName RouteController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/route")
public class RouteController {

    private static final Logger LOG= LoggerFactory.getLogger(RouteController.class);

    @Resource
    private RouteService routeService;


    @GetMapping("/list")
    public CommonResp list(RouteQueryReq req) {
        LOG.info("传入的查询参数为:{}",req);
        PageResp pageResp = routeService.list(req);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(pageResp);

        return commonResp;
    }


    @GetMapping("/selectById/{id}")
    public CommonResp selectById(@PathVariable("id")String id) {
        long routeId = Long.parseLong(id);
        RouteQueryResp routeQueryResp = routeService.selectById(routeId);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(routeQueryResp);
        return commonResp;
    }
}
