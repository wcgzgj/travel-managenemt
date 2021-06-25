package travelManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelManagement.mapper.TabSellerMapper;
import travelManagement.req.SellerQueryReq;
import travelManagement.resp.CommonResp;
import travelManagement.resp.PageResp;
import travelManagement.resp.SellerQueryResp;
import travelManagement.service.SellerService;

import javax.annotation.Resource;

/**
 * @ClassName SellerController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/seller")
public class SellerController {

    private static final Logger LOG= LoggerFactory.getLogger(SellerController.class);

    @Resource
    private SellerService sellerService;


    @GetMapping("/list")
    public CommonResp list(SellerQueryReq req) {
        LOG.info("传入的查询参数为:{}",req);
        PageResp pageResp = sellerService.list(req);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(pageResp);

        return commonResp;
    }


    @GetMapping("/selectById/{id}")
    public CommonResp selectById(@PathVariable("id")String id) {
        long sellerId = Long.parseLong(id);
        SellerQueryResp sellerQueryResp = sellerService.selectById(sellerId);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(sellerQueryResp);
        return commonResp;
    }
}
