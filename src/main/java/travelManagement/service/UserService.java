package travelManagement.service;

import travelManagement.req.UserQueryReq;
import travelManagement.req.UserSaveReq;
import travelManagement.resp.PageResp;

public interface UserService {

    PageResp list(UserQueryReq req);

    void deleteById(Long id);

    void save(UserSaveReq req);

}
