package travelManagement.service;

import travelManagement.req.RouteQueryReq;
import travelManagement.req.RouteSaveReq;
import travelManagement.resp.PageResp;
import travelManagement.resp.RouteQueryResp;

public interface RouteService {

    PageResp list(RouteQueryReq req);

    void deleteById(Long id);

    void save(RouteSaveReq req);

    RouteQueryResp selectById(Long id);

}
