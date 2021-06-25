package travelManagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import travelManagement.config.TravelManagementApplication;
import travelManagement.mapper.TabUserMapper;
import travelManagement.pojo.TabUser;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = TravelManagementApplication.class)
class TravelManagementApplicationTests {

    @Resource
    private TabUserMapper tabUserMapper;

    @Test
    void contextLoads() {
        List<TabUser> tabUsers = tabUserMapper.selectByExample(null);
        System.out.println(tabUsers);
    }

}
