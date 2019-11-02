package com.util.controller;

import com.util.dto.AddressSearchResultDTO;
import com.util.http.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/11/2 9:57
 */
@Api
@RestController
@RequestMapping("/address")
public class AddressController {

    @Value("${baidu.place.search.api}")
    private String placeSearchUrl;

    @Value("${baidu.api.ak}")
    private String ak;

    @ApiOperation(value = "addressSearch")
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
