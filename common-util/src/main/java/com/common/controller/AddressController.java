package com.common.controller;

import com.common.dto.address.AddressSearchResultDTO;
import com.common.util.http.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/11/2 9:57
 * 地址转换服务->调用百度地图api
 */
@Api
@Controller
@RequestMapping("/address")
public class AddressController {

    @Value("${baidu.place.search.api}")
    private String placeSearchUrl;

    @Value("${baidu.api.ak}")
    private String ak;

    @ApiIgnore
    @RequestMapping("/index")
    public String jobList() {
        return "addressSearch";
    }

    @ApiOperation(value = "地址搜索", notes = "地址搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "检索关键字", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lat", value = "经度", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lon", value = "经度", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "radius", value = "检索半径", required = true, paramType = "query", dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/place")
    public AddressSearchResultDTO searchNearPlace(
            @RequestParam String query,
            @RequestParam String lat,
            @RequestParam String lon,
            @RequestParam(defaultValue = "2000") String radius) {
        Map<String, Object> map = new HashMap<>();
        map.put("query",query);
        map.put("location", lat + "," + lon);
        map.put("radius", radius);
        map.put("output", "json");
        map.put("ak", ak);
        AddressSearchResultDTO resultDTO = new AddressSearchResultDTO();
        Object response = HttpUtil.get(placeSearchUrl, map, resultDTO);
        return (AddressSearchResultDTO)response;
    }
}
