package co.id.xiaomun.serviceappsxiaomun.service.menuService;

import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.request.menuModel.MenuFilter;
import co.id.xiaomun.serviceappsxiaomun.model.request.menuModel.MenuRequest;

public interface MenuService {

    public ResponseMap getAllMenu(MenuRequest request) throws Exception;

    public ResponseMap getMenuWithFilter(MenuFilter request);

    public ResponseMap getMenuPremiumCoffe(MenuRequest request) throws Exception;

    public ResponseMap getOffering(MenuRequest request) throws Exception;

}
