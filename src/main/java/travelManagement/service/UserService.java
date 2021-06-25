package travelManagement.service;

import travelManagement.req.UserQueryReq;
import travelManagement.req.UserSaveReq;
import travelManagement.resp.PageResp;
import travelManagement.resp.UserQueryResp;

public interface UserService {

    PageResp list(UserQueryReq req);

    void deleteById(Long id);

    void save(UserSaveReq req);

    UserQueryResp selectById(Long id);

}
