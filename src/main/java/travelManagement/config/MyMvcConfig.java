package travelManagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcConfig
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/10 下午7:22
 * @Version 1.0
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    private static final Logger LOG= LoggerFactory.getLogger(MyMvcConfig.class);

    /**
     * 磁盘上的文件路径
     */
    @Value("${file.dis-path}")
    private String DIS_PATH;

    /**
     * 将服务器中存储的图片资源暴露
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOG.info("外部尝试访问磁盘图片");
        registry.addResourceHandler("/pic-db/**").addResourceLocations("file:"+DIS_PATH);

        /**
         * http://127.0.0.1:8880/pic-db/1.jpg
         * 一旦有上面形式的访问路径
         * 就会向用户回显磁盘中的信息
         */
    }
}
