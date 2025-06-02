package co.id.xiaomun.serviceappsxiaomun.service.menuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.xiaomun.serviceappsxiaomun.entity.menu.MenuItem;
import co.id.xiaomun.serviceappsxiaomun.entity.menu.Offering;
import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.menuModel.MenuFilter;
import co.id.xiaomun.serviceappsxiaomun.model.menuModel.MenuRequest;
import co.id.xiaomun.serviceappsxiaomun.repository.MenuRepository;
import co.id.xiaomun.serviceappsxiaomun.repository.OfferingRepository;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OfferingRepository offeringRepository;

    public ResponseMap getAllMenu(MenuRequest request) throws Exception {

        ResponseMap response = new ResponseMap();
        List<MenuItem> menuList = menuRepository.findAll();

        List<Map<String, Object>> menuResult = menuList.stream().map(menu -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", menu.getId());
            map.put("name", menu.getName());
            map.put("price", menu.getPrice());
            map.put("category", menu.getCategory() != null ? menu.getCategory().getName() : null);
            map.put("description", menu.getDescription());
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("menu", menuResult);

        response.setStatus(true);
        response.setStatusCode("200");
        response.setResponseCode("00");

        response.setResponseMessage("Success get all menu");
        response.setResult(resultMap);

        return response;
    }

    public ResponseMap getMenuWithFilter(MenuFilter request) {
        ResponseMap response = new ResponseMap();

        String categoryName, keyword;

        categoryName = request.getCategory();
        keyword = request.getKeyword();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("menu", filterMenuItems(categoryName, keyword));

        response.setStatus(true);
        response.setStatusCode("200");
        response.setResponseCode("00");

        response.setResponseMessage("Success get all menu");
        response.setResult(resultMap);

        return response;
    }

     // func get filter menu
    public List<MenuItem> filterMenuItems(String categoryName, String keyword) {
        return menuRepository.findByCategoryNameAndNameContainingIgnoreCase(categoryName, keyword);
    }

    public ResponseMap getMenuPremiumCoffe(MenuRequest request) throws Exception {
        ResponseMap response = new ResponseMap();
        try {
            List<MenuItem> premiumMenus = menuRepository.findByCategoryId(5L); // kategori Minuman Sultan

            Map<String, Object> result = new HashMap<>();
            result.put("menus", premiumMenus);

            response.setStatus(true);
            response.setStatusCode("200");
            response.setResponseCode("00");
            response.setResponseMessage("Sukses mengambil data Minuman Sultan");
            response.setResult(result);

        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode("500");
            response.setResponseCode("99");
            response.setResponseMessage("Terjadi kesalahan: " + e.getMessage());
        }

        return response;
    }

    public ResponseMap getOffering(MenuRequest request) throws Exception {
        ResponseMap response = new ResponseMap();
        try {
            List<Offering> offeringList = offeringRepository.findAll();
            Map<String, Object> result = new HashMap<>();
            result.put("menus", offeringList);

            response.setStatus(true);
            response.setStatusCode("200");
            response.setResponseCode("00");
            response.setResponseMessage("Sukses mengambil data Offering");
            response.setResult(result);

        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode("500");
            response.setResponseCode("99");
            response.setResponseMessage("Terjadi kesalahan: " + e.getMessage());
        }

        return response;
    }

}
