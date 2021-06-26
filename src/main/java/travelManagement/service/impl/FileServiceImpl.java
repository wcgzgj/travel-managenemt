package travelManagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import travelManagement.service.FileService;
import travelManagement.util.SnowFlake;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/23 下午6:13
 * @Version 1.0
 **/
@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOG= LoggerFactory.getLogger(travelManagement.service.impl.FileServiceImpl.class);

    @Resource
    private SnowFlake snowFlake;

    /**
     * 文件将要被存储的路径
     */
    @Value("${file.dis-path}")
    private String DIS_PATH;

    /**
     * 上传文件
     * @param file
     */
    @Override
    public String upload(MultipartFile file) {
        LOG.info("上传文件开始:{}",file);
        LOG.info("文件名为:{}",file.getOriginalFilename());
        LOG.info("文件大小为:{}",file.getSize());

        String originalFilename = file.getOriginalFilename();
        /**
         * 我们需要为文件添加一个雪花字段
         * 保证文件之间不会重名
         */
        String newFileName = String.valueOf(snowFlake.nextId()) + "-" + originalFilename;

        /**
         * 文件要被存储的全路径
         * 必须包括磁盘路径和要被存储的文件名
         */
        String fullPath = DIS_PATH + newFileName;

        /**
         * 创建全路径的文件对象
         */
        File dest = new File(fullPath);

        /**
         * 将我们的文件，写入指定目录
         */
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOG.info("文件存储成功，存储路径为:{}",dest.getAbsolutePath());
        return newFileName;
    }
}
