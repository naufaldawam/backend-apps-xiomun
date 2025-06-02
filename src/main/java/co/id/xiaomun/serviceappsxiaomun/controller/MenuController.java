package co.id.xiaomun.serviceappsxiaomun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.id.xiaomun.serviceappsxiaomun.constant.LogInfoFormat;
import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.menuModel.MenuFilter;
import co.id.xiaomun.serviceappsxiaomun.model.menuModel.MenuRequest;
import co.id.xiaomun.serviceappsxiaomun.service.menuService.MenuService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private LogInfoFormat logInfoFormat;

    @PostMapping("/getMenu")
    public ResponseEntity<ResponseMap> getMenu(@RequestBody MenuRequest request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = menuService.getAllMenu(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("Access get all menu",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/getMenu", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/filterMenu")
    public ResponseEntity<ResponseMap> filterMenu(@RequestBody MenuFilter request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = menuService.getMenuWithFilter(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("Access get menu with filter",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/filterMenu", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/getMenuPremiumCoffe")
    public ResponseEntity<ResponseMap> getMenuPremiumCoffe(@RequestBody MenuRequest request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = menuService.getMenuPremiumCoffe(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("Access get menu premium coffe",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/getMenuPremiumCoffe", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/getOfferingMenu")
    public ResponseEntity<ResponseMap> getOfferingMenu(@RequestBody MenuRequest request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = menuService.getOffering(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("Access get offering",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/getOfferingMenu", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

}
