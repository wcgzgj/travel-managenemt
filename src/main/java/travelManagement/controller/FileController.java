package travelManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import travelManagement.resp.CommonResp;
import travelManagement.service.FileService;

import javax.annotation.Resource;

/**
 * @ClassName FileController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/23 下午6:11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger LOG= LoggerFactory.getLogger(travelManagement.controller.FileController.class);

    @Value("${file.web-path}")
    private String WEB_PATH;

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public CommonResp upload(@RequestParam MultipartFile file) {
        String newFileName = fileService.upload(file);
        // String filePath = WEB_PATH+newFileName;
        // LOG.info("返回的图片路径为:{}",filePath);
        LOG.info("获得的新的图片名称为:{}",newFileName);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(newFileName);
        return commonResp;
    }

}
