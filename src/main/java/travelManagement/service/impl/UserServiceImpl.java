package travelManagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import travelManagement.mapper.TabUserMapper;
import travelManagement.pojo.TabUser;
import travelManagement.pojo.TabUserExample;
import travelManagement.req.UserQueryReq;
import travelManagement.req.UserSaveReq;
import travelManagement.resp.PageResp;
import travelManagement.resp.UserQueryResp;
import travelManagement.service.UserService;
import travelManagement.util.CopyUtil;
import travelManagement.util.SnowFlake;
import travelManagement.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/24 下午7:22
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private TabUserMapper tabUserMapper;


    @Override
    public PageResp list(UserQueryReq req) {
        int pageNum = req.getPageNum();
        int pageSize = req.getPageSize();
        String name = req.getName();
        String email = req.getEmail();

        TabUserExample tabUserExample = new TabUserExample();
        TabUserExample.Criteria criteria = tabUserExample.createCriteria();
        if (StringUtil.isEmpty(name)) {
            criteria.andNameLike("%"+name+"%");
        }
        if (StringUtil.isEmpty(email)) {
            criteria.andEmailLike("%"+email+"%");
        }


        PageHelper.startPage(pageNum,pageSize);
        List<TabUser> users = tabUserMapper.selectByExample(tabUserExample);
        PageInfo<TabUser> pageInfo = new PageInfo<>();

        List<UserQueryResp> userQueryResps = CopyUtil.copyList(users, UserQueryResp.class);

        PageResp pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setList(userQueryResps);


        return pageResp;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void save(UserSaveReq req) {

    }
}
