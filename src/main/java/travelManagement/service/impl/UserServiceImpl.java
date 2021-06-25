package travelManagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.text.SimpleDateFormat;
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

    private static final Logger LOG= LoggerFactory.getLogger(UserServiceImpl.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public PageResp list(UserQueryReq req) {
        int pageNum = req.getPageNum();
        int pageSize = req.getPageSize();
        String name = req.getName();
        String email = req.getEmail();

        LOG.info("name为:{}",name);
        LOG.info("email为:{}",email);


        TabUserExample tabUserExample = new TabUserExample();
        TabUserExample.Criteria criteria = tabUserExample.createCriteria();
        if (!StringUtil.isEmpty(name)) {
            criteria.andNameLike("%"+name+"%");
        }
        if (!StringUtil.isEmpty(email)) {
            criteria.andEmailLike("%"+email+"%");
        }

        PageHelper.startPage(pageNum,pageSize);
        List<TabUser> users = tabUserMapper.selectByExample(tabUserExample);
        PageInfo<TabUser> pageInfo = new PageInfo<>(users);

        List<UserQueryResp> userQueryResps = CopyUtil.copyList(users, UserQueryResp.class);
        for (UserQueryResp userQueryResp : userQueryResps) {
            userQueryResp.setBirthdayStr(sdf.format(userQueryResp.getBirthday()));
        }

        PageResp pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setList(userQueryResps);

        return pageResp;
    }

    @Override
    public void deleteById(Long id) {
        tabUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(UserSaveReq req) {
        Long id = req.getUid();
        if (id==null) {
            TabUser copy = CopyUtil.copy(req, TabUser.class);
            copy.setUid(snowFlake.nextId());
            tabUserMapper.insert(copy);
        } else {
            TabUser copy = CopyUtil.copy(req, TabUser.class);
            tabUserMapper.updateByPrimaryKeySelective(copy);
        }
    }

    @Override
    public UserQueryResp selectById(Long id) {
        TabUser tabUser = tabUserMapper.selectByPrimaryKey(id);
        UserQueryResp copy = CopyUtil.copy(tabUser, UserQueryResp.class);
        copy.setBirthdayStr(sdf.format(copy.getBirthday()));
        return copy;
    }
}
