package com.common.controller;

import com.common.dto.address.AddressSearchResultDTO;
import com.common.util.http.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/11/2 9:57
 */
@Api
@Controller
@RequestMapping("/address")
public class AddressController {

    @Value("${baidu.place.search.api}")
    private String placeSearchUrl;

    @Value("${baidu.api.ak}")
    private String ak;

    @RequestMapping("/search")
    public String jobList() {
        return "addressSearch";
    }

    @ApiOperation(value = "addressSearch")
    @ResponseBody
    @GetMapping("/place")
    public AddressSearchResultDTO searchNearPlace(
            @RequestParam String query, @RequestParam String location,
            @RequestParam(defaultValue = "2000") String radius) {
        Map<String, Object> map = new HashMap<>();
        map.put("query",query);
        map.put("location", location);
        map.put("radius", radius);
        map.put("output", "json");
        map.put("ak", ak);
        AddressSearchResultDTO resultDTO = new AddressSearchResultDTO();
        Object response = HttpUtil.get(placeSearchUrl, map, resultDTO);
        return (AddressSearchResultDTO)response;
    }
}
