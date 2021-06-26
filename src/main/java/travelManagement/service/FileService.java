package travelManagement.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/23 下午6:13
 * @Version 1.0
 **/
public interface FileService {

    /**
     * 上传文件
     * @param file
     */
    String upload(MultipartFile file);
}
