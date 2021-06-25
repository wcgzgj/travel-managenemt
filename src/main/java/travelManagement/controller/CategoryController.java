package travelManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelManagement.mapper.TabCategoryMapper;
import travelManagement.req.CategoryQueryReq;
import travelManagement.resp.CommonResp;
import travelManagement.resp.PageResp;
import travelManagement.resp.CategoryQueryResp;
import travelManagement.service.CategoryService;

import javax.annotation.Resource;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOG= LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private CategoryService categoryService;

    @Resource
    private TabCategoryMapper tabCategoryMapper;

    @GetMapping("/list")
    public CommonResp list(CategoryQueryReq req) {
        LOG.info("传入的查询参数为:{}",req);
        PageResp pageResp = categoryService.list(req);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(pageResp);

        return commonResp;
    }


    @GetMapping("/selectById/{id}")
    public CommonResp selectById(@PathVariable("id")String id) {
        long categoryId = Long.parseLong(id);
        CategoryQueryResp categoryQueryResp = categoryService.selectById(categoryId);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(categoryQueryResp);
        return commonResp;
    }
}
